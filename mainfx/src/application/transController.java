package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class transController extends UserInfo implements Initializable {

	@FXML
	private Label date,welcome,balance,aclab,name2;
	@FXML
	private Label lb;
	@FXML
	private ListView<Label> list;
	
	FileReader fileReader;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	public void Gettrans(String[] s) {
		welcome.setText("Welcome "+s[4]);
		date.setText(date());
		aclab.setText(s[2]);
		try {
			fileReader = new FileReader(s[2]+"trans.txt");
		} catch (FileNotFoundException e1) {
			
		}
		
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try {
        	balance.setText(bufferedReader.readLine());
			while((line = bufferedReader.readLine()) != null) {
				lb=new Label(line);
				list.getItems().add(lb);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}



}
