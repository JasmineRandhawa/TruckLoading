package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import DataObjects.Item;

/* Mock Input data File Operations */
public class MockInputData 
{
	/* Public Methods */

	/* mock deadline data by copying item size from source file, mock random deadline data and 
	   and writing (size, deadline) back to source file */
	public static List<Item> MockInputItemDeadlineData(String filePath) 
	{
		List<Item> mockedData = new ArrayList<Item>();

		int row = 1;
		try 
		{
			File file = new File(filePath);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;

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

				if (values != null && values.length == 2  &&
				    values[0].trim() != null && values[0].trim().length() > 0) 
				{
					int id = itemId++;
					double size = Common.RoundDecimal(Double.parseDouble(values[0].trim()));
					
					// randomize the deadline
					int deadline = Common.GetRandomNumber(id + 2, id + 20);
					Item item = new Item(id, size, deadline);
					if(deadline <= id)
					{
						int  a = 0;
					}
					mockedData.add(item);
					row++;
				}
			}
			in.close();
			
			if (mockedData != null & mockedData.size() > 0)
			{
				WriteMockedItemDataToTextFile(mockedData, filePath);
				System.out.println("Data Mocking Complete for file "  + filePath);
			}
			
		} catch (IOException ex) {
			System.out.println("Error while mocking data" + ex.getMessage());
		}
		return mockedData;
	}

	/* Private Methods */
	
	/* Write Mocked Data Into file */
	private static void WriteMockedItemDataToTextFile(List<Item> items, String destinationFilePath) 
	{
		try {
			// empty destination file contents
			File file = new File(destinationFilePath);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");

			// write into file from the list
			FileWriter fos = new FileWriter(destinationFilePath);
			writer = new PrintWriter(fos);
			writer.println("Size\tDeadline\t");
			// loop through all your data and print it to the file
			for (Item item : items) {
				writer.print(Common.RoundDecimal(item.getItemSize()) + "\t" + item.getDeadline() + "\t");
				writer.println();
			}
			writer.close();
			fos.close();
		} catch (IOException e) {
			System.out.println("Error Printing Tab Delimited File");
		}
	}
}
