package Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import DataObjects.Constants;
import DataObjects.HtmlStrings;
import DataObjects.Item;
import DataObjects.Output;
import DataObjects.PerformanceComparison;
import DataObjects.Truck;

/*Create Output HTML Files*/
public class CreateOutputHtmlFile {

	static String outputFilePath = Constants.OutputFilePath;
	static String templatePath = Constants.HTMLOutputTemplateFilePath;

	/*
	 * create HTML file containing all results for Truck Loading (Offline + Online
	 * Algorithms)
	 */
	public static void CreateHTMLFile(int truckCapacity, int numberOfOpenBinsThreshold, List<Item> itemList,
			List<Item> sorteditemList, Output resultOPT_FFD, Output resultOnline_FirstFit, Output resultOnline_BestFit,
			Output resultOnline_HarmonicFit, List<PerformanceComparison> comparisonResult) throws IOException {
		IntitalizeFileOutput();
		String finalHtml = "";
		boolean isNoResultAvailable = CheckIfAnyResultsExist(resultOPT_FFD, resultOnline_FirstFit, resultOnline_BestFit,
				resultOnline_HarmonicFit);
		finalHtml += HtmlStrings.TruckCapacityHeading + truckCapacity + "<br />" + HtmlStrings.ThresholdHeading
				+ numberOfOpenBinsThreshold + "" + "</span><br /> <br />";

		if (isNoResultAvailable) {
			if (itemList != null && itemList.size() > 0)
				finalHtml += HtmlStringConversions.GetItemHtml(itemList, HtmlStrings.InputItemListHeading);
			finalHtml += HtmlStrings.NoResultsAvailableHtmlMessage;
		} else {
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(itemList, sorteditemList, resultOPT_FFD,
					Constants.OPTAlgoName);
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(itemList, null, resultOnline_FirstFit,
					Constants.FirstFitAlgoName) +"<br /><br /><br />";
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(itemList, null, resultOnline_BestFit,
					Constants.BestFitAlgoName);
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(itemList, null, resultOnline_HarmonicFit,
					Constants.HarmonicFitAlgoName);
			finalHtml += HtmlStringConversions.CreateComparisonResultHtml(comparisonResult);
		}
		EmbedFinalResultsInOutputFile(finalHtml);
	}

	/* Private Methods */
	
	// initialize output HTML file with HTML file template CSS
	private static void IntitalizeFileOutput() throws IOException {
		String html = new String(Files.readAllBytes(Paths.get(templatePath)));
		PrintWriter out = new PrintWriter(outputFilePath);
		// clear output file contents
		out.print("");
		out.close();
		// copy template design to results file
		out = new PrintWriter(outputFilePath);
		out.print(html);
		out.close();
	}

	// embed final results into the output HTML file
	private static void EmbedFinalResultsInOutputFile(String finalHtml) throws IOException {
		String html = new String(Files.readAllBytes(Paths.get(outputFilePath)));
		html = html.replace("This is the Output", finalHtml);
		PrintWriter out = new PrintWriter(outputFilePath);
		out.print(html);
		out.close();
	}
	
	// Check all algorithm results availability
	private static boolean CheckIfAnyResultsExist(Output resultOPT_FFD, Output resultOnline_FirstFit,
			Output resultOnline_BestFit, Output resultOnline_HarmonicFit) {
		if (!CheckIfAlgoResultExist(resultOPT_FFD) || !CheckIfAlgoResultExist(resultOnline_BestFit)
				|| !CheckIfAlgoResultExist(resultOnline_FirstFit) || !CheckIfAlgoResultExist(resultOnline_FirstFit))
			return false;
		return true;
	}

	// checkIf particular algorithm result available
	private static boolean CheckIfAlgoResultExist(Output result) {
		boolean isNoResultAvailable = true;
		List<Truck> openTrucks = result.getOpenTrucks();
		List<Truck> closedTrucks = result.getClosedTrucks();

		if ((openTrucks != null && openTrucks.size() > 0) || (closedTrucks != null && closedTrucks.size() > 0))
			isNoResultAvailable = false;
		return isNoResultAvailable;
	}
}
