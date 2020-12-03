package Results;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Algorithms.ExistingAlgorithm;
import Algorithms.ProposedAlgorithm;

import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Item;

// compute and display results of Truck Loading Online Algorithms using different algorithm approaches
public class Results 
{
	static List<Output> output  = new ArrayList<Output>();
	
	/* Public Methods */
	
	/* call all online algorithms one by one and store algorithm output and performance results */
	public static void InvokeOnlineAlgorithms(List<Item> inputItems) throws IOException
	{
		try
		{		
			output  = new ArrayList<Output>();
			
			//get results using existing online algorithm approach
			Output resultOnline_FirstFit = ExistingAlgorithm.GetResults(inputItems, 
																	    Constants.FirstFitAlgoName,
																		Constants.ExistingApproach);
			Output resultOnline_BestFit = ExistingAlgorithm.GetResults(inputItems, 
																	    Constants.BestFitAlgoName,
																		Constants.ExistingApproach);
			Output resultOnline_HarmonicFit = ExistingAlgorithm.GetResults(inputItems, 
																	    Constants.HarmonicFitAlgoName,
																		Constants.ExistingApproach);
			output.add(resultOnline_FirstFit);
			output.add(resultOnline_BestFit);
			output.add(resultOnline_HarmonicFit);
			String outputFilePath =CreateAlgorithmOutputHtmlFile.Create(resultOnline_FirstFit,
	                							                        resultOnline_BestFit, 
	                							                        resultOnline_HarmonicFit);
			System.out.println("Output file for "+ Constants.ExistingApproach + " created at location : " + outputFilePath);
			
			
			//execute each online algorithm of all risk metric approaches 
			String[] algorithmRiskMetricApproaches = { Constants.ProposedApproach1,
														Constants.ProposedApproach2,
														Constants.ProposedApproach3
													 };
			for (String approach : algorithmRiskMetricApproaches)
			{
				//get results using risk metric 1 approach
				resultOnline_FirstFit = ProposedAlgorithm.GetResults(inputItems, Constants.FirstFitAlgoName,approach);
				resultOnline_BestFit = ProposedAlgorithm.GetResults(inputItems, Constants.BestFitAlgoName,approach);
				resultOnline_HarmonicFit = ProposedAlgorithm.GetResults(inputItems, Constants.HarmonicFitAlgoName,approach);
				outputFilePath = CreateAlgorithmOutputHtmlFile.Create(resultOnline_FirstFit,
													                  resultOnline_BestFit, 
													                  resultOnline_HarmonicFit);
				output.add(resultOnline_FirstFit);
				output.add(resultOnline_BestFit);
				output.add(resultOnline_HarmonicFit);
				System.out.println("Output file for "+ approach + " created at location : " + 
				                    outputFilePath);
			}
		}
		catch (IOException ex)
		{
			System.out.println("Error while creating Algorithm  output HTML file for  :" 
		                        + ex.getMessage());
		}
	}
	
	/*  get performance comparison results for all algorithms under all approaches */
	public static List<Output> GetComparisonResults() 
	{
		return output;
	}
}
