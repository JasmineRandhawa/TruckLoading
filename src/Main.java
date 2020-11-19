import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Algorithms.OPT_Offline;
import Algorithms.Online_BestFit;
import Algorithms.Online_FirstFit;
import Algorithms.Online_HarmonicFit;

import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.HtmlStrings;
import DataObjects.Item;
import DataObjects.PerformanceComparison;

import Performance.ComputePerformance;
import Utility.CreateOutputHtmlFile;
import Utility.ItemOperations;

// Main.Java to compute and display results of Truck Loading  Algorithms
public class Main {

	public static void main(String[] args) throws IOException {
		try
		{
			String dataFilePath = Constants.DataFilePath;
	
			// load item data from file into a list
			List<Item> itemList = ItemOperations.LoadItemsIntoListFromFile(dataFilePath);
	
			// define thresholds
			int truckCapacity = Constants.TruckCapacity;
			int numberOfOpenTrucksThreshold = Constants.OpenTrucksThreshold;
			
			// For OPT Offline - First Fit Decreasing Algorithm we Sort items in ascending order of delivery deadline and descending order of
			// their size
			List<Item> sortedItemList = new ArrayList<Item>();
			sortedItemList.addAll(itemList);
			Collections.sort(sortedItemList, Item.ItemDeliveryDealineComparator);
			Collections.sort(sortedItemList, Item.ItemSizeComparator);
	
			
			// Get output result from Offline - First Fit Decreasing Algorithm
			Output resultOPT_FFD = OPT_Offline.GetResults(sortedItemList, numberOfOpenTrucksThreshold, truckCapacity);
	
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
			CreateOutputHtmlFile.CreateHTMLFile(truckCapacity,numberOfOpenTrucksThreshold,itemList,sortedItemList, resultOPT_FFD, resultOnline_FirstFit,
					resultOnline_BestFit, resultOnline_HarmonicFit, compareResults);
			System.out.println(Constants.SuccessMessage + Constants.OutputFilePath);
		}
		catch(Exception e) {
			System.out.println(Constants.ErrorMessage + e.getMessage());
		}
	}

}
