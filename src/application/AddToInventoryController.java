package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AddToInventoryController {

	@FXML
	private Label itemLbl;

	@FXML
	private Label expectedAmountLbl;

	@FXML 
	private TextField customTxtFld;

	@FXML
	private Button addToStockBtn;

	@FXML
	private Button closeBtn;

	@FXML
	private RadioButton expectedRadio;

	@FXML
	private RadioButton customRadio;

	private LineItem selectedItem;

	public void handleAddToStockBtn(ActionEvent event) throws Exception {
		
		// make a variable for the new stock, starting with the current stock. We'll add to it later
		int newStock = this.selectedItem.getCurrentStock();
		
		if(expectedRadio.isSelected()) {
		// add next expected shipment to current stock
			PendingOrder nextShipment = this.selectedItem.removeNextShipment();
			if(nextShipment == null) {
				/// todo: need to figure out exception handling, I think we should save for end though
				//this.parent.
				throw new Exception("No next shipment in queue");
			}
			newStock += nextShipment.getExpectedAmount();
		}
		else if(customRadio.isSelected()) {
			// add custom amount to current stock
			newStock += Integer.parseInt(customTxtFld.getText());
		}
		else { /// we should have one default
			throw new Exception("No radio button was selected");
		}
		
		if(newStock <= 999) {
			this.selectedItem.setCurrentStock(newStock);
		}
		else { // new stock > 999
			throw new Exception("Stock cannot exceed 999");
		}
		
		// close this extra window once the user clicks "Add to Stock"
		handleCloseBtn(null);
	}

	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

	/*
	 * If the expected radio button is chosen, make sure the custom button is deselected.
	 * Disable the text field so the user cannot enter any values.
	 * Enable the expected label.
	 */
	public void handleExpectedRadio(ActionEvent event) {
		expectedRadio.setSelected(true);
		customRadio.setSelected(false);
		customTxtFld.setDisable(true);
		expectedAmountLbl.setDisable(false);
	}

	/*
	 * If the custom radio button is chosen, make sure the expected button is deselected. 
	 * Disable the expected label.
	 */
	public void handleCustomRadio(ActionEvent event) {
		expectedRadio.setSelected(false);
		customRadio.setSelected(true);
		customTxtFld.setDisable(false);
		expectedAmountLbl.setDisable(true);
		customTxtFld.setEditable(true);
	}
	
	// set up text, item, and defaults for this Controller
	public void initialize(LineItem item) {
		this.itemLbl.setText(item.getItemName());
		this.selectedItem = item;
		if(item.hasNextShipment()) {
			handleExpectedRadio(null); // if there are shipments in the queue, set them to default radio 
		}
		else {
			handleCustomRadio(null); // set the custom shipment
		}
	}

}
