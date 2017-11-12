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
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextField amountExpectedTxt;
	
	@FXML
	private Button enterShipmentBtn;
	
	@FXML
	private Button closeBtn;
	
	public void setLabel(LineItem item) {
		this.itemLbl.setText(item.getItemName());
	}
	
	public void handleEnterShipmentBtn(ActionEvent event) {
		// add item into inventory
	}
	
	public void handleDatePicker(ActionEvent event) {
		
	}
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

}
