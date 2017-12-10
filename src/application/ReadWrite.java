package application;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.ObservableList;



public class ReadWrite {

	/*
	 * This list creates a new temp list for reading and writing data to and from the xml.  
	 */
	LineItemsList items = new LineItemsList();
	
	/*
	 * This is the main observable list for the app. 
	 * We need access locally so we can read from the XML and write to the list. 
	 */
	
	private KiLController parent; // reference to parent controller for displaying warnings
	
	private ObservableList<LineItem> appMainObservableList;
	 
	public void setAppMainObservableList(ObservableList<LineItem> lineItemObservableList) {
			this.appMainObservableList = lineItemObservableList;	
	}
	 
	/*
	 * This creates the local list. 
	 */
	
	public void writeList(ObservableList<LineItem> list){

		 items.setLineItems(new ArrayList<LineItem>());
		 for(int i = 0; i < list.size(); i++){
			 items.getLineItems().add((LineItem) list.get(i));
		 }
		 
	 }
	
	/*
	 * Export the local list to XML. 
	 */
	
	public void writeData(File file) throws JAXBException{
		
		JAXBContext context = JAXBContext.newInstance(LineItemsList.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(items, file);
	}	
	
	/*
	 * Read the XML, and create a temp list from it. 
	 * Iterate through and re-add the data to the main app. 
	 */
	
	public void readData(File file) throws Exception{
		JAXBContext context = JAXBContext.newInstance(LineItemsList.class);
		Unmarshaller um = context.createUnmarshaller();
		LineItemsList importItems = (LineItemsList) um.unmarshal(file);
		
		/*
		 * For each line item in the file, create a new object.
		 * Add the object to the main observable list.
		 */
		
		for(LineItem x : importItems.getLineItems()){
			
			LineItem temp = new LineItem(x.getItemName(), x.getCurrentStock());
			
			/*
			 * If the line item has pending orders, loop through orders and add them. 
			 */
			
			if(x.getPendingOrders() != null){
				for(PendingOrder z : x.getPendingOrders()){
					temp.addToPendingShipments(z.getExpectedAmount(), z.getExpectedArrival());
				}
			}
			
			appMainObservableList.add(temp);
			
			
		}
	}
	
	public boolean checkIfValid(File file) throws Exception{
		JAXBContext context = JAXBContext.newInstance(LineItemsList.class);
		Unmarshaller um = context.createUnmarshaller();
		LineItemsList importItems = (LineItemsList) um.unmarshal(file);
		
		/*
		 * For each line item in the file, create a new object.
		 * Add the object to the main observable list.
		 */
		
		for(LineItem x : importItems.getLineItems()){
			
			/*
			 * Check to make sure the line item name is not greater than 20 characters.
			 * Make sure it is alphanumeric. 
			 * Make sure that the stock value is an int. 
			 */
			
			if(x.getItemName().length() < 1 || x.getItemName().length() > 20){
				this.parent.displayWarning("Item name length must be between 1 and 20 characters.");
				return false;
			}
			
			for(int i = 0; i < x.getItemName().length(); i++){
				if(!Character.isLetterOrDigit(x.getItemName().charAt(i))){
					this.parent.displayWarning("Item name must be alphanumeric");
					return false;
				}
			}
			
			if(x.getCurrentStock() < 0 || x.getCurrentStock() > 999){
				this.parent.displayWarning("Stock must be between 0 and 999");
				return false;
			}
			
			if(x.getPendingOrders() != null){
				for(PendingOrder z : x.getPendingOrders()){					
					if(z.getExpectedAmount() != (int)z.getExpectedAmount()) {
						this.parent.displayWarning("Expected amount must be an integer");
						return false;
					}
					if(z.getExpectedAmount() < 0 || z.getExpectedAmount() > 999){
						this.parent.displayWarning("Stock must be between 0 and 999");
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void initialize(KiLController parent) {
		this.parent = parent;
	}
}
