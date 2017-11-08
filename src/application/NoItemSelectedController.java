package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NoItemSelectedController {
	
	@FXML
	private Button okayBtn;
	
	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) okayBtn.getScene().getWindow();
		stage.close();
	}

}
