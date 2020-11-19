package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Item;
import Utility.TruckOperations;

/* Fetch results of Online approach - Best Fit Algorithm for truck loading*/
public class Online_BestFit {
	
	static List<Item> unProcessedItemList = new ArrayList<Item>();

	/* First Fit Algorithm for Truck Loading */
	public static Output GetResults(List<Item> itemList, int numberOfOpenTrucksThreshold, int truckCapacity) {
		
		List<Truck> openTrucks = new ArrayList<Truck>(numberOfOpenTrucksThreshold);
		List<Truck> closedTrucks = new ArrayList<Truck>();
		unProcessedItemList.addAll(itemList);
		
		// open atleast one truck initially
		Truck defaultFirstTruck = new Truck(1, new ArrayList<Item>(), truckCapacity, 0, 0);
		openTrucks.add(defaultFirstTruck);

		//processing items one by one in online manner
		for (Item item : itemList) {
			
			boolean isItemAdded = false;
			
			//find an open truck which can fit the item
			Truck truck = TruckOperations.GetTruckThanItemCanFitInto(openTrucks, item.getItemSize());
			
			//if such truck is found , load item into it
			if (truck != null) {
				truck = TruckOperations.LoadItemIntoTruck(truck, item);
				isItemAdded = true;
				unProcessedItemList.remove(item);
			}

			// if item cannot fit in any of the open trucks
			if (!isItemAdded) {
				// check if we reach the threshold for number of open trucks allowed
				if (openTrucks.size() == numberOfOpenTrucksThreshold) {
					
					Truck firstTruck = TruckOperations.FindBestOpenTruckThatHasUnitDeliveryDate(openTrucks);
					if(firstTruck!=null)
					{
						// close this truck
						closedTrucks.add(firstTruck);
						openTrucks.remove(firstTruck);
					}
					else
						break;
				}
				// open new truck with item in it
				openTrucks = TruckOperations.openNewTruck(item, truckCapacity, openTrucks,closedTrucks);
				unProcessedItemList.remove(item);
			}
		}

		// return output in form of open and closed trucks after serving all items
		Output output = new Output(openTrucks, closedTrucks, unProcessedItemList);
		return output;
	}
}
