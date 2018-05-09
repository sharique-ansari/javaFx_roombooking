package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Signup extends Application{
	public void start(Stage s) {
		final FileOutputStream[] fos = {null};
		final ObjectOutputStream[] out = {null};
		s.setTitle("Signup");
		GridPane g=new GridPane();
		final ToggleGroup group = new ToggleGroup();
		RadioButton r1=new RadioButton("Student");
		 r1.setToggleGroup(group);
		RadioButton r2=new RadioButton("Faculty");
		 r2.setToggleGroup(group);
		 Text t1=new Text("User Id");
		Text t2=new Text("Password");
		Text t3=new Text("Confirm Password");
		TextField tf1=new TextField();
		PasswordField tf2=new PasswordField();
		PasswordField tf3=new PasswordField();
		Button b=new Button("Signup");
		/*tf1.setOnAction(e -> {
		});
		
		tf3.setOnAction(e -> {
			
		});*/
		b.setOnAction(e -> {
			if (!r1.isSelected() & !r2.isSelected()){
				Alert_Box.show("please select the type of user");
				return;
			}
			
			if(!check(tf1.getText())) {
				Alert_Box.show("Invalid ID");
				return;
			}

			
			if(!tf3.getText().equals(tf2.getText())) {
				Alert_Box.show("Password don't match");
				return;
			}
			if(tf3.getText().equals(tf2.getText())) {
			}
			
			if( r1.isSelected() ) {
				Student_login s1 = new Student_login(tf1.getText(),tf2.getText());
				s1.setPass(tf2.getText());

				StudentList sL =new StudentList();
				sL=sL.readSL("time.ser",sL);


				sL.insertStudent(s1);
				sL.WriteSL("time.ser",sL);

				login st=new login();
				st.start(s);
				signup_verify.show(tf1.getText(),tf2.getText());

			}
			if( r2.isSelected() ) {
				Faculty_login s1=new Faculty_login(tf1.getText(),tf2.getText());
				s1.start(s);

				FacultyList sL =new FacultyList();
				sL=sL.readSL("faculty.ser",sL);


				sL.insertFaculty(s1);
				sL.WriteSL("faculty.ser",sL);

				login st=new login();
				st.start(s);
				signup_verify.show(tf1.getText(),tf2.getText());
			}
		});
		Button b5 = new Button("Home");
		b5.setOnAction(e -> {
			home home = new home();
			home.start(s);
		});
		Button b4 = new Button("Login");
		b4.setOnAction(e -> {
			login _login = new login();
			_login.start(s);
		});
		Text t4 = new Text("Already have an account?");

		g.setHgap(20);
		g.setVgap(20);
		g.add(b5,1,0);
		g.add(r1, 2, 0);
		g.add(r2, 3,0);
		g.add(t1,1 ,2);
		g.add(t2, 1,3);
		g.add(t3,1, 4);
		g.add(tf1,2, 2);
		g.add(tf2, 2, 3);
		g.add(tf3,2 , 4);
		g.add(b,2 , 5);
		g.add(t4,1,6);
		g.add(b4,2,6);
		Scene sc=new Scene(g,500,300);
		s.setScene(sc);
		s.show();

		
		
	}
	public boolean check(String s) {

		try {
			String[] ar=s.split("@",2);
			if(ar[1].equals("iiitd.ac.in")) {
				return true;
			}
			else {
				return false;
			}
		}catch (Exception e){
			return false;
		}



	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		

	}

}
