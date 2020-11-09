import java.util.List;

import Algorithms.OPT_FirstFitDecreasing;
import Algorithms.Online_BestFit;
import Algorithms.Online_FirstFit;
import Algorithms.Online_HarmonicFit;

import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;
import DataObjects.PerformanceComparison;

import Performance.ComputePerformance;
import Utility.CreateOutput;
import Utility.Helper;

// Main.Java to compute and display results of Truck Loading  Algorithms
public class Main {

	public static void main(String[] args) {
		String dataFilePath = Constants.DataFilePath;

		// load item data from file into a list
		List<Item> itemList = Helper.LoadItemsIntoListFromFile(dataFilePath);
		System.out.println(itemList);

		// define thresholds
		int numberOfOpenTrucksThreshold = Constants.TruckCapacity;
		int truckCapacity = Constants.OpenTrucksThreshold;

		// Get output result from Offline - First Fit Decreasing Algorithm
		Output resultOPT_FFD = OPT_FirstFitDecreasing.GetResults(itemList, numberOfOpenTrucksThreshold, truckCapacity);

		// Get output result from Online - First Fit Algorithm
		Output resultOnline_FirstFit = Online_FirstFit.GetResults(itemList, numberOfOpenTrucksThreshold, truckCapacity);

		// Get output result from Online - Best Fit Algorithm
		Output resultOnline_BestFit = Online_BestFit.GetResults(itemList, numberOfOpenTrucksThreshold, truckCapacity);

		// Get output result from Online - Harmonic Fit Algorithm
		Output resultOnline_HarmonicFit = Online_HarmonicFit.GetResults(itemList, numberOfOpenTrucksThreshold,
				truckCapacity);

		// compute competitive ratio , wastage and delivered items
		List<PerformanceComparison> compareResults = ComputePerformance.GetResult(resultOPT_FFD, resultOnline_FirstFit,
				resultOnline_BestFit, resultOnline_HarmonicFit);

		// Load results into Files
		String folderLocation = CreateOutput.CreateHTMLFile(itemList, resultOPT_FFD, resultOnline_FirstFit,
				resultOnline_BestFit, resultOnline_HarmonicFit, compareResults);
		System.out.println("View Truck Loading results in files at below location : \n" + folderLocation);
	}

}
