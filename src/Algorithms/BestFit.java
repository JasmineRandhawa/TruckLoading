package Algorithms;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Constants;
import DataObjects.Input;

import java.util.ArrayList;
import java.util.List;

// fetch results of Best Fit Online approach for truck Loading packing
// Pack Items tightly in trucks based on grouping with location and delivery date
public class BestFit {

	static List<Truck> trucks = new ArrayList<Truck>();

	public static Output GetTruckLoadingResults(List<Input> inputList) {
		int wastage = 0;
		Output output = new Output(trucks, Constants.BestFitAlgoName, trucks.size(), wastage);
		return output;
	}
}
