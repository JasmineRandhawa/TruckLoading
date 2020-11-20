package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;
import Utility.ItemOperations;
import Utility.TruckOperations;

/* Fetch results of Optimal Offline Algorithm truck loading*/
public class OPT_Offline {

	// define assumptions
	static final int truckCapacity = Constants.TruckCapacity;
	static final int numberOfOpenTrucksAllowed = Constants.OpenTrucksThreshold;
	
	static List<Item> unProcessedItemList = new ArrayList<Item>();
	static List<Truck> openTrucks = new ArrayList<Truck>();
	static List<Truck> closedTrucks = new ArrayList<Truck>();
	
	/* Optimal Packing for Truck Loading */
	public static Output GetResults(List<Item> inputItems) 
	{
		//initialize output
		InitializeOutput(inputItems);
		
		if(inputItems != null && inputItems.size()>0)
		{
			List<Item> sortedItemList = new ArrayList<Item>();
		
			sortedItemList.addAll(inputItems);
			Collections.sort(sortedItemList, Item.ItemDeliveryDealineComparator);
			Collections.sort(sortedItemList, Item.ItemSizeComparator);
			unProcessedItemList.addAll(sortedItemList);

			while (unProcessedItemList.size() > 0) 
			{
				// open new truck
				Truck truck = new Truck(openTrucks.size()+closedTrucks.size()+1, 
										new ArrayList<Item>(), 
										truckCapacity, 0,0 ,false,0);
				openTrucks.add(truck);
	
				// load the truck with items that can fill the truck the best
				while (true) 
				{
					Item item = ItemOperations.GetFirstUnProcessedItemThatFitTheTruck(truck.getRemainingCapacity(),unProcessedItemList);
					if (item != null) {
						truck = TruckOperations.LoadItemIntoTruck(truck, item);
						unProcessedItemList.remove(item);
					}
					//truck cannot be filled anymore
					else
						break;
				}
				// close the truck
				CloseTruck(truck);
			}
		}
		return new Output(openTrucks, closedTrucks, unProcessedItemList);
	}

	/* Private Methods */
	//initialize Output
	private static void InitializeOutput(List<Item> inputItems) {
		openTrucks = new ArrayList<Truck>();
		closedTrucks = new ArrayList<Truck>();
		unProcessedItemList = new ArrayList<Item>();
	}
	
	// close truck
	private static void CloseTruck(Truck truckToBeClosed) {
		openTrucks.remove(truckToBeClosed);
		closedTrucks.add(truckToBeClosed);	
	}
	
	
	
}
