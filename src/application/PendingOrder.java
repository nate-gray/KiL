package application;

import java.util.Calendar;

/*
 * This class is used to create a new pendingOrder object. 
 * These objects will be stored in an ArrayList of the LineItem object.
 */

public class PendingOrder {
	
	private int expectedAmount; // Amount expected in the pending order.
	private Calendar expectedArrival; // Expected arrival date of the shipment. 
	
	public PendingOrder(int amount, Calendar date) {
		expectedAmount = amount;
		expectedArrival = date;
	}
	
	public int getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(int expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public Calendar getExpectedArrival() {
		return expectedArrival;
	}
	public void setExpectedArrival(Calendar expectedArrival) {
		this.expectedArrival = expectedArrival;
	}

}
