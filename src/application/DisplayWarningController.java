package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DisplayWarningController {
	
	@FXML
	private Button okayBtn;
	
	@FXML 
	private Label messageLbl;
	
	public void initialize(String message) {
		messageLbl.setText(message);
	}
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) okayBtn.getScene().getWindow();
		stage.close();
	}

}
