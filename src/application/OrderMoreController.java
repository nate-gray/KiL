package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderMoreController {
	
	@FXML
	private Label itemLbl;
	private LineItem item;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextField amountExpectedTxt;
	
	@FXML
	private Button enterShipmentBtn;
	
	@FXML
	private Button closeBtn;
	
	public void setLabel(LineItem newItem) {
		this.itemLbl.setText(newItem.getItemName());
		this.item = newItem;
	}
	
	public void handleEnterShipmentBtn(ActionEvent event) {
		// add item into inventory
		this.item.addToPendingShipments(
				Integer.parseInt(amountExpectedTxt.getText()), 
				this.datePicker.getValue()); // gets LocalDate object from datePicker
	}
	
	public void handleDatePicker(ActionEvent event) {
		// the datePicker seems to be working fine without any code here
	}
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

}
