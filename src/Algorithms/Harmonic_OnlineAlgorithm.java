package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.HarmonicClass;
import DataObjects.Item;
import Utility.TruckOperations;

/* Fetch results of Online algorithms for truck loading*/
public class Harmonic_OnlineAlgorithm {

	// define assumptions
	static final int truckCapacity = Constants.TruckCapacity;
	static final int numberOfOpenTrucksAllowed = Constants.OpenTrucksThreshold;

	static List<Item> unProcessedItemList = new ArrayList<Item>();
	static List<Truck> openTrucks = new ArrayList<Truck>();
	static List<Truck> closedTrucks = new ArrayList<Truck>();
	static List<HarmonicClass> classes = new ArrayList<HarmonicClass>();
	
	/* Online Algorithm for Truck Loading */
	public static Output GetResults(List<Item> inputItems, String algorithmName) 
	{
		//initialize output
		InitializeOutput(inputItems);
		
		
		Truck truck;
		int iteratorIndex = 0; 
		
		// processing items one by one in online manner
		for (Item item : inputItems) 
		{
			int classIdForItem = GetHarmonicClassForItem(item.getItemSize());
			
			if(iteratorIndex == 0)
			{
				// open at least one truck initially
				truck = new Truck(1 , new ArrayList<Item>(), truckCapacity, 0, 0 ,false,classIdForItem);
				openTrucks.add(truck);
			}
			// scan all open trucks and find available one that can fit the item
			Truck availableTruck = null;
			if (algorithmName == Constants.HarmonicFitAlgoName)
				availableTruck = TruckOperations.GetCurrentOpenTruckWithinClassThatFitsItem(openTrucks,item.getItemSize(), classIdForItem);

			// if such truck is found , load item into it
			if (availableTruck != null)
				LoadItemIntoExistingTruck(availableTruck, item);

			// if item cannot fit in any of the open trucks
			else 
			{
				// check if we reach the threshold for number of open trucks is reached
				if (openTrucks.size() >= numberOfOpenTrucksAllowed) 
					CloseTruck(algorithmName,classIdForItem);
				
				// open new truck and loads item in it
				OpenNewTruckWithItemInItemClass(item,classIdForItem);
			}
			iteratorIndex++;
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
		classes = TruckOperations.DivideIntoKClasses(truckCapacity,numberOfOpenTrucksAllowed);
	}
	
	private static int GetHarmonicClassForItem(int itemSize) {
		for (HarmonicClass harmonicClass: classes)
		{
			if(itemSize >= harmonicClass.getMinClassSize() && itemSize <= harmonicClass.getMaxClassSize())
				return harmonicClass.getClassId();
		}
		return 0;
	}
	
	// open a new Truck and load item in it
	private static void OpenNewTruckWithItemInItemClass(Item item, int classIdForItem) {
		Truck newTruck = new Truck(openTrucks.size() + closedTrucks.size() + 1, new ArrayList<Item>(), truckCapacity, 0,
				0,false,classIdForItem);
		TruckOperations.LoadItemIntoTruck(newTruck, item);
		openTrucks.add(newTruck);
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
	private static Truck CloseTruck(String algorithmName, int classIdForItem) {
		// TODO Auto-generated method stub
		// close one truck
		Truck truckToBeClosed = null;
		//close current truck open in the class
		if (algorithmName == Constants.HarmonicFitAlgoName)
			truckToBeClosed = TruckOperations.FindCurrentOpenTruck(openTrucks);
		if (truckToBeClosed != null) {
			openTrucks.remove(truckToBeClosed);
			// update trucks lists
			closedTrucks.add(truckToBeClosed);
		}
		return truckToBeClosed;
	}

}
