package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import actividad.Mapa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class DatosController implements Initializable, ControlledScreen{

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
	private TextField tf_x;
	
	@FXML
	private TextField tf_y;
	
	@FXML
	private ChoiceBox<String> cbx_inicio;
	
	private ObservableList<String> nodos = FXCollections.observableArrayList();
	
	@FXML
	public void addNodo() {
		int x = Integer.parseInt(tf_x.getText());
		int y = Integer.parseInt(tf_y.getText());
		this.mapa.addNodo(tf_nodo.getText(), x, y);
		nodos.add(tf_nodo.getText());
		cbx_1.setItems(nodos);
		cbx_2.setItems(nodos);
		cbx_inicio.setItems(nodos);
		tf_nodo.clear();
		tf_x.clear();
		tf_y.clear();
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
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}
