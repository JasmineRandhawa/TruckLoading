package Utility;

import DataObjects.Output;
import DataObjects.Input;
import DataObjects.PerformanceComparison;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Helper Methods
public class Helper {

	// load data file into Input list
	public static List<Input> LoadItemsIntoListFromFile(String dataFilePath) {
		List<Input> inputList = new ArrayList<Input>();
		return inputList;
	}

	// create HTML file containing all results for Truck Loading (Offline + Online
	// Algorithms)
	public static String CreateHTMLFile(List<Input> inputList, Output resultOPT, List<Output> resultOnline,
			List<PerformanceComparison> comparisonResult) {
		String fileLocation = "";
		String html = CreateOutputHTML.GetHTMLString(inputList, resultOPT, resultOnline, comparisonResult);
		CreateHTMLFile(fileLocation, html);
		return fileLocation;
	}

	private static void CreateHTMLFile(String fileLocation, String html) {
	}

}
