package fxml;

import java.awt.Panel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
	
	@FXML
	private Panel panel;
	
	private ArrayList<Line> lineas = new ArrayList<Line>();
	private ArrayList<String> letrasYPos = new ArrayList<String>();
	
	public void crearLineas() {
		String recorrido = mapa.getFinalRecorrido();
		if(recorrido != null) {
			char[] recorridoChar = recorrido.toCharArray();
			for(int ii = 0; ii < recorridoChar.length - 1; ii++) {
				int xIni = (mapa.getNode(String.valueOf(recorridoChar[ii])).getX() * 2) + 200;
				int yIni = (mapa.getNode(String.valueOf(recorridoChar[ii])).getY() * 2) + 200;
				int xFin = (mapa.getNode(String.valueOf(recorridoChar[ii + 1])).getX() * 2) + 200;
				int yFin = (mapa.getNode(String.valueOf(recorridoChar[ii + 1])).getY() * 2) + 200;
				lineas.add(new Line( xIni, yIni, xFin, yFin));
				letrasYPos.add(String.valueOf(recorridoChar[ii]) + ", " + xIni + ", " + yIni);
				if(ii == recorridoChar.length - 2) {
					letrasYPos.add(String.valueOf(recorridoChar[ii]) + ", " + xFin + ", " + yFin);
				}
			}
			pintarLineas();
		} else {
			System.out.println("crea un camino");
		}
	}
	
	private void pintarLineas() {
		for(Line linea: lineas) {
			canvas.getChildren().add(linea);
		}
	}
	
	@FXML
	public void pintar() {
		panel.removeAll();
		crearLineas();
	}
	
	@FXML
	public void solucion() {
		panel.removeAll();
		String finRec = mapa.getFinalRecorrido();
		double finDis = mapa.getFinalDistancia();
		String str = "Solucion \n"
				+ "El recorrido final es " + finRec + ", con una distancia de " + finDis + " km";
		lbl_solucion.setText(str);
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	@Override
	public void addMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public MapaController() {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
