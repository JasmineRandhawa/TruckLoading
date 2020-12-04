package Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.Constants;
import DataObjects.HarmonicClass;
import DataObjects.HtmlStrings;
import DataObjects.Item;
import DataObjects.Output;
import DataObjects.Truck;

import Utility.ItemOperations;

/*  HTMl String Generation Methods */
public class HtmlStringConversions 
{
	/* Public Methods  */
	
	/* get HTML string for Items data  */
	public static String GetItemHtml(List<Item> itemList, String heading) 
	{
		
		String tableHtmlString ="";
		if (itemList != null && itemList.size() > 0) 
		{
			tableHtmlString += "<span " + "style=\"color:ForestGreen;\"" + ">" + 
									"<b><h2>" + 
										heading + 
									"</b></h2>" +
							   "</span>";

			int itemIndex = 0;
			for (Item item : itemList) 
			{
				String arrowHtml ="";
				if(itemIndex!=0)
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
													     "<b><center>" + "Item " +
													     	item.getItemId() + 
													     "</center></b>" +
													 "</td>" +
												 "</tr>" +
												 "<tr>" +
													 "<td>" +
													     "<center>" + 
													     	item.getItemSize() + 
													     	"<b> : </b>"+ item.getDeadline() + 
													     "</center>" +
													 "</td>" +
												 "</tr>" +
												 "<tr>" +
													 "<td>" +
													     "<center>" + "T = "+
													     	item.getItemId() + 
													     "</center>" +
													 "</td>" +
												 "</tr>" +
											"</table>";
				itemIndex++;
			}
			tableHtmlString+= "<br /><br />";
		}
		else
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;;
		return tableHtmlString;
	}

	
	/* get HTML string for trucks data  */
	public static String GetInputAndResultHtml( String algorithmApproach,Output result, 
			                                    String algorithmName ,
												List<HarmonicClass>  harmonicClasses) 
	{
		
		String tableHtmlString = "";
		List<Item> inputItemList = ItemOperations.GetInpuItemList();
		if (inputItemList != null && inputItemList.size() > 0) 
		{
			tableHtmlString = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
							  "<table class=\"blueTable\""+">" +
								   "<tr>"+
									   "<td>"+
											"<span " + "style=\"color:ForestGreen;\"" + ">" + 
												"<b><h2>" + "Results : "+
												   algorithmName + 
												"</b></h2>" +
											"</span>"+
									    "</td>" +
									"</tr>";
				
		if(algorithmName == Constants.HarmonicFitAlgoName && harmonicClasses!=null && harmonicClasses.size() > 0) 
			tableHtmlString += "<tr><td>" +  
									 GetHarmonicClassesHtml(harmonicClasses) +
								"</td></tr>";
				

			List<Truck> closedTrucks = result.getClosedTrucks();
				
			if(closedTrucks != null && closedTrucks.size() > 0)
			{
				tableHtmlString +=  "<tr>"+
										"<td>"+
											"<b><span " + "style=\"color:Chocolate;\"" + ">" + 
												 " Closed Trucks" +
												 "</b></span>"+
										"</td>" +
									 "</tr>" + "<tr><td>" ;
						
				Collections.sort(closedTrucks,Truck.TruckIdComparator);
				for (Truck truck : closedTrucks) 
				{
					tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
											"<tr>"+
												"<td >" +
													"<h3><center>"+"Truck " + 
														  truck.getTruckId() +
														  (truck.getHarmonicClassId()> 0?
															(" : Class " + 
															    truck.getHarmonicClassId()):""
													        ) +
													"</center></h3>" +
												"</td>" +
											"</tr>"+
											((truck.getHarmonicClassId()>0)?
											("<tr>"+
													"<td >" +
														"<h3><center>"+
															"Class " +
															truck.getHarmonicClassId() +
														 "</center></h3>" +
													"</td>" +
											"</tr>"):"")+
											"<tr>"+
												"<td>" +
													"<center>"+
													     "Opening Time T <b>:</b>" + 
													     truck.getOpeningTime()+
													     "<br/>" +
													     "Closing Time T <b>:</b>" + 
													     truck.getClosingTime()+
													"</center>" +
												"</td>" +
											"</tr>"+
											((algorithmApproach != Constants.ExistingApproach &&  
											  truck.getClosingReason()!=Constants.FullTruckCloseMessage)?
											("<tr>"+
												"<td >" +
													"<center>"+
														"Gain = " + truck.getGain() +
														"&nbsp; &nbsp;&nbsp;&nbsp;Loss= " + 
														truck.getLoss() +
														"<br />Loss/Gain= " + 
														truck.getLossGainRatio() +
													"</center>" +
												"</td>" +
											"</tr>"):"")+
											"<tr>"+
												"<td >" +
													"<center>"+"Profit " +
														truck.getProfit() +
														"&nbsp;&nbsp;&nbsp;&nbsp;"+"Waste "+ 
														 truck.getWaste() +
													"</center>" +
												"</td>" +
											"</tr>"+
											"<tr>"+
												 "<td >" +
													 "<center>"+"Total Profit so far<b> : </b>" +
														 truck.getTotalProfitIncurredAtTimeT() +
														 "<br />"+"Total Waste so far<b> : </b>"+ 
														 truck.getTotalWasteIncurredAtTimeT() +
													  "</center>" +
												"</td>" +
											"</tr>"+
											"<tr>"+
												"<td >" +
												   "<center>"+ 
													    truck.getClosingReason()+
													 "</center>" +
												"</td>" +
											"</tr>";
					List<Item> itemList = truck.getTruckItems();
					if (itemList != null && itemList.size() > 0) 
					{
						for (Item item : itemList) 
						{
							tableHtmlString += "<tr>"+
													"<td >" +
														"<b>Item " + 
														 item.getItemId() +
														 " : </b>"+ 
														 item.getItemSize() + 
														 " <b> , </b>"+ 
														item.getDeadline() + 
														"<b> , </b>"+
														"T"+"<b>: </b>" + 
														item.getItemId()+ 
														"</td>" +
												"</tr>" ;
						}	
					}
					tableHtmlString += "</table> &nbsp;&nbsp;";
				}
			tableHtmlString += "</td></tr>" ;
		}
			tableHtmlString += "</table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return tableHtmlString;
	}
		
	/* get HTML String for performance comparison results class list  */
	public static String CreateComparisonResultHtml(List<Output> comparisonResults) 
	{
		List<String> algorithmApproaches = new ArrayList<String>();
		String tableHtmlString = "";
		if (comparisonResults != null && comparisonResults.size() > 0) 
		{
			for(Output output : comparisonResults) 
			{
				if(!algorithmApproaches.contains(output.getAlgorithmApproach()))
					algorithmApproaches.add(output.getAlgorithmApproach());
			}
			
			tableHtmlString = "<center><span " + "style=\"color:ForestGreen;\"" + ">" + 
											"<b><h2>" + 
												HtmlStrings.PerformanceResultsHeading +
											"</b></h2>" +
									 "</span></center>" ;

			for(String algoApproach : algorithmApproaches) 
			{
				tableHtmlString += "<span>" + 
											"<b>" + "Results : " + 
													algoApproach +
											"</b>" +
									 "</span><br /><br />" ;
				
				
				for (Output output : comparisonResults) 
				{
					if(output.getAlgorithmApproach() == algoApproach)
					{
						tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
												"<tr>"+
													"<td>" +
														"<b><span " + "style=\"color:Chocolate	;\"" + ">" + 
														  output.getAlgorthmName() +
													 	 "</b></span>"+ 
													"</td>" +
												"</tr>" +
												"<tr>" +
													"<td>" +
														"<center>" + "<b>Profit : </b> " + 
														  output.getTotalProfit() +
														"</center>" +
													"</td>" +
												"</tr>" +
												"<tr>" +
													"<td>" +
														"<center>" + "<b>Waste : </b> " + 
														     output.getTotalWaste() +
														"</center>" +
													"</td>" +
												"</tr>" +
												"<tr>" +
													"<td>" +
														 "<center>" + "<b>Waste - Profit </b> " + 
														     output.getWasteMinusProfit() +
														 "</center>" +
													 "</td>" +
												"</tr>" +
												"<tr>"+
													"<td>" +
														  "<center>" + "<b>Waste/Profit Ratio : </b> " +
														  		output.getWasteProfitRatio() + 
														  "</center>" +
													 "</td>" +
												   "</tr>"+
												   "<tr>"+
													"<td>" +
														  "<center>" + "<b>No. Of Trucks Used : </b> " +
														  		output.getClosedTrucks().size() + 
														  "</center>" +
													 "</td>" +
												   "</tr>" +
											"</table>";
					}
				}
				tableHtmlString+= "<br /><br /><br />";
			}
		}
		else
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;
		return tableHtmlString;
	}
	
	/* Private Methods  */
	
	/* get HTML String for Harmonic class list  */
	private static String GetHarmonicClassesHtml(List<HarmonicClass> harmonicClasses) 
	{
		String tableHtmlString ="";
		if (harmonicClasses != null && harmonicClasses.size() > 0) 
		{
				
			tableHtmlString += "<span " + "style=\"color:Chocolate	;\"" + ">"+
									         " <b>"+  HtmlStrings.HarmonicClassesHeading +"</b>" +
									       "</span><br />";

			Collections.sort(harmonicClasses , HarmonicClass.HarmonicClassIdIdComparator);
				
			for (HarmonicClass harmonicClass : harmonicClasses) 
			{
				tableHtmlString +=  "<table class=" + "\"" + "blueTable" + "\"" + ">"+
										"<tr>"+
											"<td>" +
												"<b><center>" + "Class " + 
													harmonicClass.getClassId() + 
												"</center></b>" +
											"</td>" +
										"</tr>" +
										"<tr>" +
											"<td>" +
												"<center>" + "<b>Item Size Range : </b> " + 
												   	harmonicClass.getMinClassSize() +
													"<b> - </b>" + harmonicClass.getMaxClassSize() + 
												 "</center>" +
											 "</td>" +
										 "</tr>" +
									"</table>";
			}
			tableHtmlString+= "<br /><br />";
		}
		else
			tableHtmlString = HtmlStrings.NoDataHtmlMessage;;
		return tableHtmlString;
	}
}
