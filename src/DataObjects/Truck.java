package DataObjects;

import java.util.Date;
import java.util.List;

// Truck object for storing truck info
public class Truck {

	private int TruckId;
	private String TruckName;
	private List<Integer> TruckItems;
	private int RemainingCapacity;
	private double minLattitude;
	private double maxLattitude;
	private double minLongitude;
	private double maxLongitude;
	private Date minDeliveryDate;
	private Date maxDeliveryDate;

	public Truck(int truckId, String truckName, List<Integer> truckItems, int remainingCapacity, double minLattitude,
			double maxLattitude, double minLongitude, double maxLongitude, Date minDeliveryDate, Date maxDeliveryDate) {
		super();
		TruckId = truckId;
		TruckName = truckName;
		TruckItems = truckItems;
		RemainingCapacity = remainingCapacity;
		this.minLattitude = minLattitude;
		this.maxLattitude = maxLattitude;
		this.minLongitude = minLongitude;
		this.maxLongitude = maxLongitude;
		this.minDeliveryDate = minDeliveryDate;
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public int getTruckId() {
		return TruckId;
	}

	public void setTruckId(int truckId) {
		TruckId = truckId;
	}

	public String getTruckName() {
		return TruckName;
	}

	public void setTruckName(String truckName) {
		TruckName = truckName;
	}

	public List<Integer> getTruckItems() {
		return TruckItems;
	}

	public void setTruckItems(List<Integer> truckItems) {
		TruckItems = truckItems;
	}

	public int getRemainingCapacity() {
		return RemainingCapacity;
	}

	public void setRemainingCapacity(int remainingCapacity) {
		RemainingCapacity = remainingCapacity;
	}

	public double getMinLattitude() {
		return minLattitude;
	}

	public void setMinLattitude(double minLattitude) {
		this.minLattitude = minLattitude;
	}

	public double getMaxLattitude() {
		return maxLattitude;
	}

	public void setMaxLattitude(double maxLattitude) {
		this.maxLattitude = maxLattitude;
	}

	public double getMinLongitude() {
		return minLongitude;
	}

	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}

	public double getMaxLongitude() {
		return maxLongitude;
	}

	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	public Date getMinDeliveryDate() {
		return minDeliveryDate;
	}

	public void setMinDeliveryDate(Date minDeliveryDate) {
		this.minDeliveryDate = minDeliveryDate;
	}

	public Date getMaxDeliveryDate() {
		return maxDeliveryDate;
	}

	public void setMaxDeliveryDate(Date maxDeliveryDate) {
		this.maxDeliveryDate = maxDeliveryDate;
	}

}
