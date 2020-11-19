package Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.Item;
import DataObjects.Truck;

/*Truck Operations such as opening new trucks , loading items into new or existing trucks , 
checking conditions for values of fields of the truck */
public class TruckOperations {

	/* Public Methods */

	// to open a new truck and load item into it
	public static List<Truck> openNewTruck(Item item, int truckCapacity, List<Truck> openTrucks , List<Truck> closedTrucks) {
		List<Item> truckItems = new ArrayList<Item>();
		int remainingCapacity = truckCapacity - item.getItemSize();
		truckItems.add(item);
		int truckIndex = 1;
		if(openTrucks!=null && openTrucks.size()>0)
			truckIndex= GetMaxtruckId(openTrucks) + 1;
		else if(closedTrucks!=null && closedTrucks.size()>0)
			truckIndex= GetMaxtruckId(closedTrucks) + 1;
		Truck truck = new Truck(truckIndex, truckItems, remainingCapacity, GetMinDeliveryDeadline(truckItems),
				GetMaxDeliveryDeadline(truckItems));
		if(openTrucks==null)
			openTrucks = new ArrayList<Truck>();
		openTrucks.add(truck);
		return openTrucks;
	}

	// to load item in an existing truck
	public static Truck LoadItemIntoTruck(Truck truck, Item item) {
		List<Item> items = truck.getTruckItems();
		items.add(item);
		truck.setTruckItems(items);
		truck.setRemainingCapacity(truck.getRemainingCapacity() - item.getItemSize());
		truck.setMaxDeliveryDeadline(GetMaxDeliveryDeadline(items));
		truck.setMinDeliveryDeadline(GetMinDeliveryDeadline(items));
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

	// scan all the open trucks and find the first truck that can fit item
	public static Truck GetTruckThanItemCanFitInto(List<Truck> openTrucks, int itemSize) {
		for (Truck truck : openTrucks) {
			if (itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}

	// scan all the open trucks and find the best truck that has delivery deadline
	// as 1 and ,ax capacity and can be closed
	public static Truck FindBestOpenTruckThatHasUnitDeliveryDate(List<Truck> openTrucks) {
		Collections.sort(openTrucks, Truck.TruckRemainingCapacityComparator);
		for (Truck truck : openTrucks)
		if (truck.getMinDeliveryDeadline() == 1) {
			return truck;
		}
		return null;
	}

	// scan all the open trucks and find the first truck that has delivery deadline
	// as 1 and can be closed
	public static Truck FindFirstOpenTruckThatHasUnitDeliveryDate(List<Truck> openTrucks) {
		for (Truck truck : openTrucks)
			if (truck.getMinDeliveryDeadline() == 1) {
				return truck;
			}
		return null;
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
