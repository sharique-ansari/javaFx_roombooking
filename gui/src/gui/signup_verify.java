package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class signup_verify {
    public static void show(String _name, String _pass){
        Stage window =new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(250);
        Label label1=new Label();
        label1.setText("Sign up complete");
        Button close_b=new Button("OK");
        close_b.setOnAction(e -> window.close());


        VBox layout=new VBox(10);
        layout.getChildren().addAll(label1,close_b);
        layout.setAlignment(Pos.CENTER);
        Scene scene =new Scene(layout);
        window.setScene(scene);
        if (verify()){
            window.showAndWait();
        }

    }

    public static Boolean verify(){
        return Boolean.TRUE;
    }
}


