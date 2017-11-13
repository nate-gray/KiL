package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AmountUsedController {
	
	@FXML
	private Label itemLbl;
	
	@FXML
	private TextField amountUsedTxt;
	
	@FXML
	private Button enterAMountUsedBtn;
	
	@FXML
	private Button closeBtn;
	
	public void setLabel(LineItem item) {
		// set name of item
		this.itemLbl.setText(item.getItemName());
	}
	
	public void handleEnterAmountUsedBtn(ActionEvent event) {
		// if amount used is <= current quantity
		// subtract the amount used from current quantity
		
		// else, display an error
		
	}
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}


}
