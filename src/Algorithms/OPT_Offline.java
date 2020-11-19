package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Item;
import Utility.ItemOperations;
import Utility.TruckOperations;

/* Fetch results of Optimal Offline approach - First Fit Decreasing Algorithm for truck loading*/
public class OPT_Offline {

	static List<Item> unProcessedItemList = new ArrayList<Item>();

	/* Optimal Packing for Truck Loading */
	public static Output GetResults(List<Item> sortedItemList, int numberOfOpenTrucksThreshold, int truckCapacity) {
		
		List<Truck> openTrucks = new ArrayList<Truck>(numberOfOpenTrucksThreshold);
		List<Truck> closedTrucks = new ArrayList<Truck>();
		unProcessedItemList.addAll(sortedItemList);

		while (unProcessedItemList.size() > 0) 
		{
			if (openTrucks.size() == numberOfOpenTrucksThreshold)
				break;
			// open new truck
			Truck truck = new Truck(1, new ArrayList<Item>(), truckCapacity, 0, 0);
			openTrucks.add(truck);

			// load the truck with items that can fill the truck the best
			while (truck.getRemainingCapacity() >= 0 && truck.getRemainingCapacity() <= truckCapacity) 
			{
				Item item = ItemOperations.GetFirstUnProcessedItemThatFitTheTruck(truck.getRemainingCapacity(),unProcessedItemList);
				if (item != null) {
					truck = TruckOperations.LoadItemIntoTruck(truck, item);
					unProcessedItemList.remove(item);
				} 
				else {
					// close the truck if its with lowest delivery timeline as 1
					if (truck.getMinDeliveryDeadline() == 1) {
						closedTrucks.add(truck);
						openTrucks.remove(truck);
					}
					break;
				}
			}
		}

		// return output in form of open and closed trucks after serving all items
		Output output = new Output(openTrucks, closedTrucks, unProcessedItemList);
		return output;
	}

}
