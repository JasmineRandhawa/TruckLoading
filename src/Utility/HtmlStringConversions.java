package Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.Constants;
import DataObjects.HarmonicClass;
import DataObjects.HtmlStrings;
import DataObjects.Item;
import DataObjects.Output;
import DataObjects.PerformanceComparison;
import DataObjects.Truck;

// Helper Methods
public class HtmlStringConversions {

	public static String CreateComparisonResultHtml(List<PerformanceComparison> comparisonResult) {
		String tableHtmlString ="";
		if (comparisonResult != null && comparisonResult.size() > 0) 
		{
			
			tableHtmlString += "<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2>" + "Results : "
									+ HtmlStrings.PerformanceResultsHeading + "</b></h2>" +
							"</span>" ;
		
			for (PerformanceComparison perf : comparisonResult) {
				tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
											"<tr>"+
												 "<td>" +
												 "<b><span " + "style=\"color:Chocolate	;\"" + ">" + 
										 	   		perf.getAlgorthmName() +
										 	   	"</b></span>"+ 
												"</td>" +
											"</tr>" +
											"<tr>" +
												 "<td>" +
												     "<center>" + "<b>Total Input Size : </b> " + perf.getTotalInputSize() +
												      "</center>" +
												 "</td>" +
											 "</tr>" +
											 "<tr>" +
												"<td>" +
													 "<center>" + "<b>Delivered Items Count : </b> " + perf.getNoOfItemsDelivered() +
													  "</center>" +
												"</td>" +
											"</tr>" +
											"<tr>" +
												 "<td>" +
												     "<center>" + "<b>Delivered Items Size : </b> " + 
												     		perf.getTotalSizeOfDeliveredItems() +
												     	 "</center>" +
												 "</td>" +
											"</tr>" +
											 "<tr>" +
												 "<td>" +
												     "<center>" + "<b>Total Wastage : </b> " +
												     	perf.getTotalWastage() + "</center>" +
												 "</td>" +
											 "</tr>" +
										     "<tr>" +
											 "<td>" +
											     "<center>" + "<b>Competitive Ratio : </b> " +
											     	perf.getCompetitiveRatio() + "</center>" +
											 "</td>" +
									     "</tr>" +
									"</table>";
			}
			tableHtmlString+= "<br /><br />";
		}
		else
		{
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;;
		}
		return tableHtmlString;
	}
	
	private static String GetHarmonicClassesHtml(List<HarmonicClass> harmonicClasses) {
		String tableHtmlString ="";
		if (harmonicClasses != null && harmonicClasses.size() > 0) 
		{
			
			tableHtmlString += "<span " + "style=\"color:Chocolate	;\"" + ">"+
								         " <b>"+  HtmlStrings.HarmonicClassesHeading +"</b>" +
								       "</span><br />";

			Collections.sort(harmonicClasses , HarmonicClass.HarmonicClassIdIdComparator);
			
			for (HarmonicClass harmonicClass : harmonicClasses) {
				tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
												 "<tr>"+
												 	"<td>" +
													     "<b><center>" + "Class " + harmonicClass.getClassId() + "</center></b>" +
													 "</td>" +
												 "</tr>" +
												 "<tr>" +
													 "<td>" +
													     "<center>" + "<b>Item Size Range : </b> " + harmonicClass.getMinClassSize() +
													     	"<b> - </b>" + harmonicClass.getMaxClassSize() + "</center>" +
													 "</td>" +
												 "</tr>" +
											"</table>";
			}
			tableHtmlString+= "<br /><br />";
		}
		else
		{
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;;
		}
		return tableHtmlString;
	}
	
	//general function for items rows hrml string
	public static String GetItemHtml(List<Item> itemList, String heading ) {
		
		String tableHtmlString ="";
		if (itemList != null && itemList.size() > 0) 
		{
			
			tableHtmlString += "<span " + "style=\"color:Chocolate	;\"" + ">"+
								         " <b>"+ heading +"</b>" +
								       "</span><br />";

			int itemIndex = 0;
			for (Item item : itemList) {
				String arrowHtml ="";
				if(itemIndex!=0 && heading != HtmlStrings.UnProcessedItemListHeading)
				{
					arrowHtml =   "<table class=" + "\"" + "blueTable" + "\"" + ">";
					for (int i =0; i < 5 ;i++)
						arrowHtml += "<tr><td></td></tr>" ;
					arrowHtml += "<tr><td><b>-----></b></td></tr>" ;
						
					for (int i =0; i < 5 ;i++)
						arrowHtml += "<tr><td></td></tr>" ;
					arrowHtml+= "</table>";
				}
				else
					arrowHtml = "&nbsp;&nbsp;&nbsp;";
				tableHtmlString += arrowHtml + "<table class=" + "\"" + "blueTable" + "\"" + ">"+
												 "<tr>"+
												 	"<td>" +
													     "<b><center>" + "Item " + item.getItemId() + "</center></b>" +
													 "</td>" +
												 "</tr>" +
												 "<tr>" +
													 "<td>" +
													     "<center>" + item.getItemSize() + "</center>" +
													 "</td>" +
												 "</tr>" +
												 "<tr>" +
													 "<td>" +
													     "<center>" + item.getDeliveryDeadline() + " day"
															+ (item.getDeliveryDeadline() > 1 ? "s" : "") + "</center>" +
													 "</td>" +
												 "</tr>" +
											"</table>";
				itemIndex++;
			}
			tableHtmlString+= "<br /><br />";
		}
		else
		{
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;;
		}
		return tableHtmlString;
	}

