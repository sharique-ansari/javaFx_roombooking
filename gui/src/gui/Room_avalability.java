package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import javax.swing.*;
import java.util.Map;

import static javafx.application.Application.launch;

public class Room_avalability extends Application{
    TableView<rooms> table= new TableView<>();
    public void start(Stage s){
        s.setTitle("Room availability");

        rooms r1 =new rooms("c201",200);
        rooms r2 =new rooms("c204",100);


        table.getItems().addAll(r1,r2);



        //availability  column



        //name column
//        TableColumn<courses,String> nameColumn=new TableColumn<>("name");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

//        table.getColumns().addAll(couloums);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox vBox=new VBox();
        vBox.getChildren().addAll(table);
        Scene sc=new Scene(vBox);

        s.setScene(sc);
        s.show();

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }

}
