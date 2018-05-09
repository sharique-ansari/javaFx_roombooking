package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class Student_login extends Application implements Serializable{

	private static final long serialVersionUID = -8691600395251561987L;
	String name;String pass;

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	ArrayList<courses> mycourses = new ArrayList<courses>();
	ArrayList<request> myRequests = new ArrayList<request>();

	public void add_course(courses newCourse){
		mycourses.add(newCourse);

	}

	public ArrayList<courses> getMycourses() {
		return mycourses;
	}

	public void setMycourses(ArrayList<courses> mycourses) {
		this.mycourses = mycourses;
	}

	public Student_login(String _name, String _pass){
		name=_name;pass=_pass;
	}
	public Student_login(){name="default user" ;pass="admin";}
	public void start(Stage s) {
		s.setTitle("Student Login");
		GridPane g=new GridPane();
		Text t1=new Text("Logged in as "+name);
		Button b1=new Button("Log Out");
		b1.setPrefWidth(125);
		b1.setOnAction(event -> {
			Logout.logout(s);

		});
		TextField tf1=new TextField();
		tf1.setPrefWidth(230);
		Button b2=new Button("Search Course");
		b2.setPrefWidth(125);
		Button b3=new Button("TimeTable");
		b3.setOnAction(e -> {
			Timetable timetable = new Timetable(this);
			for (courses current:mycourses){

				timetable.tableView.getItems().add(current);
			}
			try {
				timetable.start(s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		Button b4=new Button("Request booking");
		b4.setOnAction(e -> {
			Room_book r1 = new Room_book(this);
			r1.start(s);

		});
		b2.setOnAction(e -> {
			select_course(tf1.getText(),tf1,s);


        });
		g.setHgap(20);
		g.setVgap(20);
		g.add(t1, 1, 1);
		g.add(b1, 2, 1);
		g.add(tf1, 1, 2);
		g.add(b2, 2, 2);
		g.add(b3, 1, 3);
		g.add(b4, 2, 3);
		Scene sc=new Scene(g,400,300);
		s.setScene(sc);
		s.show();
	}
	public void read_course(){}

	public void select_course(String post_cond,TextField tf1,Stage s){
		try {
			BufferedReader br = new BufferedReader(new FileReader("test.csv"));
			String line;
			String str = "";
			String out = "";
			int key=1;
			SelectCourse s1 =new SelectCourse(this);

			while ((line = br.readLine()) !=null) {
				String[] b = line.split(",");
				if ((b[0].equals("Mandatory")|b[0].equals("Elective")|b[0].equals("HSS Elective"))){
					out=str;
					str=line;
					key=0;
					if (tf1.getText().equals("all")|tf1.getText().equals("")){
					}

				}
				else {
					str+=line;
				}
				if (key==0){
					key=1;
					String[] k=out.split(",");
					if (!k[0].equals("Mandatory/Elective")){
						String[] kl=out.split("\"");
						String str_out=kl[kl.length-1];
						if (str_out.indexOf(tf1.getText())>=1){
							String[] skl =kl[0].split(",");
							String name=skl[1];
							String code = skl[2];
							String ins = skl[3];
							int cred =Integer.parseInt(skl[4]);
							String acr= skl[5];
							courses c1= new courses(name,code,ins,acr,cred,skl[6],skl[7],skl[8],skl[9],skl[10],skl[11],skl[12]);
							int key1=1;
							if (key1==1){
								for (courses _current:mycourses){
									if (_current.getName().equals(name)){
										key1=0;
									}
								}
							}
							if (key1==1){
								s1.add_table(c1);
								System.out.println(kl[0]);
							}



						}
					}
				}

			}
			s1.start(s);


		} catch (java.io.IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}
class Logout{
	public static void logout(Stage s){
		Alert alert_box = new Alert(Alert.AlertType.CONFIRMATION);
		alert_box.setContentText("Are you sure?");
		ButtonType b1 = new ButtonType("Yes");
		ButtonType b2 = new ButtonType("No");
		alert_box.getButtonTypes().setAll(b1,b2);
		Optional<ButtonType> result = alert_box.showAndWait();
		if (result.get()==b1){
			home l = new home();
			l.start(s);
		}
		if (result.get()==b2){
			return;

		}
	}
}


