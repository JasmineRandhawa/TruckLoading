package DataObjects;

import java.util.Date;
import java.util.List;

public class Input {

	private int ItemId;
	private int ItemSize;
	private int TruckCapacity;
	private double Lattitude;
	private double Longitude;
	private Date DeliveryDate;

	public Input(int itemId, int itemSize, float lattitude, double longitude, Date deliveryDate, int truckCapacity) {
		setItemId(itemId);
		setItemSize(itemSize);
		setLattitude(lattitude);
		setLongitude(longitude);
		setDeliveryDate(deliveryDate);
		setTruckCapacity(truckCapacity);
	}

	public Date getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getLattitude() {
		return Lattitude;
	}

	public void setLattitude(double lattitude) {
		Lattitude = lattitude;
	}

	public int getTruckCapacity() {
		return TruckCapacity;
	}

	public void setTruckCapacity(int truckCapacity) {
		TruckCapacity = truckCapacity;
	}

	public int getItemSize() {
		return ItemSize;
	}

	public void setItemSize(int itemSize) {
		ItemSize = itemSize;
	}

	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}
}
