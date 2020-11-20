package DataObjects;

import java.util.Comparator;
import java.util.List;

// Truck object for storing truck info
public class Truck {

	/* Truck Class Fields */
	private int TruckId;
	private String TruckName;
	private List<Item> TruckItems;
	private int RemainingCapacity;
	private int MinDeliveryDeadline;
	private int MaxDeliveryDeadline;
	private int HarmonicClassId;
	private boolean isExpedited;
	private int TotalSizeOfExpeditedItems;

	/* Constructor for Truck Class */
	public Truck(int truckId,  List<Item> truckItems, int remainingCapacity, int minDeliveryDeadline,
			int maxDeliveryDeadline ,boolean isExpedited,int harmonicClassId) {
		TruckId = truckId;
		TruckItems = truckItems;
		RemainingCapacity = remainingCapacity;
		MinDeliveryDeadline = minDeliveryDeadline;
		MaxDeliveryDeadline = maxDeliveryDeadline;
		isExpedited= isExpedited;
		HarmonicClassId = harmonicClassId;
	}
	
	/* Getters and Setters for Truck Class Fields */
	public int getTruckId() {
		return TruckId;
	}

	public void setTruckId(int truckId) {
		TruckId = truckId;
	}

	public List<Item> getTruckItems() {
		return TruckItems;
	}

	public void setTruckItems(List<Item> truckItems) {
		TruckItems = truckItems;
	}

	public int getRemainingCapacity() {
		return RemainingCapacity;
	}

	public void setRemainingCapacity(int remainingCapacity) {
		RemainingCapacity = remainingCapacity;
	}

	public int getMinDeliveryDeadline() {
		return MinDeliveryDeadline;
	}

	public void setMinDeliveryDeadline(int minDeliveryDeadline) {
		MinDeliveryDeadline = minDeliveryDeadline;
	}

	public int getMaxDeliveryDeadline() {
		return MaxDeliveryDeadline;
	}

	public void setMaxDeliveryDeadline(int maxDeliveryDeadline) {
		MaxDeliveryDeadline = maxDeliveryDeadline;
	}
	
	public int getHarmonicClassId() {
		return HarmonicClassId;
	}

	public void setHarmonicClassId(int harmonicClassId) {
		HarmonicClassId = harmonicClassId;
	}

	public boolean getIsExpedited() {
		return isExpedited;
	}

	public void setIsExpedited(boolean isExpedited) {
		this.isExpedited = isExpedited;
	}

	public int getTotalSizeOfExpeditedItems() {
		return TotalSizeOfExpeditedItems;
	}

	public void setTotalSizeOfExpeditedItems(int totalSizeOfExpeditedItems) {
		TotalSizeOfExpeditedItems = totalSizeOfExpeditedItems;
	}

	/* Comparator for sorting the Truck list by truck Id */
	public static Comparator<Truck> TruckIdComparator = new Comparator<Truck>() {

		public int compare(Truck truck1, Truck truck2) {
			return truck1.getTruckId() - truck2.getTruckId();
		}
	};
	
	/* Comparator for sorting the Truck list by truck remaining capacity */
	public static Comparator<Truck> TruckRemainingCapacityComparator = new Comparator<Truck>() {

		public int compare(Truck truck1, Truck truck2) {
			return truck1.getRemainingCapacity() - truck2.getRemainingCapacity();
		}
	};
	
	/* Comparator for sorting the Truck list by truck delivery date comparator */
	public static Comparator<Truck> TruckDeliveryDeadlineComparator = new Comparator<Truck>() {
		public int compare(Truck truck1, Truck truck2) {
			return truck1.getMinDeliveryDeadline() - truck2.getMinDeliveryDeadline();
		}
	};
	
	/* Comparator for sorting the Truck list by truck harmonic class id comparator */
	public static Comparator<Truck> TruckHarmonicClassIdComparator = new Comparator<Truck>() {
		public int compare(Truck truck1, Truck truck2) {
			return truck1.getHarmonicClassId() - truck2.getHarmonicClassId();
		}
	};
}
