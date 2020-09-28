package DataObjects;

public class PerformanceComparison {
	private double CompetitiveRatio;
	private int NumberOfTrucksUsed;
	private int Wastage;
	private String AlgorthmName;

	public PerformanceComparison(double competitiveRatio, int numberOfTrucksUsed, int wastage, String algorthmName) {
		setCompetitiveRatio(competitiveRatio);
		setNumberOfTrucksUsed(numberOfTrucksUsed);
		setWastage(wastage);
		setAlgorthmName(algorthmName);
	}

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
