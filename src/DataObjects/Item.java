package DataObjects;

import java.util.Comparator;

/*  Data object to store Item data from input data file */
public class Item 
{
	/* Item Class Fields */
	private int ItemId;
	private double ItemSize;
	private int Deadline;

	/* Constructor for Item Class */
	public Item(int itemId, double itemSize, int deadline) {
		ItemId = itemId;
		ItemSize = itemSize;
		Deadline = deadline;
	}

	/* Getters and Setters for Item Class Fields */
	public int getItemId() {
		return ItemId;
	}

	public double getItemSize() {
		return ItemSize;
	}

	public int getDeadline() {
		return Deadline;
	}

	/*Field Comparators for Item Class*/
	
	/* Comparator for sorting the Item list by Id comparator descending order */
	public static Comparator<Item> ItemIdComparator = new Comparator<Item>() {
		public int compare(Item item1, Item item2) {
			return item2.getItemId() - item1.getItemId();
		}
	};
	
	/* Comparator for sorting the Item list by deadline comparator  ascending order */
	public static Comparator<Item> ItemDeadlineComparator = new Comparator<Item>() {
		public int compare(Item item1, Item item2) {
			return item1.getDeadline() - item2.getDeadline();
		}
	};
}
