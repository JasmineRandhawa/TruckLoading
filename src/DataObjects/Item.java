package DataObjects;

import java.util.Comparator;

public class Item {

	/* Item Class Fields */
	private int ItemId;
	private int ItemSize;
	private int DeliveryDeadline;

	/* Constructor for Item Class */
	public Item(int itemId, int itemSize, int deliveryDeadline) {
		ItemId = itemId;
		ItemSize = itemSize;
		DeliveryDeadline = deliveryDeadline;
	}

	/* Getters and Setters for Item Class Fields */
	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public int getItemSize() {
		return ItemSize;
	}

	public void setItemSize(int itemSize) {
		ItemSize = itemSize;
	}

	public int getDeliveryDeadline() {
		return DeliveryDeadline;
	}

	public void setDeliveryDeadline(int deliveryDeadline) {
		DeliveryDeadline = deliveryDeadline;
	}

	/*
	 * Comparator for sorting the item list by delivery deadline in ascending order
	 */
	public static Comparator<Item> ItemDeliveryDealineComparator = new Comparator<Item>() {

		public int compare(Item item1, Item item2) {
			return item1.getDeliveryDeadline() - item2.getDeliveryDeadline();
		}
	};

	/* Comparator for sorting the item list by item size in descending order */
	public static Comparator<Item> ItemSizeComparator = new Comparator<Item>() {

		public int compare(Item item1, Item item2) {
			return item2.getItemSize() - item1.getItemSize();
		}
	};

}
