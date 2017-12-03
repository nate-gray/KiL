package application;

import java.io.IOException;
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
	
	private KiLController parent; // reference to parent controller for displaying warnings

	public void handleAddToStockBtn(ActionEvent event) throws IOException {
		
		// make a variable for the new stock, starting with the current stock. We'll add to it later
		int stock = this.selectedItem.getCurrentStock();
		
		if(expectedRadio.isSelected()) {
			if(!this.selectedItem.hasNextShipment()) {
				this.parent.displayWarning("No next shipment in queue");
				return;
			}
			int newStock = this.selectedItem.getNextShipmentAmount();
			if(stock + newStock > 999) {
				this.parent.displayWarning("Shipment cannot make stock exceed 999");
				return;
			}
			// remove item from queue
			this.selectedItem.removeNextShipment();
			
			// add next expected shipment to current stock
			stock += newStock;
		}
		else if(customRadio.isSelected()) {
			try {
				int newStock = Integer.parseInt(customTxtFld.getText());
				if(newStock < 1 || newStock > 999) {
					this.parent.displayWarning("Shipment must be between 1 and 999");
					return;	
				}
				else if (stock + newStock > 999) {
					this.parent.displayWarning("Shipment cannot make stock exceed 999");
					return;
				}
				stock += newStock;
			}
			catch(NumberFormatException e) {
				this.parent.displayWarning("Shipment must be an integer");
				return;
			}
		}
		this.selectedItem.setCurrentStock(stock);		
		
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
	public void initialize(KiLController parent, LineItem item) {
		this.parent = parent;
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
