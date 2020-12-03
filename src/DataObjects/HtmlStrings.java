package DataObjects;

/* All the constant HTML strings used in output HTML file values are initialized here */ 
public class HtmlStrings 
{
	// table headings for item and truck data
	public static final String InputItemListHeading = "Input Item Sequence";
	public static final String HarmonicClassesHeading = "Harmonic Classes for K = " + Constants.OpenTrucksThreshold;
	public static final String PerformanceResultsHeading = "Performance Comparison:";
	public static final String AssumptionsHeading = "<center>"
			+ "												<span " + "style=\"color:ForestGreen	;\"" + ">" + 
																"<b><h2> "+
																	"Given: "+
																"</b></h2>"+
															"</span>" + 
															"<span " + "style=\"color:DarkGreen	;\"" + ">"+
																"Each Truck Capacity is of Capacity : " + 
																	Constants.TruckCapacity + "<br />" +
																"Maximum Threshold for Number of Open trucks"+
																	" allowed at any instance : " + 
																	Constants.OpenTrucksThreshold+
															"</span>"+
														"</center><br /> <br />";

	//  warnings messages for no data or output results
	public static final String NoResultsAvailableHtmlMessage = "<span " + "style=\"color:DarkRed	;\"" + ">" +
																	"<b><h2>" + 
																		"No Results Available" + 
																	"</b></h2>" + 
																"</span><br />";
	public static final String NoDataHtmlMessage = "<span " + "style=\"color:DarkRed	;\"" + ">" + 
														"<b><h2>"+
															"No data" + 
														"</b></h2>" +
													"</span><br />";
}
