package DataObjects;

//All the constant html strings used in output html file values are initialized here
public class HtmlStrings {
	
	//item List headings
	public static final String ThresholdHeading = "Maximum Threshold for Number of Open trucks allowed at any instance : ";
	public static final String TruckCapacityHeading = "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2> "+
			                                          "Given: </b></h2></span>"+
			  										  "<span " + "style=\"color:DarkGreen	;\"" + ">" + 
			  										  "Each Truck Capacity is of Capacity : ";
	public static final String InputItemListHeading = "Input Item Sequence";
	public static final String SortedItemListHeading = "Sorted Item List Sequence";
	public static final String UnProcessedItemListHeading = "UnProcessed Items";
	
	//error/warning
	public static final String NoResultsAvailableHtmlMessage =  "<span " + "style=\"color:DarkRed	;\"" + ">" + "<b><h2>" + 
														     	"No Results Available" + "</b></h2>" +
														    "</span><br />";
	public static final String NoDataHtmlMessage = "<span " + "style=\"color:DarkRed	;\"" + ">" + "<b><h2>" + 
									 				"No data" + "</b></h2>" +
									 			"</span><br />";
}