	// general function for getting html string for trucks data
	public static String GetInputAndResultHtml(  List<Item> inputItemList, List<Item> sortedItemList, 
												 Output result, String algorithmName ,List<HarmonicClass>  harmonicClasses) {
		
		String tableHtmlString = "";
		if (inputItemList != null && inputItemList.size() > 0) 
		{
				tableHtmlString = "<table class=\"blueTable\""+">" +
										"<tr>"+
											"<td>"+
												"<span " + "style=\"color:ForestGreen	;\"" + ">" + "<b><h2>" + "Results : "
														+ algorithmName + "</b></h2>" +
												 "</span>"+
											 "</td>" +
										 "</tr>";
				
				if(inputItemList!=null && inputItemList.size() > 0) 
					tableHtmlString += "<tr><td>" + 
											GetItemHtml(inputItemList, HtmlStrings.InputItemListHeading) + 
									  "</td></tr>";
				
				if(algorithmName == Constants.OPTAlgoName && sortedItemList!=null && sortedItemList.size() > 0) 
					tableHtmlString += "<tr><td>" +  
										GetItemHtml(sortedItemList, HtmlStrings.SortedItemListHeading) +
									 "</td></tr>";
				
				if(algorithmName == Constants.HarmonicFitAlgoName && harmonicClasses!=null && harmonicClasses.size() > 0) 
					tableHtmlString += "<tr><td>" +  
										GetHarmonicClassesHtml(harmonicClasses) +
									 "</td></tr>";
				
				
				List<Truck> openTrucks = result.getOpenTrucks();
				List<Truck> closedTrucks = result.getClosedTrucks();
				List<Item> unprocessedItems = result.getUnprocessedItems();
				
				//run loop two times, one for open trucks and one for closed
				String truckType = "" ;
				for(int i = 0 ; i < 2 ; i++)
				{
					List<Truck> temp = new ArrayList<Truck>();
					if(i==0 && openTrucks != null && openTrucks.size() > 0)
					{
						truckType = "Open";
						temp = new ArrayList<Truck>();
						temp.addAll(openTrucks);
					}
					if(i==1 && closedTrucks != null && closedTrucks.size() > 0)
					{
						truckType = "Closed";
						temp = new ArrayList<Truck>();
						temp.addAll(closedTrucks);
					}
					if(temp != null && temp.size() > 0)
					{
						tableHtmlString +=  "<tr>"+
												"<td>"+
													"<b><span " + "style=\"color:Chocolate	;\"" + ">" + 
														 truckType + " Truck Items" +
													 "</b></span>"+
												"</td>" +
											 "</tr>" + "<tr><td>" ;
						
						if(algorithmName == Constants.HarmonicFitAlgoName)
							Collections.sort(temp, Truck.TruckHarmonicClassIdComparator);
						else
							Collections.sort(temp, Truck.TruckIdComparator);
						
						for (Truck truck : temp) {
							tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
													 "<tr>"+
													 	"<td >" +
														     "<h3><center>"+"Truck " + truck.getTruckId() +
														     (truck.getIsExpedited()?
																     "<b>*</b>":"") +
														     (truck.getHarmonicClassId()> 0?
														     (" : Class " + truck.getHarmonicClassId()):"") +
														     "</center></h3>" +
														 "</td>" +
													  "</tr>";
							List<Item> itemList = truck.getTruckItems();
							if (itemList != null && itemList.size() > 0) 
							{
								for (Item item : itemList) {
									tableHtmlString += "<tr>"+
															"<td >" +
																"<b>Item " + item.getItemId() +" : </b>"+ item.getItemSize() + " <b> , </b>"+  
																	 item.getDeliveryDeadline() +
															 "</td>" +
														"</tr>" ;
								}	
							}
							tableHtmlString += "</table> &nbsp;&nbsp;";
							}
						tableHtmlString += "</td></tr>" ;
						}
					}
				if(unprocessedItems!=null && unprocessedItems.size() > 0) 
					tableHtmlString += "<tr><td>" + GetItemHtml(unprocessedItems,  HtmlStrings.UnProcessedItemListHeading) + "</td></tr>";
				tableHtmlString += "</table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return tableHtmlString;
	}

}
