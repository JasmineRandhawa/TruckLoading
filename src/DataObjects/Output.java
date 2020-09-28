package DataObjects;

import java.util.List;

// truck Loading Result  Data objects to store output of Algorithm
public class Output {
	private List<Truck> TruckList;
	private String AlgorithmName;
	private int NumberOfTrucksUsed;
	private int TotalWastage;

	public Output(List<Truck> truckList, String algorithmName, int numberOfTrucksUsed, int totalWastage) {
		setTruckList(truckList);
		setAlgorithmName(algorithmName);
		setNumberOfTrucksUsed(numberOfTrucksUsed);
		setTotalWastage(totalWastage);
	}

	public List<Truck> getTruckList() {
		return TruckList;
	}

	public void setTruckList(List<Truck> truckList) {
		TruckList = truckList;
	}

	public String getAlgorithmName() {
		return AlgorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		AlgorithmName = algorithmName;
	}

	public int getNumberOfTrucksUsed() {
		return NumberOfTrucksUsed;
	}

	public void setNumberOfTrucksUsed(int numberOfTrucksUsed) {
		NumberOfTrucksUsed = numberOfTrucksUsed;
	}

	public int getTotalWastage() {
		return TotalWastage;
	}

	public void setTotalWastage(int totalWastage) {
		TotalWastage = totalWastage;
	}
}
