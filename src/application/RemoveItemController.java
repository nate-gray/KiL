package application;

import javafx.collections.ObservableList;
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

	/*
	 * The main observable list from the main controller.
	 */

	private ObservableList<LineItem> appMainObservableList;

	private LineItem item;
	
	public void initialize(LineItem item) {
		this.itemLbl.setText(item.getItemName());
		this.item = item;
	}
	
	public void handleDeleteBtn(ActionEvent event) {
		appMainObservableList.remove(item);
	}
	
	public void handleCancelBtn(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	public void setAppMainObservableList(ObservableList<LineItem> lineItemObservableList) {
		this.appMainObservableList = lineItemObservableList;	
	}

}
