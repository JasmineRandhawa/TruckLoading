package DataObjects;

/* Class to store information for Performance Parameters of Truck Loading Algorithms */
public class PerformanceComparison {

	/* Performance Comparison Class Fields */
	private double CompetitiveRatio;
	private int NumberOfTrucksUsed;
	private int Wastage;
	private String AlgorthmName;

	/* Constructor for Performance Comparison Class */
	public PerformanceComparison(double competitiveRatio, int numberOfTrucksUsed, int wastage, String algorthmName) {
		setCompetitiveRatio(competitiveRatio);
		setNumberOfTrucksUsed(numberOfTrucksUsed);
		setWastage(wastage);
		setAlgorthmName(algorthmName);
	}

	/* Getters and Setters for Performance Comparison Class Fields */
	public double getCompetitiveRatio() {
		return CompetitiveRatio;
	}

	public void setCompetitiveRatio(double competitiveRatio) {
		CompetitiveRatio = competitiveRatio;
	}

	public int getNumberOfTrucksUsed() {
		return NumberOfTrucksUsed;
	}

	public void setNumberOfTrucksUsed(int numberOfTrucksUsed) {
		NumberOfTrucksUsed = numberOfTrucksUsed;
	}

	public int getWastage() {
		return Wastage;
	}

	public void setWastage(int wastage) {
		Wastage = wastage;
	}

	public String getAlgorthmName() {
		return AlgorthmName;
	}

	public void setAlgorthmName(String algorthmName) {
		AlgorthmName = algorthmName;
	}
}
