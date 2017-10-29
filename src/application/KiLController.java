package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KiLController implements Initializable {
	
	/*
	 * Initialize the fields so that the controller is able to communicate with the view. 
	 */
	
	@FXML
	public TableView<LineItem> theTable;
	
	@FXML
	public TableColumn<LineItem, String> lineItemColumn;
	
	@FXML
	public TableColumn<LineItem, Integer> stockColumn; 
	
	@FXML
	public TableColumn<LineItem, String> nextShipmentColumn;
	
	@FXML
	public Button addBtn;
	
	/*
	 * Create a new observable list that will populate the table view. 
	 * This list will contain all of the LineItem objects.
	 * Allow access to the list through the getter.
	 */
	private ObservableList<LineItem> lineItemObservableList = FXCollections.observableArrayList();
	
	public ObservableList<LineItem> getItemsInList() {
		return lineItemObservableList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 * Initialize the table view so that objects can be added to the view. 
	 * The argument passed to the PropertyValueFactory must exactly match the String/Integer Value properties in the Line Item objects. 
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lineItemColumn.setCellValueFactory(new PropertyValueFactory<LineItem, String>("itemNameForTable"));
		stockColumn.setCellValueFactory(new PropertyValueFactory<LineItem, Integer>("stockForTable"));
		nextShipmentColumn.setCellValueFactory(new PropertyValueFactory<LineItem, String>("nextShipmentForTable"));
		theTable.setItems(lineItemObservableList);	
	}
	
	/*
	 * When the add new line item button is clicked, open the new view. 
	 * The new view will use its own controller. 
	 * Make the new view modal. 
	 * 
	 */
	
	public void addNewLineItemClicked() throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewLineItemView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Pass the main observable list to the new controller. 
		 * The new controller will modify the list. 
		 */
		
		AddNewLineItemController addLineItemController = fxmlLoader.<AddNewLineItemController>getController();
		addLineItemController.setAppMainObservableList(lineItemObservableList);  
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Add New Line Item");
		stage.setScene(new Scene(parent, 282, 231));
		stage.setResizable(false);
		stage.showAndWait();
	}
		
}
