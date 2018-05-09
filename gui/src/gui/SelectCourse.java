package gui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SelectCourse extends Application{

	Student_login current_user;
	public SelectCourse(Student_login log_stu){
		current_user=log_stu;

	}
	TableView<courses> table= new TableView<>();
	public void start(Stage s) {
		s.setTitle("Select Course");

		//name column
		TableColumn<courses,String> nameColumn=new TableColumn<>("name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//instructor column
		TableColumn<courses,String> InsColumn=new TableColumn<>("instructor");
		InsColumn.setMinWidth(300);
		InsColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));

		//credits column
		TableColumn<courses,Integer> creditsColumn=new TableColumn<>("credits");
		creditsColumn.setMinWidth(100);
		creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

		//code column
		TableColumn<courses,String> codeColumn=new TableColumn<>("course code");
		codeColumn.setMinWidth(100);
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));


//		table.setItems(getCourses());
		table.getColumns().addAll(nameColumn,InsColumn,creditsColumn,codeColumn);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Text t1=new Text("Search Results");
		ComboBox cb=new ComboBox();
		cb.setPrefWidth(250);
		Button b1=new Button("Add Course");
		b1.setPrefWidth(125);
		b1.setOnAction(e -> {
			addCourse();
		});
		Button b2=new Button("Done");
		b2.setPrefWidth(125);
		b2.setOnAction(e->{
			current_user.start(s);
		});
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);

		hBox.getChildren().addAll(b2,b1);





		VBox vBox=new VBox();
		vBox.getChildren().addAll(t1,table,hBox);
		Scene sc=new Scene(vBox);

		s.setScene(sc);

		s.show();
	}
	public void addCourse(){
		ObservableList<courses> _selected,_allCourses;
		_allCourses=table.getItems();
		_selected=table.getSelectionModel().getSelectedItems();
		for (courses current:_selected){
			current_user.mycourses.add(current);
			System.out.println(current.getName());
		}
		_selected.forEach(_allCourses::remove);
		StudentList NL=new StudentList();
		NL=NL.readSL("time.ser",NL);
		for (Student_login _cur:NL.studentData){
			if (_cur.name.equals(current_user.name)){
				NL.studentData.remove(_cur);
				NL.studentData.add(current_user);
				break;
			}
		}
		NL.WriteSL("time.ser",NL);
	}
	public void add_table(courses _course){
		table.getItems().add(_course);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
