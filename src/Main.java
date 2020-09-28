import Algorithms.OPT;
import Algorithms.Online;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Input;
import DataObjects.PerformanceComparison;
import Performance.ComputePerformance;
import Utility.Helper;
import java.util.List;

// Main.Java to compute and display results of Truck Loading  Algorithms
public class Main {

	public static void main(String[] args) {
		String dataFilePath = Constants.DataFilePath;

		// load data into a list
		List<Input> inputList = Helper.LoadItemsIntoListFromFile(dataFilePath);
		System.out.println(inputList);

		// Call Offline Optimal Algorithm
		Output resultOPT = OPT.GetOfflineTruckLoadingResults(inputList);

		// Call All Online Algos and fetch combined results
		List<Output> resultOnline = Online.GetOnlineTruckLoadingResults(inputList);

		// compute competitive ratio
		List<PerformanceComparison> compareResults = ComputePerformance.GetResult(resultOPT, resultOnline);

		// DisplayAllResults();
		String fileLocation = Helper.CreateHTMLFile(inputList, resultOPT, resultOnline, compareResults);
		System.out.println("View Bin packing results at below location : \n" + fileLocation);
	}

}
