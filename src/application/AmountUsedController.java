package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AmountUsedController {
	
	@FXML
	private Label itemLbl;
	private LineItem item;
	
	@FXML
	private TextField amountUsedTxt;
	
	@FXML
	private Button enterAMountUsedBtn;
	
	@FXML
	private Button closeBtn;
	private KiLController parent;
	
	public void handleEnterAmountUsedBtn(ActionEvent event) throws IOException {
		// Calculate what new stock should be (current - amount used)
		int newStock = item.getCurrentStock() - Integer.parseInt(amountUsedTxt.getText());
		if(newStock >= 0) {
			// if >= 0, subtract the amount used from current quantity
			item.setCurrentStock(newStock);
		}
		// else, display an error
		else {
			this.parent.displayWarning("Stock cannot be negative");
			return;
		}
		handleCloseBtn(null);
	}
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}
	
	public void initialize(KiLController parent, LineItem item) {
		// set name of item
		this.parent = parent;
		this.itemLbl.setText(item.getItemName());
		this.item = item;
	}
}
