package application;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * This class is used as an adapter to allow reading and writing LocalDate objects to and from XML. 
 */

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}
	
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}