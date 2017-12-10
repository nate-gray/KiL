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
	
	public void handleDeleteBtn(ActionEvent event) {
		appMainObservableList.remove(item);
		handleCancelBtn(null);
	}
	
	public void handleCancelBtn(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	public void initialize(ObservableList<LineItem> lineItemObservableList, LineItem item) {
		this.appMainObservableList = lineItemObservableList;	
		this.itemLbl.setText(item.getItemName());
		this.item = item;
	}
}