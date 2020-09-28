package Algorithms;

import DataObjects.Input;
import DataObjects.Output;

import java.util.ArrayList;
import java.util.List;

// fetch combined results of all Online approaches for truck loading
public class Online {
	public static List<Output> GetOnlineTruckLoadingResults(List<Input> inputList) {
		List<Output> results = new ArrayList<Output>();
		results.add(FirstFit.GetTruckLoadingResults(inputList));
		results.add(NextFit.GetTruckLoadingResults(inputList));
		results.add(BestFit.GetTruckLoadingResults(inputList));
		return results;
	}
}
