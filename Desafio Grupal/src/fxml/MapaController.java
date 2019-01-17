package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import actividad.Main;
import actividad.Mapa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class MapaController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	private Mapa mapa;
	
	@FXML
	private Pane canvas;
	
	@FXML
	private Button btn_pintar;
	
	@FXML
	private Label lbl_solucion;
	
	@FXML
	private Button btn_sol;
	
	private ArrayList<Line> lineas = new ArrayList<Line>();
	public void crearLineas() {
		String recorrido = mapa.getFinalRecorrido();
		if(recorrido != null) {
			char[] recorridoChar = recorrido.toCharArray();
			for(int ii = 0; ii < recorridoChar.length - 1; ii++) {
				int xIni = mapa.getNode(String.valueOf(recorridoChar[ii])).getX();
				int yIni = mapa.getNode(String.valueOf(recorridoChar[ii])).getY();
				int xFin = mapa.getNode(String.valueOf(recorridoChar[ii + 1])).getX();
				int yFin = mapa.getNode(String.valueOf(recorridoChar[ii + 1])).getY();
				lineas.add(new Line( xIni, yIni, xFin, yFin));
				Label auxLabel1 = new Label(String.valueOf(recorridoChar[ii]));
				auxLabel1.setLayoutX(xIni + 15);
				auxLabel1.setLayoutY(yIni + 15);
				canvas.getChildren().add(auxLabel1);
				Label auxLabel2 = new Label(String.valueOf(recorridoChar[ii + 1]));
				auxLabel2.setLayoutX(xFin + 15);
				auxLabel2.setLayoutY(yFin + 15);
				canvas.getChildren().add(auxLabel2);
			}
			pintarLineas();
		} else {
			System.err.println("Crea un camino");
		}
	}
	
	private void pintarLineas() {
		canvas.getChildren().addAll(lineas);
	}
	
	@FXML
	public void pintar() {
		canvas.getChildren().removeAll();
		crearLineas();
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@Override
	public void addMapa(Mapa mapa) {
		this.mapa = mapa;
		lbl_solucion.setText("El camino más eficaz de izquierda a derecha es: " + mapa.getFinalRecorrido() + " \n Con una distancia total de " + mapa.getFinalDistancia() + "km");
	}

	public MapaController() {
		
	}
	@FXML
	private void volver() {
		myController.setScreen(Main.datosID);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
