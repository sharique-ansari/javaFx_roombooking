package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;

public class login extends Application{
	public void start(Stage s) {
		s.setTitle("Login");
		GridPane g=new GridPane();
		final ToggleGroup group = new ToggleGroup();
		RadioButton r1=new RadioButton("Student");
		r1.setToggleGroup(group);
		RadioButton r2=new RadioButton("Faculty");
		r2.setToggleGroup(group);
		RadioButton r3=new RadioButton("Admin");
		r3.setToggleGroup(group);
		
		Text t1=new Text("User Id");
		Text t2=new Text("Password");
		Text t3=new Text("Invalid ID");
		Text t4=new Text("Valid ID");

		TextField tf1=new TextField();
		PasswordField tf2=new PasswordField();
		Button b=new Button("Login");
		b.setOnAction(e -> {
			if(r1.isSelected()) {
				if (varify_id_pass(tf1.getText(),tf2.getText())){
					Student_login stu ;
					stu =sendStud(tf1.getText());
					stu.start(s);
				}
				else {
					login st=new login();
					st.start(s);
				}
			}
			else if(r2.isSelected()) {
				if (varify_id_pass1(tf1.getText(),tf2.getText())){
					Faculty_login stu ;
					stu =sendFac(tf1.getText());
					stu.start(s);
				}
				else {
					login st=new login();
					st.start(s);
				}
			}
			else if(r3.isSelected()) {
				if (varify_id_passAdmin(tf1.getText(),tf2.getText())){
					Admin_login stu ;
					stu =sendAdm(tf1.getText());
					stu.start(s);
				}
				else {
					login st=new login();
					st.start(s);
				}


			}
		});
		tf1.setOnAction(e -> {

			if(!tf1.getText().equals("iiitd")) {
			    g.getChildren().remove(t4);
			    g.add(t3, 3, 2);
		    }
		    else {
			
			g.getChildren().remove(t3);
			
			g.add(t4, 3, 2);
		}
		});
		Button b7 = new Button("Home");
		b7.setOnAction(e -> {
			home home =new home();
			home.start(s);
		});
		Text t6 =new Text("No account?");
		Button b8 = new Button("Sign up");
		b8.setOnAction(e -> {
			Signup signup = new Signup();
			signup.start(s);
		});
		g.setHgap(20);
		g.setVgap(20);
		g.add(b7,1,1);
		g.add(r1,1, 2);
		g.add(r2,2, 2);
		g.add(r3,3, 2);

		g.add(t1,1 ,3);
		g.add(t2, 1,4);
		g.add(tf1,2, 3);
		g.add(tf2, 2, 4);
		g.add(b,2 , 5);
		g.add(t6,1,6);
		g.add(b8,2,6);
		Scene sc=new Scene(g,400,300);
		s.setScene(sc);
		s.show();
		
	}
	public Boolean varify_id_pass(String id,String pass){
		StudentList checkList =new StudentList();
		checkList=checkList.readSL("time.ser",checkList);
		System.out.println(checkList.studentData.size());
		for (Student_login currentStu : checkList.studentData){
			if (id.equals(currentStu.name)){
				System.out.println("found");
				if (currentStu.pass.equals(pass)){
					return Boolean.TRUE;
				}
				System.out.println("password error");
				Alert_Box.show("invalid password");
				return Boolean.FALSE;



			}

		}
		System.out.println("not found");
		Alert_Box.show("No such account exists");


		return Boolean.FALSE;
	}
	public Boolean varify_id_pass1(String id,String pass){
		FacultyList checkList =new FacultyList();
		checkList=checkList.readSL("faculty.ser",checkList);
		System.out.println(checkList.FacultyData.size());
		for (Faculty_login currentStu : checkList.FacultyData){
			if (id.equals(currentStu.name)){
				System.out.println("found");
				if (currentStu.pass.equals(pass)){
					return Boolean.TRUE;
				}
				System.out.println("password error");
				Alert_Box.show("invalid password");
				return Boolean.FALSE;



			}

		}
		System.out.println("not found");
		Alert_Box.show("No such account exists");


		return Boolean.FALSE;
	}

	public Boolean varify_id_passAdmin(String id,String pass){
		AdminList checkList =new AdminList();
		checkList=checkList.readSL("admin.ser",checkList);
		System.out.println(checkList.AdminData.size());
		for (Admin_login currentStu : checkList.AdminData){
			if (id.equals(currentStu.name)){
				System.out.println("found");
				if (currentStu.pass.equals(pass)){
					return Boolean.TRUE;
				}
				System.out.println("password error");
				Alert_Box.show("invalid password");
				return Boolean.FALSE;



			}

		}
		System.out.println("not found");
		Alert_Box.show("No such account exists");


		return Boolean.FALSE;
	}

	public Admin_login sendAdm(String id){
		AdminList checkList =new AdminList();
		checkList=checkList.readSL("admin.ser",checkList);
		for (Admin_login currentStu : checkList.AdminData){
			if (id.equals(currentStu.name)){
				System.out.println("sending");
				return currentStu;
			}
		}
		return null;
	}

	public Faculty_login sendFac(String id){
		FacultyList checkList =new FacultyList();
		checkList=checkList.readSL("faculty.ser",checkList);
		for (Faculty_login currentStu : checkList.FacultyData){
			if (id.equals(currentStu.name)){
				System.out.println("sending");
				return currentStu;
			}
		}
		return null;
	}
	public Student_login sendStud(String id){
		StudentList checkList =new StudentList();
		checkList=checkList.readSL("time.ser",checkList);
		for (Student_login currentStu : checkList.studentData){
			if (id.equals(currentStu.name)){
				System.out.println("sending");
				return currentStu;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		 
	}
}


