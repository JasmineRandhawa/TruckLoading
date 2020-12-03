package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DataObjects.Item;

/* Item data Load and Access Operations */
public class ItemOperations 
{
	static List<Item> inputList = new ArrayList<Item>();
	
	/* Public Methods */

	/* load data file into Input list from input file */
	public static List<Item> LoadItemsIntoListFromFile(String dataFilePath) 
	{
		inputList = new ArrayList<Item>();
		
		try 
		{
			File file = new File(dataFilePath);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			int row = 1;
			int itemId = 1;
			while ((line = in.readLine()) != null) 
			{
				String[] values = line.split("\t");
				if (row == 1) {
					row++; // skip first line as it has column names
					continue;
				}

				if (values == null || values.length != 2)
					return new ArrayList<Item>();

				if (values != null && values.length == 2 && values[0].trim() != null && values[0].trim().length() > 0
						&& values[1].trim() != null && values[1].trim().length() > 0) 
				{
					int id = itemId++;
					double size = Common.RoundDecimal(Double.parseDouble(values[0].trim()));
					int deadline = Integer.parseInt(values[1].trim());
					Item item = new Item(id, size, deadline);
					inputList.add(item);
					row++;
				}
			}
			
			in.close();
			if (inputList != null & inputList.size() > 0)
				System.out.println("Data Load completed for file "  + dataFilePath);
			return inputList;
		} 
		catch (IOException ex) 
		{
			
			System.out.println("Input data load error" + ex.getMessage());
		}
		return inputList;
	}

	/* get input item list */
	public static List<Item> GetInpuItemList() 
	{
		return inputList;
	}
}
