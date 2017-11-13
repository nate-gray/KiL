package application;

import java.util.PriorityQueue;
import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * This class is used to contain the data for the line items in the table of the GUI. 
 */

public class LineItem {
	
	/*
	 * Properties for displaying the data in the table view
	 */
	
	private SimpleStringProperty itemNameForTable; 
	private SimpleIntegerProperty stockForTable;
	private SimpleStringProperty nextShipmentForTable;
	
	/*
	 * Fields to hold information about the object
	 */
	
	private String itemName; // Name of the current line item. 
	private int currentStock; // The current amount of stock available. 
	//private Calendar nextShipment; // The date of the next expected shipment. 
	//private int nextShipmentAmount; // The amount of stock expected in the next shipment. 
	private int amountUsedToday;  // The amount of stock used for the current business day.
	private PriorityQueue<PendingOrder> pendingOrderQueue = new PriorityQueue<PendingOrder>();  // queue of PendingOrder objects.
	
	/*
	 * Line item constructor.  When a new line item is created, use the values entered from the modal window, and set the 
	 * expected ship date to None, since no date has been entered for a brand new line item.
	 */
	
	public LineItem(String name, Integer stock) {
		this.itemName = name;
		this.currentStock = stock;
		
		this.itemNameForTable = new SimpleStringProperty(name);
		this.stockForTable = new SimpleIntegerProperty(stock);
		this.nextShipmentForTable = new SimpleStringProperty("None");
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}
	public LocalDate getNextShipment() {
		return pendingOrderQueue.peek().getExpectedArrival();
	}
	// removing -- we should only add to next shipments through the queue
	//public void setNextShipment(Calendar nextShipment) {
	//	this.nextShipment = nextShipment;
	//}
	public int getNextShipmentAmount() {
		return pendingOrderQueue.peek().getExpectedAmount();
	}
	// removing -- we should only add to next shipments through the queue
	//public void setNextShipmentAmount(int nextShipmentAmount) {
	//	this.nextShipmentAmount = nextShipmentAmount;
	//}
	public int getAmountUsedToday() {
		return amountUsedToday;
	}
	public void setAmountUsedToday(int amountUsedToday) {
		this.amountUsedToday = amountUsedToday;
	}
	public String getItemNameForTable() {
		return itemNameForTable.get();
	}

	public void setItemNameForTable(String itemNameForTable) {
		this.itemNameForTable = new SimpleStringProperty(itemNameForTable);
	}

	public Integer getStockForTable() {
		return stockForTable.get();
	}

	public void setStockForTable(Integer stockForTable) {
		this.stockForTable = new SimpleIntegerProperty(stockForTable);
	}

	public String getNextShipmentForTable() {
		return nextShipmentForTable.get();
	}

	public void setNextShipmentForTable(String nextShipmentForTable) {
		this.nextShipmentForTable = new SimpleStringProperty(nextShipmentForTable);
	}
	
	/*
	 * Create a new pending order object, and then add it to the pending order list.
	 */
	
	public void addToPendingShipments(int expectedAmount, LocalDate expectedDate) {	
		PendingOrder pending = new PendingOrder(expectedAmount, expectedDate);
		pendingOrderQueue.add(pending);
	}
	
	// removes next shipment from the queue and returns it
	public PendingOrder pollNextShipment() {
		return pendingOrderQueue.poll();
	}
	
}
