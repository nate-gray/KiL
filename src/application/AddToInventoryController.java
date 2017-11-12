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
	
	public void handleAddToStockBtn(ActionEvent event) {
		// add to current stock
		this.selectedItem.setCurrentStock(selectedItem.getCurrentStock() + 2);
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
	}
	
	public void setSelectedItem(LineItem item) {
		this.selectedItem = item;
	}
	public void setLabel(LineItem item) {
		this.itemLbl.setText(item.getItemName());
	}

}
