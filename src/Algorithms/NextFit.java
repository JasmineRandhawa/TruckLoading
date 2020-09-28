package Algorithms;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// fetch results of First Fit Online Algorithm for truck loading
// Pack the item into the previously used truck that matches delivery date frame and location frame
public class NextFit {
	static List<Truck> trucks = new ArrayList<Truck>();

	public static Output GetTruckLoadingResults(List<Input> inputList) {
		int wastage = 0;
		Output output = new Output(trucks, Constants.NextFitAlgoName, trucks.size(), wastage);
		return output;
	}

}
