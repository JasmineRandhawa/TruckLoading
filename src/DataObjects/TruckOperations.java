package DataObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Truck Operations such as opening new trucks , loading items into new or existing trucks 
public class TruckOperations {

	/* Public Methods */

	// to open a new truck and load item into it
	public static List<Truck> openNewTruck(Item item, int truckCapacity, List<Truck> openTrucks) {
		List<Item> truckItems = new ArrayList<Item>();
		int remainingCapacity = truckCapacity - item.getItemSize();
		truckItems.add(item);
		int truckIndex = GetMaxtruckId(openTrucks) + 1;
		Truck truck = new Truck(truckIndex, "Truck" + truckIndex, truckItems, remainingCapacity,
				GetMinDeliveryDeadline(truckItems), GetMaxDeliveryDeadline(truckItems));
		openTrucks.add(truck);
		return openTrucks;
	}

	// to load item in an existing truck
	public static Truck LoadItemIntoTruck(Truck truck, Item item) {
		List<Item> items = truck.getTruckItems();
		items.add(item);
		truck.setTruckItems(items);
		truck.setRemainingCapacity(truck.getRemainingCapacity() - item.getItemSize());
		return truck;
	}

	// to check if item's delivery deadline falls under the delivery deadline range
	// of the truck
	public static boolean IsInDeliveryDeadlineFrame(Truck truck, int deliveryDeadline) {
		if (deliveryDeadline >= truck.getMinDeliveryDeadline() && deliveryDeadline < truck.getMaxDeliveryDeadline())
			return true;
		else
			return false;
	}

	/* Private Methods */
	// Get Maximum value of delivery deadline of items in the list
	private static int GetMaxDeliveryDeadline(List<Item> truckItems) {
		Collections.sort(truckItems, Item.ItemDeliveryDealineComparator);
		Item item = truckItems.get(truckItems.size() - 1);
		return item.getDeliveryDeadline();
	}

	// Get Minimum value of delivery deadline of items in the list
	private static int GetMinDeliveryDeadline(List<Item> truckItems) {
		Collections.sort(truckItems, Item.ItemDeliveryDealineComparator);
		Item item = truckItems.get(0);
		return item.getDeliveryDeadline();
	}

	// Get Maximum value of Truck Id of trucks in the list
	private static int GetMaxtruckId(List<Truck> trucks) {
		Collections.sort(trucks, Truck.TruckIdComparator);
		Truck truck = trucks.get(trucks.size() - 1);
		return truck.getTruckId();
	}
}
