package Results;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import DataObjects.Constants;
import DataObjects.HarmonicClass;
import DataObjects.HtmlStrings;
import DataObjects.Item;
import DataObjects.Output;
import DataObjects.Truck;

import Utility.ItemOperations;
import Utility.TruckOperations;

/*Create  Algorithm Output HTML Files*/
public class CreateAlgorithmOutputHtmlFile 
{
	static final String templatePath = Constants.HTMLOutputTemplateFilePath;

	/* Public Methods */
	
	/* create output HTML file for all algorithm results for Truck Loading Online Algorithms */
	public static String Create(Output resultOnline_FirstFit,
			                    Output resultOnline_BestFit, 
							    Output resultOnline_HarmonicFit)
		throws IOException 
	{
		String algorithmApproach = resultOnline_FirstFit.getAlgorithmApproach();
		String outputFilePath = IntitalizeFileOutput(algorithmApproach);
		String finalHtml = "";

		finalHtml += HtmlStrings.AssumptionsHeading;
		finalHtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<table class=\"blueTable\"" + ">";
		List<Item> inputItemList = ItemOperations.GetInpuItemList();
		finalHtml += "<tr><td>" + 
		             HtmlStringConversions.GetItemHtml(inputItemList, HtmlStrings.InputItemListHeading)+
				     "</td></tr></table>";
		
		boolean isNoResultAvailable = CheckIfAnyResultsExist( resultOnline_FirstFit, resultOnline_BestFit,
				resultOnline_HarmonicFit);
		if (isNoResultAvailable) 
		{
			if (inputItemList != null && inputItemList.size() > 0)
				finalHtml += HtmlStrings.NoResultsAvailableHtmlMessage;
		} 
		else 
		{
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(algorithmApproach,resultOnline_FirstFit, Constants.FirstFitAlgoName,
					null) + "<br /><br /><br />";
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(algorithmApproach,resultOnline_BestFit, Constants.BestFitAlgoName,
					null);
			List<HarmonicClass> harmonicClasses = TruckOperations.GetHarmonicClassesList();
			finalHtml += HtmlStringConversions.GetInputAndResultHtml(algorithmApproach,resultOnline_HarmonicFit,
					Constants.HarmonicFitAlgoName, harmonicClasses) + "<br />";
		}

		EmbedFinalResultsInOutputFile(finalHtml, outputFilePath);
		return outputFilePath;
	}

	/* Private Methods */

	/* initialize output HTML file with HTML file template CSS */
	private static String IntitalizeFileOutput(String algorithmApproach) throws IOException 
	{
		String html = new String(Files.readAllBytes(Paths.get(templatePath)));
		String outputFilePath = "" ;
		if (algorithmApproach == Constants.ExistingApproach)
			outputFilePath = Constants.OutputFilePathForExistingApproach;
		else if (algorithmApproach == Constants.ProposedApproach1)
			outputFilePath = Constants.OutputFilePathForProposedApproach1;
		else if (algorithmApproach == Constants.ProposedApproach2)
			outputFilePath = Constants.OutputFilePathForProposedApproach2;
		else if (algorithmApproach == Constants.ProposedApproach3)
			outputFilePath = Constants.OutputFilePathForProposedApproach3;
		
		PrintWriter out = new PrintWriter(outputFilePath);
		// clear output file contents
		out.print("");
		out.close();
		// copy template design to results file
		out = new PrintWriter(outputFilePath);
		out.print(html);
		out.close();
		
		return outputFilePath;
	}

	/* embed final results into the output HTML file */
	private static void EmbedFinalResultsInOutputFile(String finalHtml, String outputFilePath) 
			      throws IOException 
	{
		String fileHtml = new String(Files.readAllBytes(Paths.get(outputFilePath)));
		fileHtml = fileHtml.replace("This is the Output", finalHtml);
		PrintWriter out = new PrintWriter(outputFilePath);
		out.print(fileHtml);
		out.close();
	}

	// Check all algorithm results availability */
	private static boolean CheckIfAnyResultsExist(Output resultOnline_FirstFit,
												  Output resultOnline_BestFit, 
												  Output resultOnline_HarmonicFit) 
	{
		if (!CheckIfAlgoResultExist(resultOnline_BestFit)
				|| !CheckIfAlgoResultExist(resultOnline_FirstFit) || !CheckIfAlgoResultExist(resultOnline_FirstFit))
			return false;
		return true;
	}

	/* checkIf particular algorithm result available */
	private static boolean CheckIfAlgoResultExist(Output result) 
	{
		boolean isNoResultAvailable = true;
		List<Truck> closedTrucks = result.getClosedTrucks();

		if (closedTrucks != null && closedTrucks.size() > 0)
			isNoResultAvailable = false;
		return isNoResultAvailable;
	}
}
