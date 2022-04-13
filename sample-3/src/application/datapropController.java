package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;





public class datapropController  implements Initializable {
	

	private String s;
	@FXML
	private ComboBox<String> ddrange;// drop down range data properties
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { 
		ObservableList<String> list= FXCollections.observableArrayList("string", "normalizedString", "boolean", "decimal", "float", "double", "integer", "nonNegativeInteger", "positiveInteger", "nonPositiveInteger", "negativeInteger", "long", "int", "short", "byte", "unsignedLong", "unsignedInt", "unsignedShort", "unsignedByte", "hexBinary", "base64Binary", "dateTime", "time", "date", "gYearMonth", "gYear", "gMonthDay", "gDay", "gMonth", "anyURI", "token", "language", "NMTOKEN", "Name", "NCName");
		ddrange.setItems(list);
	}
	
	@FXML
    void rangedd(ActionEvent event) {
		 s=ddrange.getSelectionModel().getSelectedItem().toString();
//			System.out.print(s);/
    }
	
	
	
}
