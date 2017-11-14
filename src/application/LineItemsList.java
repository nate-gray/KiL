package application;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "items")
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

