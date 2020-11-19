package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import DataObjects.Item;

//Item Operations such as searching for specific items in a list
public class ItemOperations {

	/* Public Methods */

	// load data file into Input list from input file
	public static List<Item> LoadItemsIntoListFromFile(String dataFilePath) {
		List<Item> itemList = new ArrayList<Item>();
		try {
			File file = new File(dataFilePath);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			int row = 1;
			int itemId = 1;
			while ((line = in.readLine()) != null) {
				String[] values = line.split(",");
				if (row == 1) {
					row++; // skip first line as it has column names
					continue;
				}
				if (values.length != 2)
					break;
				Item item = new Item(itemId++, Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim()));
				itemList.add(item);
				row++;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
		return itemList;
	}

	public static Item GetFirstUnProcessedItemThatFitTheTruck(int truckRemainingCapacity,
			List<Item> unProcessedItemList) {
		// scan all the unprocessed items
		for (Item item : unProcessedItemList) {
			// loads the item in the first truck it can fit it
			if (item.getItemSize() <= truckRemainingCapacity)
				return item;
		}
		return null;
	}
}
