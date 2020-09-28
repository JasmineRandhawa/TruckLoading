package Algorithms;

import DataObjects.Truck;
import DataObjects.TruckOperations;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// fetch results of First Fit Online Algorithm for truck loading
// Pack the item into the first truck it fits and matches the delivery date frame and location frame
public class FirstFit {
	static List<Truck> trucks = new ArrayList<Truck>();

	public static Output GetTruckLoadingResults(List<Input> inputList) {
		int wastage = 0;
		Output output = new Output(trucks, Constants.FirstFitAlgoName, trucks.size(), wastage);
		return output;
	}
}
