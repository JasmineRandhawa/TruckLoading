package Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.HarmonicClass;
import DataObjects.Item;
import DataObjects.Truck;

/*Truck Operations such as opening new trucks , loading items into new or existing trucks , 
checking conditions for values of fields of the truck */
public class TruckOperations {

	/* Public Methods */

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
	public static Truck GetFirstTruckThanItemCanFitInto(List<Truck> openTrucks, int itemSize) {
		for (Truck truck : openTrucks) {
			if (itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}

	// scan all the open trucks and find the best truck that can fit item
	public static Truck GetBestTruckThanItemCanFitInto(List<Truck> openTrucks, int itemSize) {
		Collections.sort(openTrucks, Truck.TruckRemainingCapacityComparator);
		for (Truck truck : openTrucks) {
			if (itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}
	
	public static Truck GetCurrentOpenTruckWithinClassThatFitsItem(List<Truck> openTrucks,int itemSize, int classIdForItem) {
		
		List<Truck> openTrucksForClass = new ArrayList<Truck>();
		for(Truck truck:openTrucks)
		{
			if(truck.getHarmonicClassId() == classIdForItem)
				openTrucksForClass.add(truck);
		}
		Collections.sort(openTrucksForClass, Truck.TruckIdComparator);
		if(openTrucksForClass!=null && openTrucksForClass.size()>0)
		{
			Truck truck = openTrucksForClass.get(openTrucksForClass.size()-1);
			if (truck!=null && itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}

	// scan all the open trucks and find the first truck that has lowest delivery
	// deadline
	public static Truck FindFirstOpenTruckWithLowestDeliveryDate(List<Truck> openTrucks) {
		Collections.sort(openTrucks, Truck.TruckDeliveryDeadlineComparator);
		return openTrucks.get(0);
	}

	// scan all the open trucks and find the best truck that has lowest delivery
	// deadline
	// max capacity and can be closed
	public static Truck FindFullestOpenTruckWithLowestDeliveryDate(List<Truck> openTrucks) {
		Collections.sort(openTrucks, Truck.TruckRemainingCapacityComparator);
		Collections.sort(openTrucks, Truck.TruckDeliveryDeadlineComparator);
		return openTrucks.get(0);
	}
	
	// find the current truck 
	public static Truck FindCurrentOpenTruck(List<Truck> openTrucks) {
		if(openTrucks!=null && openTrucks.size()>0)
		{
			Collections.sort(openTrucks, Truck.TruckIdComparator);
			Truck truck = openTrucks.get(openTrucks.size()-1);
			if (truck!=null)
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

	public static Truck UpdateTruckDeadline(Truck truckToBeClosed) {
		// TODO Auto-generated method stub
		int totalSizeOfExpeditedItems =0;
		for (Item item : truckToBeClosed.getTruckItems()) {
			if (item.getDeliveryDeadline() != 1) {
				item.setDeliveryDeadline(1);
				totalSizeOfExpeditedItems += item.getItemSize();
			}
		}
		if(totalSizeOfExpeditedItems>0)
		{
			// update the expedite flag
			truckToBeClosed.setIsExpedited(true);
			truckToBeClosed.setTotalSizeOfExpeditedItems(totalSizeOfExpeditedItems);
		}
		return truckToBeClosed;
	}
	
	//Divide into K harmonic classes
	public static List<HarmonicClass> DivideIntoKClasses(int truckCapacity, int numberOfOpenTrucksAllowed) {
		List<HarmonicClass> harmonicClasses = new ArrayList<HarmonicClass>(numberOfOpenTrucksAllowed);
		int classSizeDifference = truckCapacity/numberOfOpenTrucksAllowed ; 
		int numberOfClasses = numberOfOpenTrucksAllowed;
		for (int i = 1; i <= numberOfClasses ;i++)
		{
			int minClassSize = i==1 ? 0 :classSizeDifference * (i-1) + 1;
			int maxClassSize = classSizeDifference * i ;
			HarmonicClass harmonicClass = new HarmonicClass((numberOfClasses - i)+1 ,minClassSize,maxClassSize);
			harmonicClasses.add(harmonicClass);
		}
		return harmonicClasses;
	}
}
