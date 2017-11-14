package application;

import java.time.LocalDate;

/*
 * This class is used to create a new pendingOrder object. 
 * These objects will be stored in an ArrayList of the LineItem object.
 */

public class PendingOrder implements Comparable<PendingOrder> {
	
	private int expectedAmount; // Amount expected in the pending order.
	private LocalDate expectedArrival; // Expected arrival date of the shipment. 
	
	public PendingOrder(int amount, LocalDate date) {
		expectedAmount = amount;
		expectedArrival = date;
	}
	
	public int getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(int expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public LocalDate getExpectedArrival() {
		return expectedArrival;
	}
	public void setExpectedArrival(LocalDate expectedArrival) {
		this.expectedArrival = expectedArrival;
	}

	public int compareTo(PendingOrder o) {
		return this.expectedArrival.compareTo(o.expectedArrival);
	}
	
}
