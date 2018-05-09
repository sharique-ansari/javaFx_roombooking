package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Faculty_login extends Application implements Serializable{
	
			private static final long serialVersionUID = 5107767591600955753L;
	
	String name;
	String pass;
	public Faculty_login(String n, String p){
		this.name=n;
		this.pass=p;
	}
	public Faculty_login(){
		name ="Default faculty";
		pass ="admin";
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	ArrayList<courses> mycourses = new ArrayList<courses>();
	ArrayList<String> myRequests = new ArrayList<String>();

	public void add_course(courses newCourse){
		mycourses.add(newCourse);

	}

	public ArrayList<courses> getMycourses() {
		return mycourses;
	}

	public void setMycourses(ArrayList<courses> mycourses) {
		this.mycourses = mycourses;
	}
	public void start(Stage s) {
		ComboBox cb=new ComboBox();
		for (String _cur:this.myRequests){
			cb.getItems().add(_cur);
		}

		s.setTitle("Faculty Login");
		GridPane g=new GridPane();
		Text t1=new Text("Logged in as " +name);
		Button b1=new Button("Log Out");
		b1.setPrefWidth(131);
		Button b2=new Button("Book room");
		b2.setOnAction(e -> {
			GetInput.show(s,this,cb);
		});
		b2.setPrefWidth(200);
		b1.setOnAction(e -> {
			Logout.logout(s);
		});



		cb.setPrefWidth(200);
		Button b4=new Button("Cancel Booking");
		b4.setOnAction(e -> {
			cancelBooking(cb,s);
		});
		b4.setPrefWidth(131);
		g.setHgap(20);
		g.setVgap(20);
		g.add(t1, 1, 1);
		g.add(b1, 2, 1);
		g.add(b2, 1, 2);
		g.add(cb, 1, 3);
		g.add(b4, 2, 3);
		Scene sc=new Scene(g,400,300);
		s.setScene(sc);
		s.show();
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	public void cancelBooking(ComboBox cb,Stage s){
		Object _selected;
		ObservableList<courses> _allCourses;
		_allCourses=cb.getItems();
		_selected=cb.getSelectionModel().getSelectedItem();
		FacultyList facultyList=new FacultyList();
		facultyList = facultyList.readSL("faculty.ser",facultyList);
		for (Faculty_login _curfac:facultyList.FacultyData){
			if (_curfac.name.equals(this.name)){
				for (String _cur:_curfac.myRequests){
					if (_cur.equals(_selected)){
						_curfac.myRequests.remove(_cur);
						myRequests.remove(_selected);
						break;
					}
				}

				facultyList.WriteSL("faculty.ser",facultyList);
				_curfac.start(s);
				break;
			}
		}

	}

}

class GetInput{
	public static void show(Stage s,Faculty_login faculty_login,ComboBox cb){
		TextInputDialog dialog = new TextInputDialog("C01");
		dialog.setTitle("Book Room");
		dialog.setHeaderText("Book a Room");
		dialog.setContentText("Please enter name of the room:");

// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		FacultyList FL=new FacultyList();
		FL=FL.readSL("faculty.ser", FL);
		if (result.isPresent()){
			System.out.println("Your room : " + result.get()+ "is booked");
			Alert_Box.show(result.get()+" is booked successfully");
			faculty_login.myRequests.add(result.get()+ " ");
			cb.getItems().add(result);
			for(Faculty_login _cur:FL.FacultyData) {
				if(_cur.name.equals(faculty_login.name)) {
					FL.FacultyData.remove(_cur);
					FL.FacultyData.add(faculty_login);
					break;
				}
			}
			FL.WriteSL("faculty.ser", FL);

		}


// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(name -> System.out.println("Your name: " + name));
	}
}
