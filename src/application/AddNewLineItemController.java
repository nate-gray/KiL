package application;


import java.io.IOException;

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

	private KiLController parent;

	/*
	 * Add the new object to the list of items. 
	 */

	public void handleAddLineItemBtn(ActionEvent event) throws IOException {
		/*
		 * Try adding the values entered by the user. 
		 * If no name is entered, throw a warning. 
		 * If a non-number is entered for the stock, throw a warning.
		 */

		/*
		 * Get the text from the text field for the item name. 
		 * If the text field is blank, then throw an exception. 
		 * TODO: it is still possible to enter spaces as a title. 
		 * TODO: Limit the number of characters.
		 */
		String name = itemName.getText();

		// check name length is 1-20
		if(name.length() < 1 || name.length() > 20) {
			this.parent.displayWarning("Name length must be between 1 and 20 characters");
			return;		
		}
		
		// check that name is alphanumeric
		for(int i = 0; i < name.length(); i++) {
			if(!Character.isLetterOrDigit(name.charAt(i))) {
				this.parent.displayWarning("Item name must be alphanumeric");
				return;
			}
		}
		
		// try to add to item list, catching exceptions
		try {
			int stock = Integer.parseInt(initialStock.getText()); // Get the text from the field and convert it to an int.
			if(stock < 0 || stock > 999) { 
				this.parent.displayWarning("Stock must be between 0 and 999");
				return;
			}
			LineItem newItem = new LineItem(name, stock); //Create a new LineItem object
			appMainObservableList.add(newItem);  //Add the new object to the main observable list. 
		}
		catch(NumberFormatException e) {
			this.parent.displayWarning("Stock must be an integer");
			return;
		}
		catch(Exception e) {
			this.parent.displayWarning(e.toString());
			return;		
		}

		// Close this window once the user clicks "Add Line Item"
		handleCloseBtn(null);
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

	public void initialize(KiLController parent, ObservableList<LineItem> lineItemObservableList) {
		this.parent = parent;
		this.appMainObservableList = lineItemObservableList;	
	}


}
