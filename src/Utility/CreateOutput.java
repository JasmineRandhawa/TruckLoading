package Utility;

import java.util.List;

import DataObjects.Item;
import DataObjects.Output;
import DataObjects.PerformanceComparison;

/*Create Output HTML Files*/
public class CreateOutput {

	/*
	 * create HTML file containing all results for Truck Loading (Offline + Online
	 * Algorithms)
	 */
	public static String CreateHTMLFile(List<Item> inputList, Output resultOPT_FFD, Output resultOnline_FirstFit,
			Output resultOnline_BestFit, Output resultOnline_HarmonicFit,
			List<PerformanceComparison> comparisonResult) {
		String folderLocation = "";
		String html = GetHTMLString(inputList, resultOPT_FFD, resultOnline_FirstFit, resultOnline_BestFit,
				resultOnline_HarmonicFit, comparisonResult);
		Helper.CreateHTMLFile(folderLocation, html);
		return folderLocation;
	}

	/* Private Methods */
	private static String GetHTMLString(List<Item> itemList, Output resultOPT_FFD, Output resultOnline_FirstFit,
			Output resultOnline_BestFit, Output resultOnline_HarmonicFit,
			List<PerformanceComparison> comparisonResult) {
		String finalString = CreateHeaderHtml();
		finalString = CreateItemHtml(itemList, finalString);
		finalString = CreateOPT_FFDHtml(resultOPT_FFD, finalString);
		finalString = CreateOnline_FirstFitHtml(resultOnline_FirstFit, finalString);
		finalString = CreateOnline_BestFitHtml(resultOnline_BestFit, finalString);
		finalString = CreateOnline_HarmonicFitHtml(resultOnline_HarmonicFit, finalString);
		finalString = CreateComparisonResultHtml(comparisonResult, finalString);
		finalString = CreateFooterHtml(finalString);
		return finalString;
	}

	private static String CreateHeaderHtml() {
		return "finalString";
	}

	private static String CreateItemHtml(List<Item> inputList, String finalString) {
		return finalString;
	}

	private static String CreateOPT_FFDHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateOnline_FirstFitHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateOnline_BestFitHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateOnline_HarmonicFitHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateComparisonResultHtml(List<PerformanceComparison> comparisonResult, String finalString) {
		return finalString;
	}

	private static String CreateFooterHtml(String finalString) {
		return finalString;
	}
}
