package application;

import java.util.PriorityQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		setStockForTable(currentStock);
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
	
	public void setStockForTable(int stockForTable) {
		this.stockForTable = new SimpleIntegerProperty(currentStock); /// what does this do? (Andy)
	}
	
	public String getNextShipmentForTable() {
		return nextShipmentForTable.get();
	}

	public void setNextShipmentForTable(String nextShipmentForTable) {
		this.nextShipmentForTable = new SimpleStringProperty(nextShipmentForTable);
	}
	
	public boolean hasNextShipment() {
		return pendingOrderQueue.peek() != null;
	}
	
	// private helper function to update the next shipment date for the table to display
	private void updateNextShipmentForTable() {
		PendingOrder nextOrder = pendingOrderQueue.peek();
		if(nextOrder == null) {
			setNextShipmentForTable("None");
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String nextOrderDate = nextOrder.getExpectedArrival().format(formatter);
			setNextShipmentForTable(nextOrderDate);
		}
	}
	
	/*
	 * Create a new pending order object, and then add it to the pending order list.
	 */
	
	public void addToPendingShipments(int expectedAmount, LocalDate expectedDate) {	
		PendingOrder pending = new PendingOrder(expectedAmount, expectedDate);
		pendingOrderQueue.add(pending);
		updateNextShipmentForTable();
	}
	
	// removes next shipment from the queue and returns it
	public PendingOrder removeNextShipment() {
		PendingOrder nextShipment = pendingOrderQueue.poll(); 
		updateNextShipmentForTable();
		return nextShipment;
	}
	
}
