package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
	
	public void handleAddToStockBtn(ActionEvent event) {
		System.out.println("Test");
	}
	
	public void handleCloseBtn(ActionEvent event) {
		
	}

}
