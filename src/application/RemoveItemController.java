package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RemoveItemController {
	
	@FXML
	private Label itemLbl;
	
	@FXML
	private Button deleteBtn;
	
	@FXML
	private Button cancelBtn;
	
	public void setLabel(LineItem item) {
		this.itemLbl.setText(item.getItemName());
	}
	
	public void handleDeleteBtn(ActionEvent event) {
		
	}
	
	public void handleCancelBtn(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

}
