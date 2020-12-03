import java.io.IOException;
import java.util.List;

import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;

import Results.Results;
import Results.CreateComparisonResultsHtmlFile;

import Utility.ItemOperations;

// Main.Java to compute and display results of Truck Loading  Algorithms
public class Main 
{

	public static void main(String[] args) throws IOException 
	{
		try 
		{
			/* uncomment below function to mock deadline data by first copying item size data from source file, 
			   then mocking deadline data by random numbers and 
			   and writing the (size, deadline) back to source file */
			//MockInputData.MockInputItemDeadlineData(Constants.TinyInputDataFilePath);
			
			// input data file path
			String dataFilePath = Constants.LargeInputDataFilePath;

			// load item data from file into a list
			List<Item> inputItems = ItemOperations.LoadItemsIntoListFromFile(dataFilePath);

			// Invoke online algorithms for all approaches and create HTMl files for
			// algorithm output
			Results.InvokeOnlineAlgorithms(inputItems);

			// Get comparison results for all algorithm approaches
			List<Output> comparisonResults = Results.GetComparisonResults();

			// create HTML file for display comparison results
			CreateComparisonResultsHtmlFile.Create(comparisonResults);

		} 
		catch (Exception ex) 
		{
			System.out.println("Error While processing: " + ex.getMessage());
		}
	}

}
