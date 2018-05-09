package gui;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Admin_CheckRoom extends Application{
	Admin_login currentAdmin;
	public Admin_CheckRoom(Admin_login _admin){
		currentAdmin = _admin;
	}
	TableView<request> table= new TableView<>();
	public void start(Stage s) {



		s.setTitle("Admin Check");

		//name column
		TableColumn<request,String> nameColumn=new TableColumn<>("studentID");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//roomID column
		TableColumn<request,String> RoomIDColumn=new TableColumn<>("RoomID");
		RoomIDColumn.setMinWidth(50);
		RoomIDColumn.setCellValueFactory(new PropertyValueFactory<>("roomname"));

		//purpose column
		TableColumn<request,String> PurposeColumn=new TableColumn<>("Purpose");
		PurposeColumn.setMinWidth(300);
		PurposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));


		//capacity column
		TableColumn<request,Integer> CapacityColumn=new TableColumn<>("Rq Capacity");
		CapacityColumn.setMinWidth(200);
		CapacityColumn.setCellValueFactory(new PropertyValueFactory<>("requiredCap"));

		table.getColumns().addAll(nameColumn,RoomIDColumn,PurposeColumn,CapacityColumn);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



		Text t1=new Text("Booking Requests");
		Button b0 = new Button("Back");
		b0.setOnAction(e -> {
			currentAdmin.start(s);
		});

		Button b1=new Button("Approve Request");
		b1.setOnAction(e -> {
			approve(Boolean.TRUE);
		});
		Button b2=new Button("Reject Request");
		b2.setOnAction(e -> {
			approve(Boolean.FALSE);
		});

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(t1);
		hb1.setAlignment(Pos.CENTER_LEFT);
		hb1.setPadding(new Insets(10,10,10,10));

		hb1.setSpacing(10);
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(b0,b1,b2);
		hb2.setAlignment(Pos.CENTER);
		hb2.setPadding(new Insets(10,10,10,10));
		hb2.setSpacing(10);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hb1,table,hb2);
		Scene sc=new Scene(vBox,650,400);
		s.setScene(sc);
		s.show();
		
		
	}
	public void seeRequests(Stage s,Admin_CheckRoom ad){
		Room_requests rq1 = new Room_requests();
		rq1 = rq1.getRequests("request.ser",rq1);

		for (request _current:rq1.pendingReq){
			table.getItems().add(_current);
		}
		ad.start(s);
	}
	public void add_table(request _req){
		System.out.println(_req.room.RoomID+" "+_req.name);
		table.getItems().add(_req);
	}

	public void approve(Boolean ab){
		ObservableList<request> _selected,_allrequest;
		_allrequest=table.getItems();
		_selected=table.getSelectionModel().getSelectedItems();
		for (request current:_selected){
			Room_requests rq = new Room_requests();
			rq =rq.getRequests("request.ser",rq);
			int key=0;

				for (request _current:rq.pendingReq){

					if (key==0 && _current.name.equals(current.name) && _current.roomname.equals(current.roomname) && _current.requiredCap==current.requiredCap){


						StudentList sl = new StudentList();
						sl = sl.readSL("time.ser",sl);
						for (Student_login _curStu:sl.studentData){
							if (_curStu.name.equals(_current.name)){
								for (request _Curstucurreq:_curStu.myRequests){
									if (_Curstucurreq.purpose.equals(current.purpose) && _Curstucurreq.roomname.equals(current.roomname)){
										if (ab){
											current.setStatus(Boolean.TRUE);
											_current.setStatus(Boolean.TRUE);
											rq.approved.add(_current);
											_Curstucurreq.setStatus(Boolean.TRUE);
											rq.pendingReq.remove(_current);
										}
										if (!ab){
											current.setStatus(Boolean.FALSE);
											_current.setStatus(Boolean.FALSE);
//											rq.approved.add(_current);
											_Curstucurreq.setStatus(Boolean.FALSE);
											_Curstucurreq.setRejected(Boolean.TRUE);
											rq.pendingReq.remove(_current);
										}


									}
								}
							}
						}
						sl.WriteSL("time.ser",sl);
						key=1;
						break;
					}
				}
			System.out.println(current.getName());
			rq.UpdateRequests("request.ser",rq);
		}

		_selected.forEach(_allrequest::remove);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
