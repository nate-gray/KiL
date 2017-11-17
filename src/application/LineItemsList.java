package application;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * This class is used to create a temporary list to write to the XML file on import. 
 * It is also used to import data by converting the XML data into a consumable list. 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "items") // This is the root node in the xml for each object. 
public class LineItemsList {
 
    @XmlElement(name = "item", type = LineItem.class)
    private List<LineItem> items = new ArrayList<LineItem>();
     
    public LineItemsList() {}
 
    public LineItemsList(List<LineItem> items) {
        this.items = items;
    }
 
    public List<LineItem> getLineItems() {
        return items;
    }
 
    public void setLineItems(List<LineItem> items) {
        this.items = items;
    }   
}

