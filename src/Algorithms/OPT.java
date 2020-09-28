package Algorithms;

import DataObjects.Truck;
import DataObjects.TruckOperations;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// fetch results of Optimal Offline approach for truck loading
// Sort items in decreasing order of size ,location, delivery date 
// and load it into a first truck where it can fit and match location frame and delivery date frame
public class OPT {

	static List<Truck> trucks = new ArrayList<Truck>();

	public static Output GetOfflineTruckLoadingResults(List<Input> inputList) {
		Collections.sort(inputList, Collections.reverseOrder());

		for (Input item : inputList) {
			boolean isItemAdded = false;
			for (Truck truck : trucks) {
				if (item.getItemSize() <= truck.getRemainingCapacity()
						&& TruckOperations.IsInLocationFrame(truck, item.getLattitude(), item.getLongitude())
						&& TruckOperations.IsInDeliveryDateFrame(truck, item.getDeliveryDate())) {
					truck = TruckOperations.LoadItemIntoTruck(truck, item);
					isItemAdded = true;
					break;
				}
			}
			if (!isItemAdded)
				trucks = TruckOperations.openNewTruck(item, trucks);
		}
		int wastage = 0;
		Output output = new Output(trucks, Constants.OPTAlgoName, trucks.size(), wastage);
		return output;
	}
}
