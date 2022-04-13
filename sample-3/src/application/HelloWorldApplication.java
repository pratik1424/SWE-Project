package application;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

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
	
	 
	 public void clearFile(Scanner s, Formatter f){
		    //go through and do this every time in order to delete previous crap
		    while(s.hasNext()){
		        f.format("");
		    }
		} 
	 
	  @Override
	    public void start(Stage primaryStage) {
		  
		  
		  
		  
		  try
		  {
			  
			  Scanner s = new Scanner(new File("classlist.txt"));
			 Formatter f = new Formatter("classlist.txt");  
			  clearFile(s,f);
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