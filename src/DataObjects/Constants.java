package DataObjects;

//All the constants values are initialized here
public class Constants {
	
	// Assumptions
	public static final int OpenTrucksThreshold = 2;
	public static final int TruckCapacity = 100;
	
	//Algorithm Names
	public static final String OPTAlgoName = "Offline OPT Algorithm";
	public static final String FirstFitAlgoName = "Online First Fit Algorithm";
	public static final String HarmonicFitAlgoName = "Online Harmonic Fit Algorithm";
	public static final String BestFitAlgoName = "Online Best Fit Algorithm";
	
	//File paths
	public static final String DataFilePath = "src/InputFile/WorstCaseInput.txt";
	public static final String OutputFilePath = "src/OutputFile/Results.html";
	public static final String HTMLOutputTemplateFilePath = "src/OutputFile/Template.html";	
	
	//console messages
	public static final String SuccessMessage = "Success in creating Output file at location : ";
	public static final String ErrorMessage = "Error in creating html file : " ;
}
