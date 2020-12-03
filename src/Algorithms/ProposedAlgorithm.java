package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;

import Utility.Common;
import Utility.TruckOperations;

/* Fetch results of Online algorithm using Proposed Risk metric Approaches for truck loading*/
public class ProposedAlgorithm 
{
	// define assumptions
	static final double truckCapacity = Common.RoundDecimal(Constants.TruckCapacity);
	static final int numberOfOpenTrucksAllowed = Constants.OpenTrucksThreshold;

	// declare output
	static List<Truck> openTrucks = new ArrayList<Truck>();
	static List<Truck> closedTrucks = new ArrayList<Truck>();
	static int T;

	/* Public Methods */
	
	/* get results for FF, BF and HF online algorithm under all risk metric approaches */
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
			else if (algorithmName == Constants.HarmonicFitAlgoName) {
				classIdForItem = TruckOperations.GetHarmonicClassForItem(item.getItemSize());
				truckFound = TruckOperations.FindCurrentOpenTruckWithinClassThatFitsItem(openTrucks,
						                        item.getItemSize(), classIdForItem);
			}

			if (truckFound != null)
				LoadItemIntoTruck(truckFound, item);
			else // if such truck is not found , open new truck and load item into it
				OpenNewTruckWithItem(item, classIdForItem);

			// close open truck if possible
			CloseTruck(algorithmApproach);
		}

		// force close all remaining open trucks
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

	// open a new Truck and load item in it */
	private static void OpenNewTruckWithItem(Item item, int classIdForItem) 
	{
		Truck newTruck = new Truck( openTrucks.size() + closedTrucks.size() + 1, 
									new ArrayList<Item>(), truckCapacity,
									classIdForItem, T);
		TruckOperations.LoadItemIntoTruck(newTruck, item);
		openTrucks.add(newTruck);
	}

	/*  load item into a open existing truck */
	private static void LoadItemIntoTruck(Truck availableTruck, Item item) 
	{
		availableTruck = TruckOperations.LoadItemIntoTruck(availableTruck, item);
		openTrucks.remove(availableTruck);
		openTrucks.add(availableTruck);
	}

	/*  close truck using proposed risk metric and threshold logic */
	private static void CloseTruck(String algorithmApproach) 
	{
		List<Truck> trucksToBeClosed = new ArrayList<Truck>();
		
		/*compute risk metric 1 for every truck 
		  and close the truck if risk metric is >1 or if truck is full*/
		for (Truck truck : openTrucks) 
		{
			//compute gain risk, profit loss before closing
			double risk = 0.0;
			double spaceRemainingInTruck = truck.getRemainingCapacity();
			double totalSizeOfItemsAtRisk = TruckOperations.GetTotalSizeOfItemsDueNextDay(truck, T);
			
			//get gain and loss value based upon risk metric  algorithm approach applied
			double gain = GetGainValueAsPerRiskMetric(algorithmApproach,spaceRemainingInTruck);
			double loss = GetLossValueAsPerRiskMetric(algorithmApproach,totalSizeOfItemsAtRisk);
			
			boolean isTruckFull = false;
			if (gain == 0.0) 
			{
				isTruckFull = true;
				truck.setClosingReason(Constants.FullTruckCloseMessage);
			}
			if (!isTruckFull)
				risk = Common.RoundDecimal(loss / gain);

			truck.setGain(gain);
			truck.setLossGainRatio(risk);
			truck.setLoss(loss);
			
			double waste_value = Common.RoundDecimal(TruckOperations.GetTotalWasteOfTruck(truck, T) + truckCapacity);
			double profit_value = Common.RoundDecimal(TruckOperations.GetTotalProfitOfTruck(truck, T));
			truck.setProfit(profit_value);
			truck.setWaste(waste_value);
			truck.setClosingTime(0);
			truck.setTotalProfitIncurredAtTimeT(Common.RoundDecimal(TruckOperations.GetTotalProfitSoFar(closedTrucks) + profit_value));
			truck.setTotalWasteIncurredAtTimeT(Common.RoundDecimal(TruckOperations.GetTotalWasteSoFar(closedTrucks) + waste_value));
			
			// if risk > 1  or truck is full, close the truck
			if (risk >= 1.0 || isTruckFull) 
			{
				if(!isTruckFull)
					truck.setClosingReason(Constants.RiskExceedingOneCloseMessage);
				truck.setClosingTime(T);
				closedTrucks.add(truck);
				trucksToBeClosed.add(truck);
			}
		}
		
		// update openTrucksCopy list by removing trucks the items that are closed
		for (Truck truck : trucksToBeClosed)
			openTrucks.remove(truck);

		//if threshold is reached, close the truck with max risk
		if (openTrucks.size() > numberOfOpenTrucksAllowed) 
		{
			Truck truck = TruckOperations.FindTruckWithMaxRiskForKeepingOpen(openTrucks);
			if (truck != null) 
			{
				truck.setClosingReason(Constants.RiskExceedingOneCloseMessage);
				truck.setClosingTime(T);
				openTrucks.remove(truck);
				closedTrucks.add(truck);
			}
		}
	}
	
	/* As per algorithm approach for risk metric, compute loss value incurred if truck is kept open */
	private static double GetLossValueAsPerRiskMetric(String algorithmApproach, double totalSizeOfItemsAtRisk) 
	{
		double loss = 0;
		if(algorithmApproach == Constants.ProposedApproach1)
			loss = Common.RoundDecimal(totalSizeOfItemsAtRisk) ;
		else if(algorithmApproach == Constants.ProposedApproach2)
			loss = Common.RoundDecimal(2* totalSizeOfItemsAtRisk) ;
		else if(algorithmApproach == Constants.ProposedApproach3)
			loss = Common.RoundDecimal(2* totalSizeOfItemsAtRisk) ;
		return loss;
	}

	/*  as per algorithm approach for risk metric, compute gain value incurred if truck is kept open */
	private static double GetGainValueAsPerRiskMetric(String algorithmApproach, double spaceRemainingInTruck) 
	{
		double gain = 0;
		if(algorithmApproach == Constants.ProposedApproach1)
			gain = Common.RoundDecimal(spaceRemainingInTruck) ;
		else if(algorithmApproach == Constants.ProposedApproach2)
			gain = Common.RoundDecimal((spaceRemainingInTruck* spaceRemainingInTruck)/2) ;
		else if(algorithmApproach == Constants.ProposedApproach3)
			gain = Common.RoundDecimal((2* (spaceRemainingInTruck* spaceRemainingInTruck))/3) ;
		return gain;
	}

	/* close all the remaining last open trucks after all input sequence is processed */
	private static void ForceCloseAllRemainingOpenTrucks()
	{
		for (Truck truck : openTrucks) 
		{
			truck.setClosingReason(Constants.RemainingOpenTrucksCloseMessage);
			truck.setClosingTime(T);
			closedTrucks.add(truck);
		}
		openTrucks = new ArrayList<Truck>();
	}
}
