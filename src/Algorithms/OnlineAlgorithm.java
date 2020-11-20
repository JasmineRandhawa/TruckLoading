package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;
import Utility.TruckOperations;

/* Fetch results of Online algorithms for truck loading*/
public class OnlineAlgorithm {

	// define assumptions
	static final int truckCapacity = Constants.TruckCapacity;
	static final int numberOfOpenTrucksAllowed = Constants.OpenTrucksThreshold;

	static List<Item> unProcessedItemList = new ArrayList<Item>();
	static List<Truck> openTrucks = new ArrayList<Truck>();
	static List<Truck> closedTrucks = new ArrayList<Truck>();

	/* Online Algorithm for Truck Loading */
	public static Output GetResults(List<Item> inputItems, String algorithmName) 
	{
		//initialize output
		InitializeOutput(inputItems);
		
		// open at least one truck initially
		Truck truck = new Truck(1 , new ArrayList<Item>(), truckCapacity, 0, 0,false,0);
		openTrucks.add(truck);

		// processing items one by one in online manner
		for (Item item : inputItems) 
		{
			// scan all open trucks and find available one that can fit the item
			Truck availableTruck = null;
			if (algorithmName == Constants.FirstFitAlgoName)
				availableTruck = TruckOperations.GetFirstTruckThanItemCanFitInto(openTrucks, item.getItemSize());
			else if (algorithmName == Constants.BestFitAlgoName)
				availableTruck = TruckOperations.GetBestTruckThanItemCanFitInto(openTrucks, item.getItemSize());

			// if such truck is found , load item into it
			if (availableTruck != null)
				LoadItemIntoExistingTruck(availableTruck, item);

			// if item cannot fit in any of the open trucks
			else 
			{
				// check if we reach the threshold for number of open trucks is reached
				if (openTrucks.size() >= numberOfOpenTrucksAllowed) 
					CloseTruck(algorithmName);
				
				// open new truck and loads item in it
				OpenNewTruckWithItem(item);
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
		unProcessedItemList.addAll(inputItems);
	}
	
	// open a new Truck and load item in it
	private static void OpenNewTruckWithItem(Item item) {
		Truck newtruck = new Truck(openTrucks.size() + closedTrucks.size() + 1, new ArrayList<Item>(), truckCapacity,0,0,false,0);
		TruckOperations.LoadItemIntoTruck(newtruck, item);
		openTrucks.add(newtruck);
		unProcessedItemList.remove(item);
	}

	// load item into a open truck
	private static void LoadItemIntoExistingTruck(Truck availableTruck, Item item) {
		availableTruck = TruckOperations.LoadItemIntoTruck(availableTruck, item);
		openTrucks.remove(availableTruck);
		openTrucks.add(availableTruck);
		unProcessedItemList.remove(item);
	}
	
	// close truck
	private static Truck CloseTruck(String algorithmName) {
		// TODO Auto-generated method stub
		// close one truck
		Truck truckToBeClosed = null;
		if (algorithmName == Constants.FirstFitAlgoName)
			truckToBeClosed = TruckOperations.FindFirstOpenTruckWithLowestDeliveryDate(openTrucks);
		else if (algorithmName == Constants.BestFitAlgoName)
			truckToBeClosed = TruckOperations.FindFullestOpenTruckWithLowestDeliveryDate(openTrucks);

		if (truckToBeClosed != null) {
			openTrucks.remove(truckToBeClosed);
			// update trucks lists
			closedTrucks.add(truckToBeClosed);
			
		}
		return truckToBeClosed;
	}

}
