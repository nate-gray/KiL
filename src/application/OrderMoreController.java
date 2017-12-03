package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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

	// internal LineItem so we know which was selected
	private LineItem item;

	private KiLController parent;

	public void handleEnterShipmentBtn(ActionEvent event) throws IOException {
		// add item into inventory
		try {
			int stock = Integer.parseInt(amountExpectedTxt.getText());
			if(stock < 1 || stock > 999) {
				this.parent.displayWarning("Stock must be between 1 and 999");
				return;
			}
			
			this.item.addToPendingShipments(stock, this.datePicker.getValue());
		}
		catch(NumberFormatException e) {
			this.parent.displayWarning("Stock must be an integer");
			return;
		}
		catch(NullPointerException e) {
			this.parent.displayWarning("Date is missing or incorrectly formatted");
			return;
		}
		catch(Exception e) {  // in case there's another Exception we missed
			this.parent.displayWarning(e.toString());
			return;
		}
		handleCloseBtn(null);
	}

	public void handleDatePicker(ActionEvent event) {
		// the datePicker seems to be working fine without any code here
	}

	public void handleCloseBtn(ActionEvent event) {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}

	public void initialize(KiLController parent, LineItem item) {
		this.parent = parent;
		this.itemLbl.setText(item.getItemName());
		this.item = item;

		// the following code sets the date picker's pattern to yyyy-mm-dd for consistency with ship date sorting
		// note: adapted from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html
		// 		I broke it up into a concrete class so I'd better understand it -- it was anonymous (Andy) 
		class myDateConverter extends StringConverter<LocalDate> // StringConverter is abstract
		{
			String pattern = "yyyy-MM-dd";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);		     
			myDateConverter() { // constructor
				datePicker.setPromptText(pattern.toLowerCase()); // shows "yyyy-mm-dd" when no number entered
			}
			@Override public String toString(LocalDate date) {
				if (date != null) { 
					return dateFormatter.format(date);
				}
				else { 
					return "";
				}
			}
			@Override public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} 
				else {
					return null;
				}
			}
		}
		StringConverter<LocalDate> myConverter = new myDateConverter();
		this.datePicker.setConverter(myConverter);
	}
}
