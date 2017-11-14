package application;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.sun.javafx.collections.ObservableListWrapper;

/*
 * This class is used to contain the data for the line items in the table of the GUI. 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LineItem")

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
	public LineItem() {
		
	}
	
	public LineItem(String name, Integer stock) {
		this.itemName = name;
		this.currentStock = stock;
		
		this.itemNameForTable = new SimpleStringProperty(name);
		this.stockForTable = new SimpleIntegerProperty(stock);
		this.nextShipmentForTable = new SimpleStringProperty("None");
	}
	
//	@XmlAttribute(name = "itemName")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
//	@XmlAttribute(name = "stock")
	public int getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
		this.stockForTable = new SimpleIntegerProperty(currentStock); /// what does this do? (Andy)
	}
//	public String getItemNameForTable() { /// is this needed/used? (Andy)
//		return itemNameForTable.get();
//	}

	public void setItemNameForTable(String itemNameForTable) { /// is this needed/used? (Andy)
		this.itemNameForTable = new SimpleStringProperty(itemNameForTable);
	}

	public Integer getStockForTable() { /// is this needed/used? (Andy)
		return stockForTable.get();
	}

//	public String getNextShipmentForTable() { /// is this needed/used? (Andy)
//		return nextShipmentForTable.get();
//	}

	public void setNextShipmentForTable(String nextShipmentForTable) { /// is this needed/used? (Andy)
		this.nextShipmentForTable = new SimpleStringProperty(nextShipmentForTable);
	}
	
	/*
	 * Create a new pending order object, and then add it to the pending order list.
	 */
	
	public void addToPendingShipments(int expectedAmount, LocalDate expectedDate) {	
		PendingOrder pending = new PendingOrder(expectedAmount, expectedDate);
		pendingOrderQueue.add(pending);
	}
	
//	public PriorityQueue<PendingOrder> getPendingOrders(){
//		return this.pendingOrderQueue;
//	}
	
	
	// removes next shipment from the queue and returns it
	public PendingOrder removeNextShipment() {
		return pendingOrderQueue.poll();
	}
	
}
