package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;

import Utility.Common;
import Utility.TruckOperations;

/* Fetch results of Online algorithm using Existing Approach for truck loading*/
public class ExistingAlgorithm 
{
	// define assumptions
	static final double truckCapacity = Common.RoundDecimal(Constants.TruckCapacity);
	static final int numberOfOpenTrucksAllowed = Constants.OpenTrucksThreshold;

	// declare output
	static List<Truck> openTrucks = new ArrayList<Truck>();
	static List<Truck> closedTrucks = new ArrayList<Truck>();
	static int T;

	/* Public Methods */

	/* get results for FF, BF and HF as per existing approach */
	public static Output GetResults(List<Item> inputItems, String algorithmName , String algorithmApproach) 
	{
		// initialize output
		InitializeOutput(inputItems);

		// processing items one by one
		for (Item item : inputItems) 
		{
			int classIdForItem = 0;

			// increase the time T by 1
			T = T + 1;

			// scan all open trucks and find available one as per algorithm logic that can
			// fit the item
			Truck truckFound = null;
			if (algorithmName == Constants.FirstFitAlgoName)
				truckFound = TruckOperations.FindFirstTruckThatFits(openTrucks, item.getItemSize());
			else if (algorithmName == Constants.BestFitAlgoName)
				truckFound = TruckOperations.FindFullestTruck(openTrucks, item.getItemSize());
			else if (algorithmName == Constants.HarmonicFitAlgoName) 
			{
				classIdForItem = TruckOperations.GetHarmonicClassForItem(item.getItemSize());
				truckFound = TruckOperations.FindCurrentOpenTruckWithinClassThatFitsItem(openTrucks,
						item.getItemSize(), classIdForItem);
			}

			if (truckFound != null)
				LoadItemIntoTruck(truckFound, item);
			else // if such truck is not found , open new truck and load item into it
				OpenNewTruckWithItem(item, classIdForItem);

			CloseTruck(algorithmName);
		}

		// force close last remaining open trucks
		ForceCloseAllRemainingOpenTrucks();

		double profit = TruckOperations.GetTotalProfitSoFar(closedTrucks);
		double waste = TruckOperations.GetTotalWasteSoFar(closedTrucks);
		return new Output(closedTrucks, profit, waste, algorithmName,algorithmApproach);
	}

	
	/* Private Methods */

	/*  initialize Output */
	private static void InitializeOutput(List<Item> inputItems) 
	{
		openTrucks = new ArrayList<Truck>();
		closedTrucks = new ArrayList<Truck>();
		TruckOperations.DivideIntoKHarmonicClasses(truckCapacity, numberOfOpenTrucksAllowed);
		T = 0;
	}

	/*  open a new Truck and load item in it */
	private static void OpenNewTruckWithItem(Item item, int classIdForItem) 
	{
		Truck newTruck = new Truck(openTrucks.size() + closedTrucks.size() + 1, 
				                   new ArrayList<Item>(), truckCapacity,
				                   classIdForItem, T);
		TruckOperations.LoadItemIntoTruck(newTruck, item);
		openTrucks.add(newTruck);
	}

	// load item into an open existing truck */
	private static void LoadItemIntoTruck(Truck availableTruck, Item item) 
	{
		availableTruck = TruckOperations.LoadItemIntoTruck(availableTruck, item);
		openTrucks.remove(availableTruck);
		openTrucks.add(availableTruck);
	}

	/*  Close truck as per algorithm logic i.e. First truck, or best truck or current truck */
	private static void CloseTruck(String algorithmName) 
	{
		// force close all fully filled open trucks
		ForceCloseAllFullyFilledOpenTrucks();
		
		// if threshold is still reached, close truck
		if (openTrucks.size() > numberOfOpenTrucksAllowed)
		{
			Truck truckToBeClosed = null;
			if (algorithmName == Constants.FirstFitAlgoName)
				truckToBeClosed = TruckOperations.FindFirstOpenTruckWithinDeadline(openTrucks, T);
			else if (algorithmName == Constants.BestFitAlgoName)
				truckToBeClosed = TruckOperations.FindBestOpenTruckWithMaxItemsWithinDeadline(openTrucks, T);
			else if (algorithmName == Constants.HarmonicFitAlgoName)
				truckToBeClosed = TruckOperations.FindCurrentOpenTruck(openTrucks);
	
			if (truckToBeClosed != null)
			{				
				Close(truckToBeClosed, Constants.ThresholdCloseMessage);
				openTrucks.remove(truckToBeClosed);
			}
		}
	}

	/*  close all open trucks that are filled up to full capacity */
	private static void ForceCloseAllFullyFilledOpenTrucks() 
	{
		List<Truck> trucksToBeClosed = new ArrayList<Truck>();
		for (Truck truck : openTrucks)
		{
			if (truck.getRemainingCapacity() == 0.0)
			{
				Close(truck, Constants.FullTruckCloseMessage);
				trucksToBeClosed.add(truck);
			}
		}
		
		// update open trucks list
		for (Truck truck : trucksToBeClosed)
			openTrucks.remove(truck);
	}

	/* close all the remaining last open trucks after all input sequence is processed */
	private static void ForceCloseAllRemainingOpenTrucks() 
	{
		// add all open trucks to closed trucks list
		for (Truck truck : openTrucks) 
			Close(truck, Constants.RemainingOpenTrucksCloseMessage);
		
		//reset the opne trucks list
		openTrucks = new ArrayList<Truck>();
	}

	/* compute waste profit  values and close the truck */
	private static void Close(Truck truck, String reason) 
	{
		double waste_value = Common.RoundDecimal(TruckOperations.GetTotalWasteOfTruck(truck, T) + truckCapacity);
		double profit_value = Common.RoundDecimal(TruckOperations.GetTotalProfitOfTruck(truck, T));
		truck.setProfit(profit_value);
		truck.setWaste(waste_value);
		truck.setClosingTime(T);
		truck.setClosingReason(reason);
		truck.setTotalProfitIncurredAtTimeT(Common.RoundDecimal(TruckOperations.GetTotalProfitSoFar(closedTrucks) + profit_value));
		truck.setTotalWasteIncurredAtTimeT(Common.RoundDecimal(TruckOperations.GetTotalWasteSoFar(closedTrucks) + waste_value));
		closedTrucks.add(truck);
	}	
}
