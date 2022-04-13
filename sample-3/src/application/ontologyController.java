package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;





public class ontologyController implements Initializable {
	
	// classes 
	@FXML
    public ComboBox<String> classes;
	@FXML
	private TextField classNameField;
	 @FXML
    private Button addClassButton;
	 

	
	String className;
	String parentClass;
	ObservableList<String> classList;
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { 
		classList= FXCollections.observableArrayList("owlThing");
		
		try {
            Scanner sc = new Scanner(new File("classlist.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.equals("owlThing"))
                	continue;
                classList.add(line.toString());
 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		classes.setItems(classList);
	}
	
	
	// class functions
	@FXML
	void choseParentClass(ActionEvent event) {
		parentClass=classes.getSelectionModel().getSelectedItem().toString();
	}
	
	@FXML
	void addClass(ActionEvent event)  throws IOException {
		className=classNameField.getText().toString();
		classes.getItems().addAll(className);
		
		

		try {
			owlxx(parentClass,className);
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileWriter writer = new FileWriter("classlist.txt"); 
		for(String str: classList) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
		
		System.out.print(className);
		
		
	}
	
	
	void owlxx(String parent_class,String class_name) throws OWLOntologyCreationException{
		// IRI IOR = IRI.create("http://owl.api.tutorial");
		
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		 OWLOntology o = man.loadOntologyFromOntologyDocument(new File("C:\\Users\\kagal\\Desktop\\s11.owl"));
		OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
//		 OWLClass student = df.getOWLClass(IOR+"#Student");
//		 // There is a class called Student.
//		 OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(student);
//		 o.add(da);
//		 // BtechStudent is a subclass of Student.
		 
//		 // IddStudent is a subclass of Student.
//		 OWLClass iddStudent = df.getOWLClass(IOR+"#IddStudent");
//		 OWLSubClassOfAxiom sca2 = df.getOWLSubClassOfAxiom(iddStudent, student);
//		 o.add(sca2);
		 
		// OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
		 if(parent_class.equals("owlThing"))
			{
			// OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
			 OWLClass s = df.getOWLClass(IOR+"#"+class_name);
			 // There is a class called Student.
			 OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(s);
			 o.add(da);
			}
			else
			{
				// OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
				OWLClass owl_parent_class=df.getOWLClass(IOR+"#"+parent_class);
				OWLClass subclass = df.getOWLClass(IOR+"#"+class_name);
				 OWLSubClassOfAxiom sca = df.getOWLSubClassOfAxiom(subclass, owl_parent_class);
				 o.add(sca);
			}
		 OWLXMLOntologyFormat format = new OWLXMLOntologyFormat();
		 FileOutputStream fos = null;
		 try {
		 fos = new FileOutputStream(new File("C:\\Users\\kagal\\Desktop\\s11.owl"));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		 try {
		 o.saveOntology(format, fos);
		 } catch (OWLOntologyStorageException e) {
		 e.printStackTrace();
		 }
		 try {
		 fos.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 
		 
	}
	
	
	
	
	
	
	
	
	
	
}
