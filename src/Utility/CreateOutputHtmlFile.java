package Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import DataObjects.Constants;
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

		try {
			IntitalizeFileOutput();
			String finalHtml = "";
			finalHtml = CreateInputItemHtml(itemList, truckCapacity, numberOfOpenBinsThreshold, finalHtml);
			finalHtml = CreateOfflineAlgorithmResultHtml(sorteditemList, resultOPT_FFD, finalHtml,
					Constants.OPTAlgoName);
			finalHtml = CreateOnlineAlgorithmResultHtml(resultOnline_FirstFit, finalHtml, Constants.FirstFitAlgoName);
			finalHtml = CreateOnlineAlgorithmResultHtml(resultOnline_BestFit, finalHtml, Constants.BestFitAlgoName);
			finalHtml = CreateOnlineAlgorithmResultHtml(resultOnline_HarmonicFit, finalHtml,
					Constants.HarmonicFitAlgoName);
			finalHtml = CreateComparisonResultHtml(comparisonResult, finalHtml);
			EmbedFinalResultsInOutputFile(finalHtml);
			System.out.println("Success in creating Output file at location : " + outputFilePath);
		} catch (Exception e) {
			System.out.println("Error in creating html file : " + e.getMessage());
		}
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

	// embed final results into the output html file
	private static void EmbedFinalResultsInOutputFile(String finalHtml) throws IOException {
		String html = new String(Files.readAllBytes(Paths.get(outputFilePath)));
		html = html.replace("This is the Output", finalHtml);
		PrintWriter out = new PrintWriter(outputFilePath);
		out.print(html);
		out.close();
	}

	// get input list html string
	private static String CreateInputItemHtml(List<Item> itemList, int truckCapacity, int numberOfOpenBinsThreshold,
			String htmlString) {
		String headingTag = "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2> Given: </b></h2></span>"
				+ "<span " + "style=\"color:DarkGreen	;\"" + ">" + "Each Truck Capacity is of Capacity : "
				+ truckCapacity + "<br />" + "Maximum Threshold for Number of Open trucks allowed at any instance : "
				+ numberOfOpenBinsThreshold + "" + "</span><br /> <br />";
		String itemTableString = Helper.GetItemTableHtmlString(itemList, false, false);
		return htmlString + "<br />" + headingTag + itemTableString;
	}

	// get offline algorithm results html string
	private static String CreateOfflineAlgorithmResultHtml(List<Item> sorteditemList, Output resultOffline,
			String htmlString, String algorithmName) {
		String optHeadingTag = "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2>" + "Results : "
				+ algorithmName + "</b></h2></span>";
		String sortedItemListTableHtmlString = Helper.GetItemTableHtmlString(sorteditemList, true, false);
		List<Truck> openTrucks = resultOffline.getOpenTrucks();
		String openTrucksTableHtmlString = Helper.GetTruckTableHTMLString(openTrucks, "Open");
		return htmlString + "<br />" + optHeadingTag + sortedItemListTableHtmlString + openTrucksTableHtmlString;
	}

	// get online algorithm results html string
	private static String CreateOnlineAlgorithmResultHtml(Output resultOnline, String htmlString,
			String algorithmName) {
		String optHeadingTag = "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2>" + "Results : "
				+ algorithmName + "</b></h2></span>";
		List<Truck> openTrucks = resultOnline.getOpenTrucks();
		String openTrucksTableHtmlString = Helper.GetTruckTableHTMLString(openTrucks, "Open");
		return htmlString + "<br />" + optHeadingTag + openTrucksTableHtmlString;
	}

	private static String CreateComparisonResultHtml(List<PerformanceComparison> comparisonResult, String htmlString) {
		return htmlString;
	}
}
