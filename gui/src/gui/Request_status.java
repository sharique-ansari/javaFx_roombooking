package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Request_status extends Application {
    Student_login currentUser;
    ListView<String> listView=new ListView<>();
    public Request_status(Student_login _cur){
        currentUser=_cur;
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button b1 =new Button("Back");
        b1.setOnAction(e -> {
            currentUser.start(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(b1,listView);
        Scene scene =new Scene(layout,300,250);
        primaryStage.setTitle("Requests result");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void add_to_list(String a, Boolean b,Boolean c){
        if (b){
            listView.getItems().add(a + "  is booked succesfully");
        }
        else if (c){
            listView.getItems().add(a + " is rejected");
        }
        else {
            listView.getItems().add(a+"  request is still pending");
        }
    }
}
