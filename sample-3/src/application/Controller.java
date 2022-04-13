package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;



public class Controller {
	
	  @FXML
	    private Button firstbutton;
	  
	  @FXML
	    private Button secondbutton;

	    @FXML
	    private Button thirdbutton;


	    

	   
	@FXML
    void click(ActionEvent event ) throws IOException {
		URL url = Paths.get(".\\src\\application\\addclass.fxml").toUri().toURL();
		  Parent root = FXMLLoader.load(url);
		//Parent root = FXMLLoader.load(getClass().getResource("addclass.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle(" Application");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
		//System.out.print("pp");
    }
	
	@FXML
    void click1(ActionEvent event) throws IOException {
		
		URL url = Paths.get(".\\src\\application\\addobjprop.fxml").toUri().toURL();
		  Parent root = FXMLLoader.load(url);
		
	//	Parent root = FXMLLoader.load(getClass().getResource("addobjprop.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle(" Application");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
	
	 @FXML
	    void click2(ActionEvent event) throws IOException {
		 
		 URL url = Paths.get(".\\src\\application\\adddataprop.fxml").toUri().toURL();
		  Parent root = FXMLLoader.load(url);
		
		 
		 //Parent root = FXMLLoader.load(getClass().getResource("adddataprop.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setTitle(" Application");
	        primaryStage.setScene(new Scene(root, 800, 500));
	        primaryStage.show();
	    }
	
	
    
}