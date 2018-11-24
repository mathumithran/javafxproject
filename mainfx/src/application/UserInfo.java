package application;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
public class UserInfo extends loginController implements Initializable, Runnable {
	@Override
	public void run() {
		try {
			loginController lc = new loginController();
			lc.random();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	} 
	
	public void Getuser(String[] s) throws IOException {
		welcome.setText("Welcome "+s[4]);
		date.setText(date());
		FileReader fileReader = new FileReader(s[2]+"trans.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        balance.setText(bufferedReader.readLine());
		bufferedReader.close();
		aclab.setText(s[2]);
	}
	public void trans10(ActionEvent e)  throws Exception{
		int i = 0;
		String fileName ="temp_val.txt";
		String line = null;
		 String a[]=new String[20];
		 FileReader fileReader =new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	        	a[i]=line;
	            System.out.println(line);
	            i++;
	        } 
	        bufferedReader.close(); 
	       ((Node)e.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader load = new FXMLLoader();
		Pane root = load.load(getClass().getClassLoader().getResource("application/trans.fxml").openStream());
		transController ui1 = (transController)load.getController();
		ui1.Gettrans(a);
		Scene scene = new Scene(root,1000,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void state(ActionEvent e) throws Exception {
		String fileName = "temp_val.txt";
		 String line = null;
		 int i=0;
		 String a[]=new String[10];
		  FileReader fileReader =new FileReader(fileName);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      while((line = bufferedReader.readLine()) != null) {
	      	a[i]=line;
	          i++;
	      } 
	      bufferedReader.close();  
		Stage primaryStage = new Stage();
		FXMLLoader load = new FXMLLoader();
		((Node)e.getSource()).getScene().getWindow().hide();
		Pane root = load.load(getClass().getClassLoader().getResource("application/loginmain.fxml").openStream());
		UserInfo ui = (UserInfo)load.getController();
		ui.Getuser(a);
		Scene scene = new Scene(root,1000,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	FXMLLoader load;
	Stage primaryStage;
public void pay(ActionEvent e)throws Exception {
	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/payment.fxml"));
	Scene scene = new Scene(root,1000,600);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
	}


public void payconfirm(ActionEvent e) throws Exception {
	String fileName = "temp_val.txt";
	 String line;
	 int i=0,flag = 0,permision=1;
	 String a[]=new String[10];
	  FileReader fileReader =new FileReader(fileName);
     BufferedReader bufferedReader = new BufferedReader(fileReader);
     while((line = bufferedReader.readLine()) != null) {
     	a[i]=line;
         i++;} 
     bufferedReader.close(); 
    String paye_ac=a[2];
    
    //mytrans
 	FileReader fr = new FileReader(a[2]+"trans.txt");
 	BufferedReader bf = new BufferedReader(fr);
 	int k=0;
 	 String paye[]=new String[30];
 	int preamount = Integer.parseInt(bf.readLine());
 	while((line = bf.readLine()) != null) {
 		System.out.println(line);
      	paye[k]=line;
          k++;
      } bf.close();
      
      //checking
      if(amount.getText().equals("")) {
    	  amounterr.setText("*Enter amount") ;
      }
      else {
     int value = Integer.parseInt(amount.getText());
     
   if(preamount>value&&value>0&&value<=10000&&!(ifsc.getText().equals(""))) {
	String ben = benac.getText(),statement = a[4]+pur.getText();
	loginController lc = new loginController();
	
	
	//ben account
	
	
	try {
		FileReader fr1 = new FileReader(ben+"trans.txt");
		System.out.println("Account already exist");
		BufferedReader bf1 = new BufferedReader(fr1);
		i=0;
		String b[]=new String[30];
		int preamount_ben = Integer.parseInt(bf1.readLine());
		while((line = bf1.readLine()) != null) {
			System.out.println("ben acc :"+line);
	     	b[i]=line;
	         i++;
	     } bf1.close();
	     BufferedWriter trans = new BufferedWriter(new FileWriter(ben+"trans.txt"));
	     trans.write((preamount_ben+value)+"\r\n"+lc.space(statement)+" "+lc.space("-")+" "+lc.space(""+value)+"\r\n");
		for(int j=0;j<i;j++) {
			trans.write(b[i]+"\r\n");
		}trans.close();
	} catch (FileNotFoundException e1)
	{
		
        BufferedReader binfo = new BufferedReader(new FileReader("info.txt"));
      
        while((line = binfo.readLine()) != null) {
        	String rollno=binfo.readLine();
        	if(benac.getText().equalsIgnoreCase(rollno)) {
        		flag=1;
        		break;}}
        if(flag==1) {
		BufferedWriter trans = new BufferedWriter(new FileWriter(ben+"trans.txt"));
		trans.write((1000+value)+"\r\n"+lc.space(statement)+" "+lc.space("-")+" "+lc.space(""+value)+"\r\n");
		trans.write(lc.space("Initial balance")+" "+lc.space("-")+" "+lc.space("1000")+"\r\n");
		trans.close();}
      else {
    	  permision = 0;
    	  benacerr.setText("*Invalid account no.");
      }
	}
	if(permision==1) {
	statement = benname.getText()+pur.getText();
     BufferedWriter trans = new BufferedWriter(new FileWriter(paye_ac+"trans.txt"));
     trans.write((preamount-value)+"\r\n"+lc.space(statement)+" "+lc.space(""+value)+" "+lc.space("-")+"\r\n");
     
	for(int j=0;j<k;j++) {
		trans.write(paye[j]+"\r\n");}
	trans.close();}
	state(e);

     }
   else if(value>10000||value<0){
	   amounterr.setText("*Amount should [1 to 10000]") ;
   }
   else if(ifsc.getText().equals("")) {
	   ifscerr.setText("*Enter IFSC code");
   }
   else {
		amounterr.setText("*Insufficient Balance");}
      }
}


public void cancel(ActionEvent e)throws Exception{
	state(e);
}
public void profile(ActionEvent e) throws Exception {  
     Stage primaryStage = new Stage();
		FXMLLoader load = new FXMLLoader();
		Pane root = load.load(getClass().getClassLoader().getResource("application/details.fxml").openStream());
		Scene scene = new Scene(root,1000,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	     
}

public void save(ActionEvent e) throws Exception {
	String fileName = "temp_val.txt";
	 String line = null;
	 int i=0;
	 String a[]=new String[10];
	  FileReader fileReader =new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    while((line = bufferedReader.readLine()) != null) {
    	a[i]=line;
        i++;
    }
    bufferedReader.close();
    n1.setText(a[4]);
    n2.setText(a[5]);
    n3.setText(a[2]);
	try {
		fileReader = new FileReader(a[2]+"trans.txt");
	} catch (FileNotFoundException e1) {
		
	}
	
    BufferedReader bufferedReader1 = new BufferedReader(fileReader);
    String line1;
    try {
    	n4.setText(bufferedReader1.readLine());
	} catch (IOException event) {} 
}

public void change(ActionEvent e) throws Exception {
	 Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Changepass.fxml"));
    Scene scene = new Scene(root,1000,600);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();

}
public void nconf(ActionEvent e) throws Exception {
	 String fileName = newuser.getText()+oldpass.getText()+".txt";
	 String line,x=npass.getText(),y=cpass.getText(),z=account.getText();
	 String a[]=new String[10];
	 int i=0;
	    try {
	        BufferedWriter wr = new BufferedWriter(new FileWriter("temp_val.txt"));
	        FileReader fileReader =new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        while((line = bufferedReader.readLine()) != null) {
	        	a[i]=line;
	        	wr.write(line+"\r\n");
	            System.out.println(line);
	            i++;
	        } wr.close();
	        bufferedReader.close();  
	        System.out.println("login successfully");
	    }
	    catch(FileNotFoundException ex) {
	    	status.setText("*Incorret user name or password");               
	    }
	    catch(IOException ex) {
	    	status.setText("*Incorret user name or password");                
	        
	    }
	    model l = new model();
		int check = l.comp( npass.getText(), cpass.getText());
		System.out.println(check);

	if(newuser.getText().equals(a[0])&& oldpass.getText().equals(a[3])&&is_Valid_Password(npass.getText())&&check==1) {
		File file = new File(a[0]+a[3]+".txt");
		File newFile = new File(a[0]+npass.getText()+".txt");
	        if(file.renameTo(newFile)){
	        	BufferedWriter out = new BufferedWriter(new FileWriter(newFile));
				BufferedWriter out1 = new BufferedWriter(new FileWriter(account.getText()+"for.txt"));
				System.out.println(a.length);
				for(i=0;i<a.length;i++) {
					if(i==3) {
						out.write(npass.getText()+"\r\n");
						out1.write(npass.getText()+"\r\n");
					}
					else {
				out.write(a[i]+"\r\n");
				out1.write(a[i]+"\r\n");
				}}
				out.close();out1.close();
	        	state(e);
	            System.out.println("File rename success");;
	        }else{
	            System.out.println("File rename failed");
	        }
		state(e);
	}
	else if(check!=0) {status.setText("*Mismatching password");}
	else if(!is_Valid_Password(npass.getText())) {status.setText("*Invalid password");}
}
@FXML
private javafx.scene.control.TextField benname,benac,amount,pur,ifsc,newuser,account,oldpass,npass,cpass;
@FXML
private Label date,welcome,balance,aclab,name2,benacerr,amounterr,ifscerr,aclab1;

@FXML
private Label n1,n2,n3,n4,status;

@FXML
private Tab acctab;
}