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
	
	public void readData(File file) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(LineItemsList.class);
		Unmarshaller um = context.createUnmarshaller();
		LineItemsList importItems = (LineItemsList) um.unmarshal(file);
		for(LineItem x : importItems.getLineItems()){
			LineItem temp = new LineItem(x.getItemName(), x.getCurrentStock());
			appMainObservableList.add(temp);
			if(x.getPendingOrders() != null){
				for(PendingOrder z : x.getPendingOrders()){
					temp.addToPendingShipments(z.getExpectedAmount(), z.getExpectedArrival());
				}
			}
		}
	}
}
