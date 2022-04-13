package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;





public class datapropController  implements Initializable {
	

	
	
	
    @FXML
    private ComboBox<String> dataDomainList;
	
	@FXML
	private ComboBox<String> ddrange;// drop down range data properties
	
    @FXML
    private TextField dataPropertyName;
    
    
    @FXML
    private Button addDataPropertyButton;
    
    String domainClass;
    String range;
    String data_property_name;
    
    
 
    
    
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { 
		
		ObservableList<String> domain_list= FXCollections.observableArrayList();
		
		try {
            Scanner sc = new Scanner(new File("classlist.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                domain_list.add(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		dataDomainList.setItems(domain_list);
		ObservableList<String> range_list= FXCollections.observableArrayList("string", "normalizedString", "boolean", "decimal", "float", "double", "integer", "nonNegativeInteger", "positiveInteger", "nonPositiveInteger", "negativeInteger", "long", "int", "short", "byte", "unsignedLong", "unsignedInt", "unsignedShort", "unsignedByte", "hexBinary", "base64Binary", "dateTime", "time", "date", "gYearMonth", "gYear", "gMonthDay", "gDay", "gMonth", "anyURI", "token", "language", "NMTOKEN", "Name", "NCName");
		ddrange.setItems(range_list);
		
	}
	
	@FXML
    void rangedd(ActionEvent event) {
		 range=ddrange.getSelectionModel().getSelectedItem().toString();
//			System.out.print(s);/
    }
	
	
    @FXML
    void selectDomain(ActionEvent event) {
    		domainClass=dataDomainList.getSelectionModel().getSelectedItem().toString();
    }
    
    
    @FXML
    void addDataProperty(ActionEvent event) throws OWLOntologyCreationException {
    		
    	data_property_name= dataPropertyName.getText().toString();
    	IRI IOR = IRI.create("http://owl.api.tutorial");
    	OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		 OWLOntology o = man.loadOntologyFromOntologyDocument(new File("C:\\Users\\kagal\\Desktop\\s11.owl"));
		 OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
    	
		 OWLDataProperty h = df.getOWLDataProperty(IOR+"#"+data_property_name);
		 OWLDeclarationAxiom da8 = df.getOWLDeclarationAxiom(h);
		 o.add(da8);
		 
		 OWLClass domain_name = df.getOWLClass(IOR+"#"+domainClass);
		 
		 OWLDataPropertyDomainAxiom dp = df.getOWLDataPropertyDomainAxiom(h, domain_name);
		 o.add(dp);
    	
		 switch (range)
		 {
		 case "string":
			 OWLDataPropertyRangeAxiom dr = df.getOWLDataPropertyRangeAxiom(h, df.getStringOWLDatatype());
			 o.add(dr);
			 break;
		 case "integer":
			 OWLDataPropertyRangeAxiom dr2 = df.getOWLDataPropertyRangeAxiom(h, df.getIntegerOWLDatatype());
			 o.add(dr2);
			 break;
		 case "float":
			 OWLDataPropertyRangeAxiom dr3 = df.getOWLDataPropertyRangeAxiom(h, df.getFloatOWLDatatype());
			 o.add(dr3);
			 break;
		 case "boolean":
			 OWLDataPropertyRangeAxiom dr4 = df.getOWLDataPropertyRangeAxiom(h, df.getBooleanOWLDatatype());
			 o.add(dr4);
			 break;
		 case "double":
			 OWLDataPropertyRangeAxiom dr5 = df.getOWLDataPropertyRangeAxiom(h, df.getDoubleOWLDatatype());
			 o.add(dr5);
			 break;
		 default:
			 break;
		
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
