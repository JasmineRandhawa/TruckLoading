package DataObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TruckOperations {

	public static boolean IsInLocationFrame(Truck truck, double lattitude, double longitude) {
		if (lattitude >= truck.getMinLattitude() && lattitude <= truck.getMaxLattitude()
				&& longitude >= truck.getMinLongitude() && longitude <= truck.getMaxLongitude())
			return true;
		else
			return false;
	}

	public static boolean IsInDeliveryDateFrame(Truck truck, Date deliveryDate) {
		if (deliveryDate.after(truck.getMinDeliveryDate()) && deliveryDate.before(truck.getMaxDeliveryDate()))
			return true;
		else
			return false;
	}

	private static int getMaxIndexInTrucks(List<Truck> trucks) {
		int maxTruckId = 0;
		for (Truck truck : trucks) {
			if (truck.getTruckId() > maxTruckId)
				maxTruckId = truck.getTruckId();
		}
		return maxTruckId;
	}

	public static List<Truck> openNewTruck(Input item, List<Truck> trucks) {
		List<Integer> truckItems = new ArrayList<Integer>();
		int remainingCapacity = Constants.TruckCapacity - item.getItemSize();
		if (item.getItemSize() != 0)
			truckItems.add(item.getItemId());
		int truckIndex = getMaxIndexInTrucks(trucks) + 1;
		Truck truck = new Truck(truckIndex, "Truck" + truckIndex, truckItems, remainingCapacity, item.getLattitude(),
				item.getLattitude() + Constants.locationFrame, item.getLongitude(),
				item.getLongitude() + Constants.locationFrame, item.getDeliveryDate(), item.getDeliveryDate()); // Add
																												// Constants.deliveryDateFrame)
		trucks.add(truck);
		return trucks;
	}

	public static Truck LoadItemIntoTruck(Truck truck, Input item) {
		truck.setRemainingCapacity(truck.getRemainingCapacity() - item.getItemSize());
		List<Integer> items = truck.getTruckItems();
		items.add(item.getItemId());
		truck.setTruckItems(items);
		return truck;
	}

}
