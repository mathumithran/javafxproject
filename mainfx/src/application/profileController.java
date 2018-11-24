package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class profileController extends UserInfo implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	@FXML
	private Label n1,acno,mail,bal;
	
	public void GetUser(String s) {
		n1.setText(s);
		
	}
	
}
