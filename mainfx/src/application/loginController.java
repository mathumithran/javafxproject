package application;

import java.io.BufferedReader;
import javafx.scene.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class loginController implements Runnable
{

	 @FXML
		private ImageView imgview;
	 public void selectimg(ActionEvent event) {
		 FileChooser file = new FileChooser();
		 file.setInitialDirectory(new File("C:\\Users\\Hp\\eclipse-workspace\\mainfx\\src\\application\\img"));
		 File selectedFile = file.showOpenDialog(null);
		 if(selectedFile != null) {
			 System.out.println("file:///"+selectedFile.getPath());
			 Image img = new Image("file:///"+selectedFile.getPath());
			 imgview.setImage(img);
		 }
	 }
	public void button(ActionEvent event) throws Exception {page("application/loginfxml.fxml",event);}
	public void newuser(ActionEvent event) throws Exception {page("application/newuser.fxml");}
	public void home(ActionEvent event) throws Exception{ page("application/login.fxml",event);}
	public void forgetpass(ActionEvent event) throws Exception{page("application/forgetpass.fxml",event);}
	public void reset(ActionEvent event) throws Exception{
		name.setText("");
		pass.setText("");
	}
	
	public void validpass(ActionEvent event) throws Exception {
		String s = newpass.getText();
		System.out.println(newpass.getText());
		 if (is_Valid_Password(s)) { passerr.setText("");}
		 else { passerr.setText("*Not valid password "); }
	}
	
	public void newconf(ActionEvent event) throws Exception {
        if( newpass.getText().equals("")) {passerr.setText("*password should not empty");}
		String s = newpass.getText(),line, name ,rollno;
	    System.out.println(newpass.getText());
			model l = new model();
			int check = l.comp( newpass.getText(), confpass.getText());
			System.out.println(check);
			validpass(event);
			int nameflag=1;
			if(newuser.getText().equals("")) {
				nameerr.setText("*Enter your name");
				nameflag =0;
			}else
				nameerr.setText("");
			if(email.getText().equals("")) {
				emailerr.setText("*Enter your Email-ID");
				nameflag =0;
			}else
				emailerr.setText("");
			if(acno.getText().equals("")) {
					accerr.setText("*Enter your account number");
			}else
					accerr.setText("");
			String avail = newuser.getText()+acno.getText()+"for.txt";
				try{FileReader fileReader =new FileReader(avail);
		        if(head.getText().equals("NEW USER - Registration")) {
			    	accerr.setText("*Account No. already exist");
			    	nameflag =0;}
					}
				    catch(FileNotFoundException ex) {
				    	if(head.getText().equals("Forget Password")) {
				    	accerr.setText("*User name not available");
				    	nameflag =0;}
				    }
				
				FileReader fileReader =new FileReader("info.txt");
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int i=0,flag=0;
	            while((line = bufferedReader.readLine()) != null) {
	            	rollno=bufferedReader.readLine();
	            	if(acno.getText().equalsIgnoreCase(rollno)) {
	            		flag=1;
	            		break;}
	                System.out.println(line);
	                i++;
	            }
	            if(flag!=1) {accerr.setText("*Check your account number");}
	            name=line;
	            System.out.println(line);
				validateJavaDate(DOB.getText());
				upass = newpass.getText();
				String fileName ="temp.txt",forget = acno.getText()+"for.txt";
				String a[]= {newuser.getText(),DOB.getText(),acno.getText(),upass,name,email.getText()};
				if(check==1 && is_Valid_Password(s)&&validateJavaDate(DOB.getText())&&nameflag==1&&flag==1) {
				match.setText("");
				loginController lc = new loginController();
				try {
					FileReader fr = new FileReader( acno.getText()+"trans.txt");
					BufferedReader bf = new BufferedReader(fr);
					i=0;
					String a1[] = new String[30];
					int preamount = Integer.parseInt(bf.readLine());
					while((line = bf.readLine()) != null) {
				     	a1[i]=line;
				         i++;
				     } bf.close();
				     BufferedWriter trans = new BufferedWriter(new FileWriter( acno.getText()+"trans.txt"));
				     trans.write((preamount+1000)+"\r\n");
					for(int j=0;j<i;j++) {
						trans.write(a1[i]+"\r\n");
					}trans.close();
				} catch (FileNotFoundException e1) {
					BufferedWriter trans = new BufferedWriter(new FileWriter(acno.getText()+"trans.txt"));
					trans.write("1000\r\n"+lc.space("Initial balance")+" "+lc.space("-")+" "+lc.space("1000"));
					trans.close();
				}
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
					BufferedWriter out1 = new BufferedWriter(new FileWriter(forget));
					System.out.println(a.length);
					for(i=0;i<a.length;i++) {
					out.write(a[i]+"\r\n");
					out1.write(a[i]+"\r\n");
					}
					out.close();out1.close();
					pop();
				}
				catch (IOException e) {System.out.println("Exception Occurred" + e);}
				Thread th = new Thread(lc);
				th.start();
			}
			if(check!=1) {match.setText("*Mismatched");}
		}
	
int rand=0,i;
	



