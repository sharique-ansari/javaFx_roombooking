package gui;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Admin_login extends Application implements Serializable{
	private static final long serialVersionUID = -3461519131515236179L;
	String name,pass;

	ArrayList<String> myRequests = new ArrayList<>();
	public Admin_login(String _name, String _pass){
		name=_name;pass=_pass;
	}
	public Admin_login(){name="defaultAdmin" ;pass="admin";}
	public void start(Stage s) {
		s.setTitle("Admin Login");
		GridPane g=new GridPane();
		Text t1=new Text("Logged in as Admin");
		Button b1=new Button("Log Out");
		b1.setOnAction(e -> {
			Logout.logout(s);

		});
		ComboBox cb=new ComboBox();
		for (String _cur:this.myRequests){
			cb.getItems().add(_cur);
		}
		cb.setPrefWidth(200);


		cb.setOnAction(e -> {
			for(int i=0;i<this.myRequests.size();i++){
				cb.getItems().add(this.myRequests.get(i));
			}
			});
		Button b6 =new Button("Cancel booking");

		g.add(b6,2,5);
		g.add(cb,1,5);
		b1.setPrefWidth(170);
		Button b2=new Button("Check all requests");
		b2.setPrefWidth(170);
		b2.setOnAction(e -> {

			seeRequests(s);
		});
		Button b3=new Button("Room Book");
		b3.setOnAction(e -> {
			GetAdmInput.show(s,this,cb);
		});
		b3.setPrefWidth(170);
		g.setHgap(20);
		g.setVgap(20);
		g.add(t1, 1, 2);
		g.add(b1, 2, 2);
		g.add(b2, 1, 4);
		g.add(b3, 2, 4);
		Scene sc=new Scene(g,400,300);
		s.setScene(sc);
		s.show();
	}

    public void seeRequests(Stage s){

        Admin_CheckRoom admin_ = new Admin_CheckRoom(this);
        Room_requests rq1 = new Room_requests();
        rq1 = rq1.getRequests("request.ser",rq1);



        for (request _current:rq1.pendingReq){
            admin_.add_table(_current);
        }
        admin_.start(s);
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
		AdminList adminList=new AdminList();
		adminList = adminList.readSL("admin.ser",adminList);
		for (Admin_login _curfac:adminList.AdminData){
			if (_curfac.name.equals(this.name)){
				for (String _cur:_curfac.myRequests){
					if (_cur.equals(_selected)){
						_curfac.myRequests.remove(_cur);
						myRequests.remove(_selected);
						break;
					}
				}

				adminList.WriteSL("admin.ser",adminList);
				_curfac.start(s);
				break;
			}
		}

	}

}

class GetAdmInput{
	public static void show(Stage s,Admin_login faculty_login,ComboBox cb){
		TextInputDialog dialog = new TextInputDialog("C01");
		dialog.setTitle("Admin Book Room");
		dialog.setHeaderText("Book a Room");
		dialog.setContentText("Please enter name of the room:");

// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		AdminList FL=new AdminList();
		FL=FL.readSL("admin.ser", FL);
		if (result.isPresent()){
			System.out.println("Your room : " + result.get()+ "is booked");
			Alert_Box.show(result.get()+" is booked successfully");
			faculty_login.myRequests.add(result.get()+ " ");
			cb.getItems().add(result);
			for(Admin_login _cur:FL.AdminData) {
				if(_cur.name.equals(faculty_login.name)) {
					FL.AdminData.remove(_cur);
					FL.AdminData.add(faculty_login);
					break;
				}
			}
			FL.WriteSL("admin.ser", FL);

		}


// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(name -> System.out.println("Your name: " + name));
	}
}


