package application;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewLineItemController {
	
	/*
	 * Initialize fields so the view can communicate with the controller. 
	 */
	
	@FXML
	public TextField itemName;
	
	@FXML
	public TextField initialStock;
	
	@FXML
	public Button addLineItemBtn;
	
	@FXML 
	public Button closeBtn;
	
	@FXML
	public Label invalidNameWarning;
	
	@FXML
	public Label invalidNumberWarning;
	
	/*
	 * The main observable list from the main controller.
	 */
	
	private ObservableList<LineItem> appMainObservableList;
	
	/*
	 * Add the new object to the list of items. 
	 */
	
	public void handleAddLineItemBtn(ActionEvent event) {
		
		/*
		 * Hide any left over warnings. 
		 */
		invalidNumberWarning.setVisible(false);
		invalidNameWarning.setVisible(false);
		
		/*
		 * Try adding the values entered by the user. 
		 * If no name is entered, throw a warning. 
		 * If a non-number is entered for the stock, throw a warning.
		 */
		
		try{
			addLineItem();
		} catch(NumberFormatException e) {
			e.printStackTrace();
			invalidNumberWarning.setVisible(true);
		} catch (Exception e) {
			invalidNameWarning.setVisible(true);
			e.printStackTrace();
		}
	}
	
	/*
	 * Add New Line Item button logic.
	 */
	
	private void addLineItem() throws Exception {
		
		/*
		 * Get the text from the text field for the item name. 
		 * If the text field is blank, then throw an exception. 
		 * TODO: it is still possible to enter spaces as a title. 
		 * TODO: Limit the number of characters.
		 */
		
		String name = itemName.getText();
		if(name.length() == 0) {
			throw new Exception();
		}
		int stock = Integer.parseInt(initialStock.getText()); // Get the text from the field and convert it to an int. 
		LineItem newItem = new LineItem(name, stock); //Create a new LineItem object
		appMainObservableList.add(newItem);  //Add the new object to the main observable list. 
	}
	
	/*
	 * Close the modal window.
	 */
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

	/*
	 * Set the observable list for this controller to the main observable list. 
	 */
	
	public void setAppMainObservableList(ObservableList<LineItem> lineItemObservableList) {
		this.appMainObservableList = lineItemObservableList;	
	}
	

}
