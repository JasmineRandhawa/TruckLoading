package DataObjects;

/* All the constants values are initialized here */
public class Constants 
{
	// Assumptions
	public static final int OpenTrucksThreshold = 2;
	public static final double TruckCapacity = 1.0;

	// Algorithm Names
	public static final String FirstFitAlgoName = "Online First Fit Algorithm";
	public static final String HarmonicFitAlgoName = "Online Harmonic Fit Algorithm";
	public static final String BestFitAlgoName = "Online Best Fit Algorithm";
	
	//Type of Algorithm sApproaches
	public static final String ExistingApproach =   "Existing Algo";
	public static final String ProposedApproach1 =  "Proposed Algo Risk Metric 1";
	public static final String ProposedApproach2 =  "Proposed Algo Risk Metric 2";
	public static final String ProposedApproach3 =  "Proposed Algo Risk Metric 3";

	// Input File paths
	public static final String TinyInputDataFilePath = "src/InputFile/TruckLoadingInputTiny.txt";
	public static final String SmallInputDataFilePath = "src/InputFile/TruckLoadingInputSmall.txt";
	public static final String MediumInputDataFilePath = "src/InputFile/TruckLoadingInputMedium.txt";
	public static final String LargeInputDataFilePath = "src/InputFile/TruckLoadingInputLarge.txt";
	
	// Output File paths
	public static final String OutputFilePathForExistingApproach = "src/OutputFile/ExistingApproachResults.html";
	public static final String OutputFilePathForProposedApproach1 = "src/OutputFile/ProposedApproach1Results.html";
	public static final String OutputFilePathForProposedApproach2 = "src/OutputFile/ProposedApproach2Results.html";
	public static final String OutputFilePathForProposedApproach3 = "src/OutputFile/ProposedApproach3Results.html";
	public static final String OutputFilePathForComparisonResults = "src/OutputFile/PerformanceComparisonResults.html";
	public static final String HTMLOutputTemplateFilePath = "src/OutputFile/Template.html";

	//Truck Closing Reason Messages
	public static final String FullTruckCloseMessage = "Closing Fully Filled Truck";
	public static final String RemainingOpenTrucksCloseMessage = "Closing Last Remaining <br/ >Open truck";
	public static final String ThresholdCloseMessage = "Closing as per Threshold logic";
	public static final String OptThresholdCloseMessage = "Closing Truck With<br /> Opt Max Profit";
	public static final String OptNoFutureItemFoundCloseMessage = "Closing as no more future item<br /> within deadline fits";
	public static final String RiskExceedingOneCloseMessage = "Closing as risk > 1<br />";
	public static final String ThresholddMaxRiskCloseMessage = "Threshold Reached <br/> Closing one with max risk<br />";
}
