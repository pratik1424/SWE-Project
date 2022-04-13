package application;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloWorldApplication extends Application {

	
	 public static void main(String[] args) throws IOException {
	        launch(args);
	    }
	
	  @Override
	    public void start(Stage primaryStage) {
		  try
		  {
			  URL url = Paths.get(".\\src\\application\\first.fxml").toUri().toURL();
			  Parent root = FXMLLoader.load(url);
			//  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("first.fxml"));
		        primaryStage.setTitle("FXML Application");
		        primaryStage.setScene(new Scene(root, 800, 500));
		        primaryStage.show();
			//  System.out.print("SWE");
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
	        
	    }


	   
}