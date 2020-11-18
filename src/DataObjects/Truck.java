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

	/* Constructor for Truck Class */
	public Truck(int truckId,  List<Item> truckItems, int remainingCapacity, int minDeliveryDeadline,
			int maxDeliveryDeadline) {
		TruckId = truckId;
		TruckItems = truckItems;
		RemainingCapacity = remainingCapacity;
		MinDeliveryDeadline = minDeliveryDeadline;
		MaxDeliveryDeadline = maxDeliveryDeadline;
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

	/* Comparator for sorting the Truck list by truck Id */
	public static Comparator<Truck> TruckIdComparator = new Comparator<Truck>() {

		public int compare(Truck truck1, Truck truck2) {
			return truck1.getTruckId() - truck2.getTruckId();
		}
	};

}
