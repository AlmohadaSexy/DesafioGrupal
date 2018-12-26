package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import actividad.Mapa;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DatosController implements Initializable, ControlledScreen {
	ScreensController myController;
	private Mapa mapa;
	
	@FXML
	private Button btn_aceptar;
	
	@FXML
	private Button btn_nodo;
	
	@FXML
	private Button btn_conexion;
	
	@FXML
	private ChoiceBox<String> cbx_1;
	
	@FXML
	private ChoiceBox<String> cbx_2;
	
	@FXML
	private TextField tf_nodo;
	
	@FXML
	private TextField tf_y;
	
	@FXML
	private TextField tf_x;
	
	@FXML
	private Label lbl_incorrect;
	
	@FXML
	private Button btn_new;
	@FXML
	private ChoiceBox<String> cbx_inicio;
	
	@FXML
	private CheckBox check_x;
	
	@FXML
	private CheckBox check_y;
	
	private ObservableList<String> nodos = FXCollections.observableArrayList();
	
	@FXML
	public void addNodo() {
		if(!nodos.contains(tf_nodo.getText())) {
			int x = 0;
			int y = 0;
			if(check_x.isSelected()) {
				x = 0 - Integer.parseInt(tf_x.getText());
			} else {
				x = Integer.parseInt(tf_x.getText());
			}
			if(check_y.isSelected()) {
				y = 0 - Integer.parseInt(tf_y.getText());
			} else {
				y = Integer.parseInt(tf_y.getText());
			}
			this.mapa.addNodo(tf_nodo.getText(), x, y);
			nodos.add(tf_nodo.getText());
			cbx_1.setItems(nodos);
			cbx_2.setItems(nodos);
			cbx_inicio.setItems(nodos);
			tf_nodo.clear();
			tf_y.clear();
			tf_x.clear();
		} else {
			lbl_incorrect.setVisible(true);
		}
	}
	
	@FXML
	public void addConexion() {
		String nodo1 = cbx_1.getValue();
		String nodo2 = cbx_2.getValue();
		this.mapa.addConexion(nodo1, nodo2);
	}
	
	@FXML
	public void aceptar() {
		this.mapa.encontrarPutoCamino(cbx_inicio.getValue());
		JOptionPane.showMessageDialog(null,
			    "El recorrido más eficaz es '" + this.mapa.getFinalRecorrido() + "' con una distancia de " + mapa.getFinalDistancia() + " km.",
			    "Solucion",
			    JOptionPane.PLAIN_MESSAGE);
//		Main.cargarNuevas(this.mapa);
//		myController.setScreen(Main.graphID);
	}
	
	@FXML
	public void borrarTodo() {
		this.mapa = new Mapa();
		this.nodos.clear();
		tf_nodo.clear();
		tf_y.clear();
		tf_x.clear();
		cbx_1.setItems(nodos);
		cbx_2.setItems(nodos);
		cbx_inicio.setItems(nodos);
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mapa = new Mapa();
		
		lbl_incorrect.setVisible(false);
		
        /**
         * 
         * 
         * 
         * 
         */
		tf_y.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            tf_y.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		tf_x.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            tf_x.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}

	public DatosController() {
		
	}
	@Override
	public void addMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}
