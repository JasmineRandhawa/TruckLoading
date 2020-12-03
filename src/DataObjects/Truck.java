package DataObjects;

import java.util.Comparator;
import java.util.List;

/*  Data object to store truck data */
public class Truck 
{
	/* Truck Class Fields */
	private int TruckId;
	private String TruckName;
	private List<Item> TruckItems;
	private double RemainingCapacity;
	private int HarmonicClassId;
	private int OpeningTime;
	private int ClosingTime;
	private double Profit;
	private double Waste;
	private double Gain;
	private double Loss;
	private double LossGainRatio;
	private String ClosingReason;
	private double TotalProfitIncurredAtTimeT;
	private double TotalWasteIncurredAtTimeT;

	/* Constructor for Truck Class */
	public Truck(int truckId, List<Item> truckItems, double remainingCapacity, 
			     int harmonicClassId, int openingTime) {
		TruckId = truckId;
		TruckItems = truckItems;
		RemainingCapacity = remainingCapacity;
		HarmonicClassId = harmonicClassId;
		OpeningTime = openingTime;
	}
	
	/* Getters and Setters for Truck Class Fields */
	public int getTruckId() {
		return TruckId;
	}

	public List<Item> getTruckItems() {
		return TruckItems;
	}

	public void setTruckItems(List<Item> truckItems) {
		TruckItems = truckItems;
	}

	public double getRemainingCapacity() {
		return RemainingCapacity;
	}

	public void setRemainingCapacity(double remainingCapacity) {
		RemainingCapacity = remainingCapacity;
	}

	public int getHarmonicClassId() {
		return HarmonicClassId;
	}

	public int getClosingTime() {
		return ClosingTime;
	}

	public void setClosingTime(int closingTime) {
		ClosingTime = closingTime;
	}

	public double getProfit() {
		return Profit;
	}

	public void setProfit(double profit) {
		Profit = profit;
	}

	public double getWaste() {
		return Waste;
	}

	public void setWaste(double waste) {
		Waste = waste;
	}

	public double getGain() {
		return Gain;
	}

	public void setGain(double gain) {
		Gain = gain;
	}

	public double getLoss() {
		return Loss;
	}

	public void setLoss(double loss) {
		Loss = loss;
	}

	public int getOpeningTime() {
		return OpeningTime;
	}

	public double getLossGainRatio() {
		return LossGainRatio;
	}

	public void setLossGainRatio(double lossGainRatio) {
		LossGainRatio = lossGainRatio;
	}

	public String getClosingReason() {
		return ClosingReason;
	}

	public void setClosingReason(String closingReason) {
		ClosingReason = closingReason;
	}
	
	public double getTotalProfitIncurredAtTimeT() {
		return TotalProfitIncurredAtTimeT;
	}

	public void setTotalProfitIncurredAtTimeT(double totalProfitIncurredAtTimeT) {
		TotalProfitIncurredAtTimeT = totalProfitIncurredAtTimeT;
	}

	public double getTotalWasteIncurredAtTimeT() {
		return TotalWasteIncurredAtTimeT;
	}

	public void setTotalWasteIncurredAtTimeT(double totalWasteIncurredAtTimeT) {
		TotalWasteIncurredAtTimeT = totalWasteIncurredAtTimeT;
	}

	/*Field Comparators for Truck Class*/
	
	/* Comparator for sorting the Truck list by truck Id ascending order*/
	public static Comparator<Truck> TruckIdComparator = new Comparator<Truck>() {

		public int compare(Truck truck1, Truck truck2) {
			return truck1.getTruckId() - truck2.getTruckId();
		}
	};

	/* Comparator for sorting the Truck list by truck Id in descending order */
	public static Comparator<Truck> ReverseTruckIdComparator = new Comparator<Truck>() {

		public int compare(Truck truck1, Truck truck2) {
			return truck2.getTruckId() - truck1.getTruckId();
		}
	};

	/* Comparator for sorting the Truck list by truck's Remaining capacity ascending order */
	public static Comparator<Truck> TruckRemainingCapacityComparator = new Comparator<Truck>() 
	{
		public int compare(Truck truck1, Truck truck2) {
			return Double.compare(truck1.getRemainingCapacity(), truck2.getRemainingCapacity());
		}
	};

	/*Comparator for sorting the Truck list by 
	  Total Size Of Items Within Deadline in descending order  */
	public static Comparator<Truck> TruckTotalSizeOfItemsWithinDeadlineComparator = new Comparator<Truck>() {
		public int compare(Truck truck1, Truck truck2) {
			return Double.compare(truck2.getProfit(), truck1.getProfit());
		}
	};

	/*Comparator for sorting the Truck list by truck Harmonic class Id in ascending order  */
	public static Comparator<Truck> TruckHarmonicClassIdComparator = new Comparator<Truck>() {
		public int compare(Truck truck1, Truck truck2) {
			return truck1.getHarmonicClassId() - truck2.getHarmonicClassId();
		}
	};

	/* Comparator for sorting the Truck list by loss gain ratio */
	public static Comparator<Truck> TruckLossGainRatioComparator = new Comparator<Truck>() {
		public int compare(Truck truck1, Truck truck2) {
			return Double.compare(truck2.getLossGainRatio(), truck1.getLossGainRatio());
		}
	};
}
