package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Timetable extends Application{

    Student_login student_login ;
    public Timetable(Student_login stu){
        student_login=stu;
    }

    TableView<courses> tableView =new TableView<>();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 =new Button("Back");
        b1.setOnAction(e ->{
            student_login.start(primaryStage);
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(b1);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);

        //name column
        TableColumn<courses,String> nameColumn=new TableColumn<>("Course Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //monday time column
        TableColumn<courses,String> MondayColumn=new TableColumn<>("Monday");
        MondayColumn.setMinWidth(150);
        MondayColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));

        //tuesday time column
        TableColumn<courses,String> TuesdayColumn=new TableColumn<>("Tuesday");
        TuesdayColumn.setMinWidth(150);
        TuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));

        //wednesday time column
        TableColumn<courses,String> WednesdayColumn=new TableColumn<>("Wednesday");
        WednesdayColumn.setMinWidth(150);
        WednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));

        //thur time column
        TableColumn<courses,String> ThursdayColumn=new TableColumn<>("Thursday");
        ThursdayColumn.setMinWidth(150);
        ThursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));

        //friday time column
        TableColumn<courses,String> FridayColumn=new TableColumn<>("Friday");
        FridayColumn.setMinWidth(150);
        FridayColumn.setCellValueFactory(new PropertyValueFactory<>("Friday"));

        //Tutorials column
        TableColumn<courses,String> TutColumn=new TableColumn<>("Tutorial");
        TutColumn.setMinWidth(150);
        TutColumn.setCellValueFactory(new PropertyValueFactory<>("tutorial"));

        //Lab column
        TableColumn<courses,String> LabColumn=new TableColumn<>("LAB");
        LabColumn.setMinWidth(150);
        LabColumn.setCellValueFactory(new PropertyValueFactory<>("lab"));

        tableView.getColumns().addAll(nameColumn,MondayColumn,TuesdayColumn,WednesdayColumn,ThursdayColumn,FridayColumn,TutColumn,LabColumn);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,tableView);
        Scene sc = new Scene(vBox,1200,500);
        primaryStage.setScene(sc);

        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
