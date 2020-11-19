package DataObjects;

import java.util.List;

// Truck Loading Result  Data objects to store output of Algorithm
public class Output {

	/* Output Class Fields */
	private List<Truck> OpenTrucks;
	private List<Truck> ClosedTrucks;
	private List<Item> UnprocessedItems;

	/* Constructor for Output Class */
	public Output(List<Truck> openTrucks, List<Truck> closedTrucks, List<Item> unProcessedItemList) {
		super();
		OpenTrucks = openTrucks;
		ClosedTrucks = closedTrucks;
		UnprocessedItems = unProcessedItemList;
	}

	/* Getters and Setters for Output Class Fields */
	public List<Truck> getOpenTrucks() {
		return OpenTrucks;
	}

	public void setOpenTrucks(List<Truck> openTrucks) {
		OpenTrucks = openTrucks;
	}

	public List<Truck> getClosedTrucks() {
		return ClosedTrucks;
	}

	public void setClosedTrucks(List<Truck> closedTrucks) {
		ClosedTrucks = closedTrucks;
	}

	public List<Item> getUnprocessedItems() {
		return UnprocessedItems;
	}

	public void setUnprocessedItems(List<Item> closedTrucks) {
		UnprocessedItems = closedTrucks;
	}
}
