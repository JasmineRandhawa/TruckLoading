package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import DataObjects.Item;
import DataObjects.Truck;

// Helper Methods
public class Helper {

	// load data file into Input list
	public static List<Item> LoadItemsIntoListFromFile(String dataFilePath) {
		List<Item> itemList = new ArrayList<Item>();
	    try {
	    	File file = new File(dataFilePath);
	        BufferedReader in = new BufferedReader(new FileReader(file));
	        String line ;
	        int row = 1;
	        int itemId = 1;
	        while ((line = in.readLine())!= null) {
	        	String[] values = line.split(",");
	        	if( row == 1)
	        	{
	        		row++; // skip first line as it has column names
	        		continue;
	        	}
	        	if(values.length != 2) break;
	            Item item = new Item(itemId++, 
	            				  Integer.parseInt(values[0].trim()), 
	            				  Integer.parseInt(values[1].trim()));
	            itemList.add(item);
	            row++;
	        }
	        in.close();
	    } catch (IOException e) {
	        System.out.println("File Read Error");
	    }
		return itemList;
	}
	
	// general function for getting html string for trucks data
	public static String GetTruckTableHTMLString(List<Truck> trucks, String truckType) {
		
		String tableHtmlString ="";

		if (trucks != null && trucks.size() > 0) {
			int truckCount = trucks.size();
			
			List<Truck> firstHalf = new ArrayList<Truck>();
			int firstHalfCount = truckCount %2 ==0 ? truckCount/2-1 :truckCount/2 ;
			
			List<Truck> secondHalf = new ArrayList<Truck>();
			int secondHalfCount = truckCount %2 ==0 ? truckCount/2 :truckCount/2 +1 ;
			
			for (int i = 0; i <= firstHalfCount; i++)
				firstHalf.add(trucks.get(i));
			for (int i = secondHalfCount ; i < truckCount; i++)
				secondHalf.add(trucks.get(i));
			if(firstHalf!=null && firstHalf.size()>0)
			{
				tableHtmlString  = GetTruckRowsHtmlString(firstHalf,truckType);
				if(secondHalf!=null && secondHalf.size()>0)
					tableHtmlString  = tableHtmlString + GetTruckRowsHtmlString(secondHalf,null);
			}
		}
		return  tableHtmlString ; 	
	}

	// general function for getting html string for input items or truck items data
	public static String GetItemTableHtmlString(List<Item> itemList, boolean isSorted, boolean istruckItem) {
		if (!istruckItem)
		{
			String tableHeadingTag = "";
			
			if (isSorted)
				tableHeadingTag = "<span " + "style=\"color:Chocolate	;\"" + ">"+
							          "Sorted Input Item Sequence with decreasing size " +
							           "and increasing deadline" +
							       "</span>";
			else
				tableHeadingTag = "<span " + "style=\"color:Chocolate	;\"" + ">" + 
										"Input Item Sequence" +
								   "</span>";
			return "<table class=" + "\"" + "pinkTable" + "\"" + ">"+
						"<tr><td colspan=\"5\">" +
							     "<h1><center>" + tableHeadingTag + "</center></h1>" +
							 "</td>" +
						 "</tr>" +
						 "<tr>" +
						      "<th>ItemId</th>" +
						      "<th>ItemSize</th>" +
						      "<th>ItemDeadline</th>" +
						 "</tr>"+
					     GetItemRowsHtmlString(itemList)+
					 "</table>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;";								
			}
			else
			return  "<tr>" +
					    "<th>ItemId</th>" +
					    "<th>ItemSize</th>" +
					     "<th>ItemDeadline</th>" +
					 "</tr>" + 
					 GetItemRowsHtmlString(itemList);
	}

	private static String GetTruckRowsHtmlString(List<Truck> trucks, String truckType) {
			
		String tableHtmlString = "<table class=" + "\"" + "pinkTable" + "\"" + ">";
		String tableHeadingTag =  "<b><span " + "style=\"color:Chocolate	;\"" + ">" + 
		  		(truckType!=null ? (truckType + " Truck Items") : "")+
		  		"<b></span>";
		String rowsHtml = "<tr>"+
                "<td colspan=\"5\">"+
				    "<h1><center>" +
				    tableHeadingTag +
				  	"</center></h1>"+
				  "</td>"+
			"</tr>";
		if(truckType==null)rowsHtml ="";
		for (Truck truck : trucks) {
			 rowsHtml = rowsHtml + "<tr " + "style=\"background-color:PaleGoldenrod;\""  + ">"+
		                            "<td colspan=\"5\">"+
									    "<h1><center>" +
									    	"Truck " + truck.getTruckId()+ 
									  	"</center></h1>"+
									  "</td>"+
								"</tr>"+
					            GetItemTableHtmlString(truck.getTruckItems(), false, true);
		}
		tableHtmlString =  tableHtmlString + rowsHtml + "</table>&nbsp;";
		return tableHtmlString;
	}
	
	/*Private Methods*/
	//general function for items rows hrml string
	private static String GetItemRowsHtmlString(List<Item> itemList) {
		String tableHtmlString ="";
		if (itemList != null && itemList.size() > 0) {
			for (Item item : itemList) {
				String trTagString = "<tr " + "style=\"background-color:Cornsilk;\"" + ">";
				tableHtmlString = tableHtmlString + trTagString + "<td>" + "Item " + item.getItemId() + "</td>" + "<td>"
						+ item.getItemSize() + "</td>" + "<td>" + item.getDeliveryDeadline() + " day"
						+ (item.getDeliveryDeadline() > 1 ? "s" : "") + "</td></tr>";
			}
		}
		return tableHtmlString;
	}

}
