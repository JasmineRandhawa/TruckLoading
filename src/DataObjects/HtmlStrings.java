package DataObjects;

//All the constant html strings used in output html file values are initialized here
public class HtmlStrings {
	
	//item List headings
	public static final String InputItemListHeading = "Input Item Sequence";
	public static final String SortedItemListHeading = "Sorted Item List Sequence";
	public static final String HarmonicClassesHeading = "Harmonic Classes for K = " + Constants.OpenTrucksThreshold;
	public static final String PerformanceResultsHeading = "Performance Results:";
	public static final String UnProcessedItemListHeading = "UnProcessed Items";
	public static final String AssumptionsHeading =  "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2> "+
										              "Given: </b></h2></span>"+
													  "<span " + "style=\"color:DarkGreen	;\"" + ">" + 
													  "Each Truck Capacity is of Capacity : " + 
													   Constants.TruckCapacity + "<br />" + 
													  "Maximum Threshold for Number of Open trucks allowed at any instance : " +
													   Constants.OpenTrucksThreshold + "" + "</span><br /> <br />";
	
	//error/warning
	public static final String NoResultsAvailableHtmlMessage =  "<span " + "style=\"color:DarkRed	;\"" + ">" + "<b><h2>" + 
														     	"No Results Available" + "</b></h2>" +
														    "</span><br />";
	public static final String NoDataHtmlMessage = "<span " + "style=\"color:DarkRed	;\"" + ">" + "<b><h2>" + 
									 				"No data" + "</b></h2>" +
									 			"</span><br />";
}
