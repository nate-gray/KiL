package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlType;

import javafx.collections.ObservableList;



public class ReadWrite {

	 LineItemsList items = new LineItemsList();
	 
	 
	 public void testing(ObservableList list){

		 items.setLineItems(new ArrayList<LineItem>());
		 for(int i = 0; i < list.size(); i++){
			 items.getLineItems().add((LineItem) list.get(i));
		 }
		 
	 }
	
	private static final String LINEITEM_XML = "./lineitem.xml";
	
	public void writeData() throws JAXBException{
		
		JAXBContext context = JAXBContext.newInstance(LineItemsList.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(items, new File(LINEITEM_XML));
	}
	
	

}
