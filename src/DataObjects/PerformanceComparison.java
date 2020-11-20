package DataObjects;

/* Class to store information for Performance Parameters of Truck Loading Algorithms */
public class PerformanceComparison {

	/* Performance Comparison Class Fields */
	private double CompetitiveRatio;
	private int NoOfOpenTrucks;
	private int NoOfClosedTrucks;
	private int NoOfItemsDelivered;
	private int TotalSizeOfExpeditedItems;
	private int TotalSizeOfDeliveredItems;
	private int TotalWastage;
	private int TotalInputSize;

	private String AlgorthmName;

	/* Getters and Setters for Performance Comparison Class Fields */
	public double getCompetitiveRatio() {
		return CompetitiveRatio;
	}
	public void setCompetitiveRatio(double competitiveRatio) {
		CompetitiveRatio = competitiveRatio;
	}
	public int getNoOfOpenTrucks() {
		return NoOfOpenTrucks;
	}
	public void setNoOfOpenTrucks(int noOfOpenTrucks) {
		NoOfOpenTrucks = noOfOpenTrucks;
	}
	public int getNoOfClosedTrucks() {
		return NoOfClosedTrucks;
	}
	public void setNoOfClosedTrucks(int noOfClosedTrucks) {
		NoOfClosedTrucks = noOfClosedTrucks;
	}
	public int getNoOfItemsDelivered() {
		return NoOfItemsDelivered;
	}
	public void setNoOfItemsDelivered(int noOfItemsDelivered) {
		NoOfItemsDelivered = noOfItemsDelivered;
	}
	public int getTotalSizeOfExpeditedItems() {
		return TotalSizeOfExpeditedItems;
	}
	public void setTotalSizeOfExpeditedItems(int totalSizeOfExpeditedItems) {
		TotalSizeOfExpeditedItems = totalSizeOfExpeditedItems;
	}
	public int getTotalSizeOfDeliveredItems() {
		return TotalSizeOfDeliveredItems;
	}
	public void setTotalSizeOfDeliveredItems(int totalSizeOfDeliveredItems) {
		TotalSizeOfDeliveredItems = totalSizeOfDeliveredItems;
	}
	public int getTotalWastage() {
		return TotalWastage;
	}
	public void setTotalWastage(int totalWastage) {
		TotalWastage = totalWastage;
	}
	public String getAlgorthmName() {
		return AlgorthmName;
	}
	public void setAlgorthmName(String algorthmName) {
		AlgorthmName = algorthmName;
	}
	
	public int getTotalInputSize() {
		return TotalInputSize;
	}
	public void setTotalInputSize(int totalInputSize) {
		TotalInputSize = totalInputSize;
	}
}
