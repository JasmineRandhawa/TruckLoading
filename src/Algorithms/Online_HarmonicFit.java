package Algorithms;

import DataObjects.Truck;
import DataObjects.Output;
import DataObjects.Item;

import java.util.ArrayList;
import java.util.List;

/* Fetch results of Online approach - Harmonic Fit Algorithm for truck loading*/
public class Online_HarmonicFit {
	static List<Truck> trucks = new ArrayList<Truck>();

	/* Harmonic Fit Algorithm for Truck Loading */
	public static Output GetResults(List<Item> itemList, int numberOfOpenTrucksThreshold, int truckCapacity) {
		List<Truck> openTrucks = new ArrayList<Truck>(numberOfOpenTrucksThreshold);
		List<Truck> closedTrucks = new ArrayList<Truck>();

		Output output = new Output(openTrucks, closedTrucks);
		return output;
	}

}
