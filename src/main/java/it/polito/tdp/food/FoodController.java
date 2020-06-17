/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.AdiacentPortion;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	
    	try {
    		
    		if (boxPorzioni.getSelectionModel().getSelectedItem()==null)
    			throw new NullPointerException("Non è stata selezionata alcuna porzione valida.");
    		
    		if (boxPorzioni.getSelectionModel().isEmpty())
    			throw new NullPointerException("Sono presenti problemi. Probabilmente non è ancora stato generato il grafo.");

    		List<AdiacentPortion> path = model.percorsoDiPesoMassimo(Integer.parseInt(txtPassi.getText()), boxPorzioni.getSelectionModel().getSelectedItem());
    		
    		txtResult.setText(String.format("Il vertice di partenza è %s.\n", boxPorzioni.getSelectionModel().getSelectedItem()));
    		
    		if(path.isEmpty())
    			throw new NullPointerException("Non è possibile proseguire l'esplorazione. Non esistono sono presenti abbastanza nodi.");
 
    		for (AdiacentPortion ad : path)
    			txtResult.appendText(String.format("Arco: %s con peso %s.\n", ad.getPortionName(), ad.getWeight()));

    		
    	} catch (NumberFormatException nfe) {
    		txtResult.setText("Inserire un numero.");
    	} catch (Exception e) {
    		txtResult.setText(e.getMessage());
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	try {
    		if (boxPorzioni.getSelectionModel().getSelectedItem()==null)
    			throw new NullPointerException("Non è stata selezionata alcuna porzione valida.");
    		
    		if (boxPorzioni.getSelectionModel().isEmpty())
    			throw new NullPointerException("Sono presenti problemi. Probabilmente non è ancora stato generato il grafo.");
    	
    		List<AdiacentPortion> l = new ArrayList<AdiacentPortion>(model.getCorrelati(boxPorzioni.getSelectionModel().getSelectedItem()));
    		
    		if(l.isEmpty())
    			throw new NullPointerException("Non è presente alcun vertice collegato al tipo di porzione selezionato.");
    		
    		for (AdiacentPortion ad : l)
    			txtResult.appendText(String.format("Arco vicino: %s con peso %s\n", ad.getPortionName(), ad.getWeight()));
    		
    	} catch (Exception e) {
    		txtResult.setText(e.getMessage());

    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	try {
    		if (txtCalorie.getText().isBlank())
    			throw new NullPointerException("Non è stato inserito alcun valore");
    		
    		double calorie = Double.parseDouble(txtCalorie.getText());
        	model.creaGrafo(calorie);
        	
        	boxPorzioni.getItems().clear();
        	boxPorzioni.getItems().addAll(model.getGraph().vertexSet());
        	
        	
        	txtResult.setText(model.getCreation());
		}
    	catch (NumberFormatException nfe)
    	{
    		txtResult.setText("Inserire un numero.");
    	}
    	catch (Exception e) {
    		txtResult.setText(e.getMessage());

    	}
    	
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
