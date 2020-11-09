package Algorithms;

import DataObjects.Truck;
import DataObjects.TruckOperations;
import DataObjects.Output;
import DataObjects.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Fetch results of Optimal Offline approach - First Fit Decreasing Algorithm for truck loading*/
public class OPT_FirstFitDecreasing {

	/* First Fit Decreasing Algorithm for Truck Loading */
	public static Output GetResults(List<Item> itemList, int numberOfOpenTrucksThreshold, int truckCapacity) {
		List<Truck> openTrucks = new ArrayList<Truck>(numberOfOpenTrucksThreshold);
		List<Truck> closedTrucks = new ArrayList<Truck>();

		// Sort items in ascending order of delivery deadline and descending order of
		// their size
		Collections.sort(itemList, Item.ItemDeliveryDealineComparator);
		Collections.sort(itemList, Item.ItemSizeComparator);

		for (Item item : itemList) {
			boolean isItemAdded = false;

			// scan all the open trucks
			for (Truck truck : openTrucks) {
				// loads the item in the first truck it can fit it
				if (item.getItemSize() <= truck.getRemainingCapacity()) {
					truck = TruckOperations.LoadItemIntoTruck(truck, item);
					isItemAdded = true;
					break;
				}
			}

			// if item cannot fit in any of the open truck
			if (!isItemAdded) {
				// check if we reach the threshold for number of open trucks allowed
				if (openTrucks.size() == numberOfOpenTrucksThreshold) {
					// close the first truck as it is the fullest and with lowest delivery timeline
					Truck firstTruck = openTrucks.get(0);
					closedTrucks.add(firstTruck);
					openTrucks.remove(0);
				}
				// open new truck
				openTrucks = TruckOperations.openNewTruck(item, truckCapacity, openTrucks);
			}
		}

		// return output in form of open and closed trucks after serving all items
		Output output = new Output(openTrucks, closedTrucks);
		return output;
	}
}
