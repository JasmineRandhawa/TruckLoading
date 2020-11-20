import java.io.IOException;
import java.util.List;

import Algorithms.Harmonic_OnlineAlgorithm;
import Algorithms.OPT_Offline;
import Algorithms.OnlineAlgorithm;

import DataObjects.Output;
import DataObjects.Constants;
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
			List<Item> inputItems = ItemOperations.LoadItemsIntoListFromFile(dataFilePath);
			
			// Get output result from Offline - First Fit Decreasing Algorithm
			Output resultOPT_FFD = OPT_Offline.GetResults(inputItems);
	
			// Get output result from Online - First Fit Algorithm
			Output resultOnline_FirstFit = OnlineAlgorithm.GetResults(inputItems, Constants.FirstFitAlgoName);
	
			// Get output result from Online - Best Fit Algorithm
			Output resultOnline_BestFit = OnlineAlgorithm.GetResults(inputItems, Constants.BestFitAlgoName);
	
			// Get output result from Online - Harmonic Fit Algorithm
			Output resultOnline_HarmonicFit = Harmonic_OnlineAlgorithm.GetResults(inputItems, Constants.HarmonicFitAlgoName);
	
			// compute competitive ratio , wastage and delivered items
			List<PerformanceComparison> comparisonResults = ComputePerformance.GetResult(inputItems,
															resultOPT_FFD, resultOnline_FirstFit,
					                                        resultOnline_BestFit, resultOnline_HarmonicFit);
	
			// Load results into Files
			CreateOutputHtmlFile.CreateHTMLFile(inputItems, resultOPT_FFD, resultOnline_FirstFit,resultOnline_BestFit, 
					                            resultOnline_HarmonicFit,comparisonResults);
			System.out.println(Constants.SuccessMessage + Constants.OutputFilePath);
		}
		catch(Exception e) {
			System.out.println(Constants.ErrorMessage + e.getMessage());
		}
	}

}
