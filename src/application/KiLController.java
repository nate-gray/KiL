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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	public Button addLineItemBtn;
	
	@FXML
	private Button addToInventoryBtn;
	
	@FXML
	private Button enterAmountUsedBtn;
	
	@FXML
	private Button orderMoreBtn;
	
	@FXML
	private Button removeBtn;
	
	@FXML
	private Button filterBtn;
	
	@FXML
	private TextField filterTxt;
	
	@FXML
	private MenuItem addNewLineItemMenu;
	
	@FXML
	private MenuItem removeLineItemMenu;
	
	@FXML
	private MenuItem sortAlphabetically;
	
	@FXML
	private MenuItem sortByCurrentStock;
	
	@FXML
	private MenuItem sortByShipDate;
	
	@FXML
	private MenuItem importData;
	
	@FXML
	private MenuItem exportData;
	
	@FXML
	private MenuItem exit;
	
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
		
		/*
		 * Display the modal window
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Add New Line Item");
		stage.setScene(new Scene(parent, 282, 231));
		stage.setResizable(false);
		stage.showAndWait();
		
		// doesn't need a refresh, AddNewLineItemController adds to the item list (lineItemObservableList) directly
	}
	
	public void addToInventoryClicked() throws IOException {
		
		/*
		 * Get the selected item in the table.
		 */
		
		if(theTable.getSelectionModel().getSelectedItem() == null) {
			displayNoItemSelectedWarning();
		}
		LineItem selectedLineItem = theTable.getSelectionModel().getSelectedItem();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddToInventoryView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Set the label in the new window to the selected line item. 
		 * TODO: Also set the expected amount label.
		 */
		
		AddToInventoryController addToInventoryController = fxmlLoader.<AddToInventoryController>getController();
		addToInventoryController.setLabel(selectedLineItem);
		
		// also set the line item so it can be added to
		addToInventoryController.setSelectedItem(selectedLineItem);
		
		/*
		 * Display the modal window for adding to the inventory.
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Add To Inventory");
		stage.setScene(new Scene(parent, 315, 209));
		stage.setResizable(false);
		stage.showAndWait();
		
		theTable.refresh();
	}
	
	public void enterAmountUsedClicked() throws IOException {
		/*
		 * Get the selected item in the table.
		 */
		
		if(theTable.getSelectionModel().getSelectedItem() == null) {
			displayNoItemSelectedWarning();
		}
		LineItem selectedLineItem = theTable.getSelectionModel().getSelectedItem();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AmountUsedView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Set the label in the new window to the selected line item. 
		 */
		
		AmountUsedController amountUsedController = fxmlLoader.<AmountUsedController>getController();
		amountUsedController.setLabel(selectedLineItem);
		
		/*
		 * Display the modal window for entering the amount used. 
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Enter Amount Used");
		stage.setScene(new Scene(parent, 316, 184));
		stage.setResizable(false);
		stage.showAndWait();
		
		theTable.refresh();
	}
	
	public void orderMoreClicked() throws IOException {
		/*
		 * Get the selected item in the table.
		 */
		
		if(theTable.getSelectionModel().getSelectedItem() == null) {
			displayNoItemSelectedWarning();
		}
		LineItem selectedLineItem = theTable.getSelectionModel().getSelectedItem();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderMoreView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Set the label in the new window to the selected line item. 
		 */
		
		OrderMoreController orderMoreController = fxmlLoader.<OrderMoreController>getController();
		orderMoreController.setLabel(selectedLineItem);
		
		/*
		 * Display the modal window for ordering more. 
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Order More");
		stage.setScene(new Scene(parent, 418, 243));
		stage.setResizable(false);
		stage.showAndWait();
		
		// doesn't need a refresh since the order queue is (currently) hidden from the user
	}
	
	public void removeClicked() throws IOException {
		/*
		 * Get the selected item in the table.
		 */
		
		if(theTable.getSelectionModel().getSelectedItem() == null) {
			displayNoItemSelectedWarning();
		}
		LineItem selectedLineItem = theTable.getSelectionModel().getSelectedItem();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveItemView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Set the label in the new window to the selected line item. 
		 */
		
		RemoveItemController removeItemController = fxmlLoader.<RemoveItemController>getController();
		removeItemController.setLabel(selectedLineItem);
		
		removeItemController.setAppMainObservableList(lineItemObservableList);  
		
		/*
		 * Display the modal window for removing an item. 
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Remove Item");
		stage.setScene(new Scene(parent, 296, 159));
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	public void displayNoItemSelectedWarning() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoItemSelectedView.fxml"));
		Parent parent = fxmlLoader.load();
		
		/*
		 * Display the modal window for adding to the inventory.
		 */
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Add To Inventory");
		stage.setScene(new Scene(parent, 375, 84));
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	public void handleSortAlphabetically() {
		
	}
	
	public void handleSortByStock() {
		
	}
	
	public void handleSortByDate() {
		
	}
	
	public void handleImportData() {
		
	}
	
	public void handleExportData() {
		
	}
	
	public void handleExit() {
		
	}
	
	public void handleFilterBtn() {
		
	}
		
}
