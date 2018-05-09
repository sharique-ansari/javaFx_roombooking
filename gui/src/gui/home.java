package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class home extends Application{
	public void start(Stage s) {

		s.setTitle("Home page");
		GridPane g=new GridPane();
		Button b1=new Button("Signup");
		Button b2=new Button("Login");
		b2.setOnAction(e -> {
			login l=new login();
			l.start(s);
		});
		b1.setOnAction(e -> {
			Signup su=new Signup();
			su.start(s);
		});
		b1.setPrefWidth(200);
		b2.setPrefWidth(200);
		g.add(b1,2, 2);
		g.add(b2,2, 3);
		g.setHgap(20);
		g.setVgap(20);
		Scene sc=new Scene(g,300,300);


		s.setScene(sc);

		s.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
