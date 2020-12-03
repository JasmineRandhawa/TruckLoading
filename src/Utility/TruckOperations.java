package Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataObjects.HarmonicClass;
import DataObjects.Item;
import DataObjects.Truck;

/* Truck data Load , processing and Access Operations */
public class TruckOperations 
{
	static List<HarmonicClass> harmonicClasses = new ArrayList<HarmonicClass>();

	/* Public Methods */
	
	/* Divide into K Harmonic classes */
	public static List<HarmonicClass> DivideIntoKHarmonicClasses(double truckCapacity, int k) 
	{
		int remainingClassesToBeAdded = 0;
		harmonicClasses = new ArrayList<HarmonicClass>();
		
		if (k > 0) 
		{
			if (k >= 1) 
			{
				harmonicClasses.add(new HarmonicClass(k, 0.01, Common.RoundDecimal(1.0 / k)));
				if (k == 1)
					return harmonicClasses;
			}
			if (k >= 2) 
			{
				harmonicClasses.add(new HarmonicClass(1, 0.51, 1.0));
				if (k == 2)
					return harmonicClasses;
				remainingClassesToBeAdded = k - 2;
			}

			for (int i = remainingClassesToBeAdded + 1; i > 1; i--) 
			{
				HarmonicClass harmonicClass = new HarmonicClass
												(
													i, 
													Common.RoundDecimal(((1.0 / (i + 1)) + 0.01)),
													Common.RoundDecimal(1.0 / i)
												);
				harmonicClasses.add(harmonicClass);
			}
		}
		return harmonicClasses;
	}
	
	/* Get Harmonic Classes List */
	public static List<HarmonicClass> GetHarmonicClassesList() 
	{
		return harmonicClasses;
	}

	/*  to load item in an existing truck */
	public static Truck LoadItemIntoTruck(Truck truck, Item item) 
	{
		List<Item> items = truck.getTruckItems();
		items.add(item);
		truck.setTruckItems(items);
		double spaceRemainingIntruck = Common.RoundDecimal(truck.getRemainingCapacity() - item.getItemSize());
		truck.setRemainingCapacity(spaceRemainingIntruck);
		return truck;
	}

