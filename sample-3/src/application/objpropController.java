package application;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.google.common.io.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class objpropController implements Initializable {
	
	@FXML
    private ComboBox<String> domainList;

    @FXML
    private ComboBox<String> rangeList;

    @FXML
    private TextField objectPropertyName;
    
    @FXML
    private Button addObjectPropertyButton;

    
    String domainClass;
    String rangeClass;
    String obj_property_name;
    


    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> domain_list= FXCollections.observableArrayList();
		ObservableList<String> range_list= FXCollections.observableArrayList();
		
		try {
            Scanner sc = new Scanner(new File("classlist.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                domain_list.add(line.toString());
                range_list.add(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	//System.out.print(ClassList);
//		ontologyController obj=new ontologyController();
//		domain_list.addAll(obj.classList);
//		range_list.addAll(obj.classList);
	domainList.setItems(domain_list);
	rangeList.setItems(range_list);
//		
		
	}
	
	@FXML
    void choseDomain(ActionEvent event) {
		
		
		domainClass=domainList.getSelectionModel().getSelectedItem().toString();
		
		
    }
	
    @FXML
    void choseRange(ActionEvent event) {
    	
    	rangeClass=rangeList.getSelectionModel().getSelectedItem().toString();
    	
    	
    }
    
    
    @FXML
    void addObjectProperty(ActionEvent event)  throws IOException, OWLOntologyCreationException {
    	obj_property_name= objectPropertyName.getText().toString();
    	IRI IOR = IRI.create("http://owl.api.tutorial");
    	OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		 OWLOntology o = man.loadOntologyFromOntologyDocument(new File("C:\\Users\\kagal\\Desktop\\s11.owl"));
		 OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
    	OWLObjectProperty h = df.getOWLObjectProperty(IOR+"#"+obj_property_name);
    	OWLDeclarationAxiom da21 = df.getOWLDeclarationAxiom(h);
    	o.add(da21);
    	
    	
    	OWLClass domain_name = df.getOWLClass(IOR+"#"+domainClass);
    	
    	OWLClass range_name = df.getOWLClass(IOR+"#"+rangeClass);
    	OWLObjectPropertyDomainAxiom dp12 = df.getOWLObjectPropertyDomainAxiom(h, domain_name);
    	o.add(dp12);
    	OWLObjectPropertyRangeAxiom dr12 = df.getOWLObjectPropertyRangeAxiom(h, range_name);
    	o.add(dr12);
    	
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
    	
    	//System.out.print("pk");
    }
}
