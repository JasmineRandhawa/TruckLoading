package Results;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import DataObjects.Constants;
import DataObjects.HtmlStrings;
import DataObjects.Output;

/*Create Output HTML Files for performance comparison results*/
public class CreateComparisonResultsHtmlFile 
{
	/* Public Methods */
	
	/*  create HTML file for Performance Comparison results */
	public static void Create(List<Output> comparisonResults)
			throws IOException 
	{
		try
		{
			String outputFilePath = IntitalizeFileOutput();
			String finalHtml = HtmlStrings.AssumptionsHeading;
			finalHtml += HtmlStringConversions.CreateComparisonResultHtml(comparisonResults);
			EmbedFinalResultsInOutputFile(finalHtml, outputFilePath);
			System.out.println("Output file for Comparison Results created at location : " + 
			                    Constants.OutputFilePathForComparisonResults);
		}
		catch (IOException ex)
		{
			System.out.println("Error while creating Comparison results HTML file :" 
		                        + ex.getMessage());
		}
	}

	/* Private Methods */

	/*  initialize output HTML file with HTML file template CSS */
	private static String IntitalizeFileOutput() throws IOException 
	{
		String fileHtml = new String(Files.readAllBytes(Paths.get(Constants.HTMLOutputTemplateFilePath)));
		String outputFilePath= Constants.OutputFilePathForComparisonResults;
		
		PrintWriter out = new PrintWriter(outputFilePath);
		// clear output file contents
		out.print("");
		out.close();
		// copy template design to results file
		out = new PrintWriter(outputFilePath);
		out.print(fileHtml);
		out.close();
		
		return outputFilePath;
	}

	/*  embed final results into the output HTML file */
	private static void EmbedFinalResultsInOutputFile(String finalHtml, String outputFilePath) 
			       throws IOException 
	{
		String fileHtml = new String(Files.readAllBytes(Paths.get(outputFilePath)));
		fileHtml = fileHtml.replace("This is the Output", finalHtml);
		PrintWriter out = new PrintWriter(outputFilePath);
		out.print(fileHtml);
		out.close();
	}
}