	/*  scan all the open trucks and find the first truck that can fit item */
	public static Truck FindFirstTruckThatFits(List<Truck> openTrucks, double itemSize) 
	{
		for (Truck truck : openTrucks) 
		{
			if (itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}

	/*  scan all the open trucks and find the fullest truck that can fit item */
	public static Truck FindFullestTruck(List<Truck> openTrucks, double itemSize) 
	{
		Collections.sort(openTrucks, Truck.TruckRemainingCapacityComparator);
		for (Truck truck : openTrucks)
			if (itemSize <= truck.getRemainingCapacity())
				return truck;
		return null;
	}

	/*  scan all trucks within the harmonic class to which item 
	    and find the currently open truck among them that  can fit the item */
	public static Truck FindCurrentOpenTruckWithinClassThatFitsItem(List<Truck> openTrucks, 
			                                                        double itemSize,
			                                                        int classIdForItem) 
	{

		List<Truck> openTrucksForClass = new ArrayList<Truck>();
		for (Truck truck : openTrucks)
			if (truck.getHarmonicClassId() == classIdForItem)
				openTrucksForClass.add(truck);

		Collections.sort(openTrucksForClass, Truck.ReverseTruckIdComparator);
		if (openTrucksForClass != null && openTrucksForClass.size() > 0) 
		{
			Truck truck = openTrucksForClass.get(0);
			if (truck != null && itemSize <= truck.getRemainingCapacity())
				return truck;
		}
		return null;
	}

	/*  find the first open truck that has items with deadline greater than T */
	public static Truck FindFirstOpenTruckWithinDeadline(List<Truck> openTrucks, int T) 
	{
		for (Truck truck : openTrucks) 
		{
			List<Item> items = truck.getTruckItems();
			for (Item item : items) 
			{
				int deadline = item.getDeadline();
				if (deadline >= T + 1)
					return truck;
			}
		}
		return openTrucks.get(0);
	}

	/* find the best open truck that has maximum total size for items that have
        deadline greater than T */
	public static Truck FindBestOpenTruckWithMaxItemsWithinDeadline(List<Truck> openTrucks, 
																	int T) 
	{
		double profit = 0.0;
		for (Truck truck : openTrucks) 
		{
			List<Item> items = truck.getTruckItems();
			for (Item item : items) 
			{
				int deadline = item.getDeadline();
				if (deadline >= T + 1)
					profit = item.getItemSize();
			}
			truck.setProfit(profit);
		}
		Collections.sort(openTrucks, Truck.TruckTotalSizeOfItemsWithinDeadlineComparator);
		return openTrucks.get(0);
	}

	/*  find the most recently open truck */
	public static Truck FindCurrentOpenTruck(List<Truck> openTrucks) 
	{
		if (openTrucks != null && openTrucks.size() > 0) 
		{
			Collections.sort(openTrucks, Truck.ReverseTruckIdComparator);
			Truck truck = openTrucks.get(0);
			if (truck != null)
				return truck;
		}
		return null;
	}

	/* get total size Of truck items with Deadline equal to  T + 1
	   get total size of items due next day 
	   i.e. items at risk of being lost if truck is kept open */
	public static double GetTotalSizeOfItemsDueNextDay(Truck truck, int T) 
	{
		double totalSizeOfItemsWithinDeadline = 0.0;
		List<Item> items = truck.getTruckItems();
		for (Item item : items) 
		{
			int deadline = item.getDeadline();
			if (deadline == T + 1)
				totalSizeOfItemsWithinDeadline += item.getItemSize();
		}
		return totalSizeOfItemsWithinDeadline;
	}

	/* get total profit of truck i.e. total size Of truck items with Deadline Greater Than T */
	public static double GetTotalProfitOfTruck(Truck truck, int T) 
	{
		double totalSizeOfItemsWithinDeadline = 0.0;
		List<Item> items = truck.getTruckItems();
		for (Item item : items) 
		{
			int deadline = item.getDeadline();
			if (deadline >= T + 1)
				totalSizeOfItemsWithinDeadline += item.getItemSize();
		}
		return totalSizeOfItemsWithinDeadline;
	}

	/* get total waste of truck i.e. total size Of truck items 
	   with Deadline less than equal to T
	   total size of items in the truck that missed deadline */
	public static double GetTotalWasteOfTruck(Truck truck, int T) 
	{
		double totalSizeOfWastedItemsThatMissedDeadline = 0.0;
		List<Item> items = truck.getTruckItems();
		for (Item item : items) 
		{
			int deadline = item.getDeadline();
			if (deadline < T + 1)
				totalSizeOfWastedItemsThatMissedDeadline += item.getItemSize();
		}
		return totalSizeOfWastedItemsThatMissedDeadline;
	}
	
	/* find truck with maximum risk of loosing items if kept open */
	public static Truck FindTruckWithMaxRiskForKeepingOpen(List<Truck> trucks) 
	{
		Collections.sort(trucks, Truck.TruckLossGainRatioComparator);
		for (Truck truck : trucks)
			if (truck.getLossGainRatio() != 0.0)
				return truck;

		return null;
	}

	/* compute total profit so far */
	public static double GetTotalProfitSoFar(List<Truck> closedTrucks) {
		double totalProfitValue= 0.0;
		for (Truck t : closedTrucks) 
			totalProfitValue += t.getProfit();
		return totalProfitValue;
	}

	/* compute total profit so far */
	public static double GetTotalWasteSoFar(List<Truck> closedTrucks) {
		double totalWasteValue= 0.0;		
		for (Truck t : closedTrucks) 
			totalWasteValue += t.getWaste();
		return totalWasteValue;
	}
		
	/* get harmonic class to which item belongs */
	public static int GetHarmonicClassForItem(double itemSize) 
	{
		for (HarmonicClass harmonicClass : harmonicClasses) 
		{
			if (itemSize >= harmonicClass.getMinClassSize() && 
				itemSize <= harmonicClass.getMaxClassSize())
				return harmonicClass.getClassId();
		}
		return 0;
	}
}
