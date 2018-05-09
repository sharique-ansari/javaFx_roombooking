package gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Room_book extends Application {
	Student_login currentUser;
	public Room_book(Student_login _student1){
		currentUser=_student1;
	}

	public void start(Stage s) {
		s.setTitle("Room Booking");
		GridPane g=new GridPane();
		Button b0 = new Button("back");
		b0.setOnAction(e -> {
			currentUser.start(s);
		});
		Button b1=new Button("Check Current Status");
		b1.setOnAction(e -> {checkStatus(currentUser,s);});
		b1.setPrefWidth(200);
		Text t1=new Text("New Request");
		Text t2=new Text("Room no (Optional)");
		Text t3=new Text("Purpose Of Booking");
		Text t4=new Text("Required Capacity");
		TextField tf1=new TextField();
		TextField tf2=new TextField();
		TextField tf3=new TextField();
		Button b2=new Button("Request");
		b2.setOnAction(e -> {
			b2Pressed(tf1,tf2,tf3,s);
		});
		g.setHgap(20);
		g.setVgap(20);
		g.add(b0,1,1);
		g.add(b1, 2, 1);
		g.add(t1,1 , 3);
		g.add(t2, 1, 4);
		g.add(t3, 1, 5);
		g.add(t4, 1, 6);
		g.add(tf1, 2, 4);
		g.add(tf2, 2, 5);
		g.add(tf3,2, 6);
		g.add(b2, 2,7);
		Scene sc=new Scene(g,400,300);
		s.setScene(sc);
		s.show();
		
		
		
	}
	public void checkStatus(Student_login _student,Stage s){
		if (_student.myRequests.size()!=0){
			Request_status rq =new Request_status(currentUser);
			for (request current:_student.myRequests){

				rq.add_to_list(current.room.RoomID,current.status,current.rejected);
				System.out.println(current.room.RoomID+" "+current.status);
			}
			try {
				rq.start(s);
			} catch (Exception e) {
				System.out.println("some error occurred "+e.toString());
				Alert_Box.show("error, try again");
			}


		}
		else {
			Alert_Box.show("No requests made yet");
		}

	}
	public void b2Pressed(TextField tf1,TextField tf2, TextField tf3,Stage s){
		Roomlist Rl = new Roomlist();
		Rl = Rl.readAllrooms("allrooms.ser",Rl);
		int key=0;
		for (rooms _current:Rl.AllRooms){
			if (_current.RoomID.equals(tf1.getText())){
				key=1;
				if (_current.capacity<Integer.parseInt(tf3.getText())){
					Alert_Box.show("Room capacity exceeds");
					tf3.clear();
					return;
				}
				try {
					RequestBooking(_current,tf2.getText(),Integer.parseInt(tf3.getText()));
					tf1.clear();tf2.clear();tf3.clear();
					Alert_Box.show("request successfully added");
					currentUser.start(s);
					return;
				}
				catch (Exception e){
					Alert_Box.show("Booking failed");
					tf1.clear();tf2.clear();tf3.clear();
				}


			}
		}
		if (key==0){
			Alert_Box.show("No such room found");
		}

	}
	public void RequestBooking(rooms R1,String _purpose, int _reqCap){
		if (available()){
			Room_requests Ra=new Room_requests();

			//read existing data
			Ra=Ra.getRequests("request.ser",Ra);

			request Rq1 = new request(R1,currentUser,_purpose,_reqCap);
			Ra.pendingReq.add(Rq1);
			currentUser.myRequests.add(Rq1);

			//write to req
			Ra.UpdateRequests("request.ser",Ra);
			StudentList NL=new StudentList();
			NL=NL.readSL("time.ser",NL);
			for (Student_login _cur:NL.studentData){
				if (_cur.name.equals(currentUser.name)){
					NL.studentData.remove(_cur);
					NL.studentData.add(currentUser);
					break;
				}
			}
			NL.WriteSL("time.ser",NL);



			//write to db
		}
	}
	public Boolean available(){
		return Boolean.TRUE;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);

	}

}
