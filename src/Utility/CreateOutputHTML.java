package Utility;

import DataObjects.Input;
import DataObjects.Output;
import DataObjects.PerformanceComparison;

import java.util.List;

public class CreateOutputHTML {

	public static String GetHTMLString(List<Input> inputList, Output resultOPT, List<Output> resultOnline,
			List<PerformanceComparison> comparisonResult) {
		String finalString = CreateHeaderHtml();
		finalString = CreateInputHtml(inputList, finalString);
		finalString = CreateOPTHtml(resultOPT, finalString);
		finalString = CreateOnlineHtml(resultOPT, finalString);
		finalString = CreateComparisonResultHtml(comparisonResult, finalString);
		finalString = CreateFooterHtml(finalString);
		return finalString;
	}

	private static String CreateFooterHtml(String finalString) {
		return finalString;
	}

	private static String CreateComparisonResultHtml(List<PerformanceComparison> comparisonResult, String finalString) {
		return finalString;
	}

	private static String CreateOnlineHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateOPTHtml(Output resultOPT, String finalString) {
		return finalString;
	}

	private static String CreateInputHtml(List<Input> inputList, String finalString) {
		return finalString;
	}

	private static String CreateHeaderHtml() {
		return "finalString";
	}

}