public void otp(ActionEvent event) throws Exception {
	FileReader fileReader =new FileReader("genotp.txt");
    @SuppressWarnings("resource")
	BufferedReader bufferedReader = new BufferedReader(fileReader);
    String r = bufferedReader.readLine();
	System.out.println("Rand : "+r);
	if(otpmatch.getText().equals(r))
		button(event);
	else
		otperr.setText("*Invalid OTP");
}
public void resend(ActionEvent event) throws Exception {
	otpmatch.setText("");
	random();}


public void random() throws Exception {
	Random t =new Random();
	rand=t.nextInt(8999)+1000;
	String fileName = "genotp.txt",line,a[]=new String[20];
	BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
	out.write(""+rand);
	out.close();
	fileName ="temp_val.txt";
	FileReader fileReader =new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    int i=0;
    while((line = bufferedReader.readLine()) != null) {
    	a[i]=line;
        System.out.println(line);
        i++;
    } 
    bufferedReader.close();  
	String writef =a[0]+a[3]+".txt";
	try {
		BufferedWriter outap = new BufferedWriter(new FileWriter(writef));
		for(i=0;i<=5;i++) {
		outap.write(a[i]+"\r\n");}
		outap.close();
	}
	catch (IOException e) {
		System.out.println("Exception Occurred" + e);
	}
	out.close();
	loginController lc = new loginController();
	String body= "Email verification otp is "+ rand+" Dont share this otp.";
	lc.sendme(a[5],body);
}
public void sendme(String a,String body) {
	try {
		String[] mailid = {a};
		send_mail id = new send_mail();
		
		id.send(mailid,body);
	} catch (AddressException e) {
		System.out.println("not valid email id");
	}
	catch(MessagingException e) {
		System.out.println("network error");
	}
}

@Override
public void run() {
	try {
		random();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
	
}



public void pop()throws Exception  {
	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/popup.fxml"));
	Scene scene = new Scene(root,400,200);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setTitle("OTP");
	primaryStage.setScene(scene);
	primaryStage.show();
}




public void login(ActionEvent event) throws Exception {
 String fileName = name.getText()+pass.getText()+".txt";
 String line;
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
        // Or we could just do this: 
        // ex.printStackTrace();
    }

if(name.getText().equals(a[0])&& pass.getText().equals(a[3])) {
	
	status.setText("login successfully");
	UserInfo ui = new UserInfo();
	ui.state(event);
	
}
}


public String space(String a)
{int len = a.length();
if(len<50) {
for(int i=len;i<50;i++) {
	a = a+" ";
}}
return a;
	
}	
		
		
		//login text
			
		@FXML
		private TextField name,pass,newuser,DOB,acno,newpass,confpass,email,otpmatch;
		
		@FXML
		private Label status,nameerr,date,dateerr,accerr,passerr,otperr,emailerr,match,head,note;
		
			 String dob,ac;
			 String uname="user",upass="pass",cpass;
		
		public String date() {
			String d;
			int day,mon,year,sec,min,hour;
			GregorianCalendar dat = new GregorianCalendar();
			day = dat.get(Calendar.DAY_OF_MONTH);
			mon = dat.get(Calendar.MONTH);
			year = dat.get(Calendar.YEAR);
			sec = dat.get(Calendar.SECOND);
			min = dat.get(Calendar.MINUTE);
			hour = dat.get(Calendar.HOUR);
			d = day+"/"+(mon+1)+"/"+year+"  "+hour+":"+min+":"+sec;
			return d;
		}
		 public boolean validateJavaDate(String strDate)
		   {
			/* Check if date is 'null' */
			if (strDate.trim().equals(""))
			{
				dateerr.setText("*Enter a valid Date format");
			    return false;
			}
			/* Date is not 'null' */
			else
			{
			    /*
			     * Set preferred date format,
			     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
			    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/mm/yyyy");
			    sdfrmt.setLenient(false);
			    /* Create Date object
			     * parse the string into date 
		             */
			    try
			    {
			        Date javaDate = sdfrmt.parse(strDate); 
			        dateerr.setText("");
			    }
			    /* Date format is invalid */
			    catch (ParseException e)
			    {
			    	dateerr.setText("*Invalid Date format");
			        return false;
			    }
			    /* Return true if date format is valid */
			    return true;
			}
		   }
		public static final int PASSWORD_LENGTH = 8;
			 public  boolean is_Valid_Password(String password) {

			        if (password.length() < PASSWORD_LENGTH) return false;

			        int charCount = 0;
			        int numCount = 0;
			        for (int i = 0; i < password.length(); i++) {

			            char ch = password.charAt(i);

			            if (is_Numeric(ch)) numCount++;
			            else if (is_Letter(ch)) charCount++;
			            else return false;
			        }
			        return (charCount >= 2 && numCount >= 2);
			    }
			  public static boolean is_Letter(char ch) {
			        ch = Character.toUpperCase(ch);
			        return (ch >= 'A' && ch <= 'Z');
			    }
	         public static boolean is_Numeric(char ch) {

			        return (ch >= '0' && ch <= '9');
			    }
			
			
			
		public void page(String up,ActionEvent event) throws Exception {
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(up));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
		}
	public void page(String up) throws Exception {
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(up));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		}
		public static void appendStrToFile(String fileName,String str)
{
try {

// Open given file in append mode.
BufferedWriter out = new BufferedWriter(
new FileWriter(fileName, true));
out.write(str);
out.close();
}
catch (IOException e) {
System.out.println("exception occoured" + e);
}
}
	
}
