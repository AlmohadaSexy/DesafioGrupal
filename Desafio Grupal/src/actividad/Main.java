package actividad;

import fxml.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static String datosID= "Datos";
	public static String datosFile= "Datos.fxml";
	public static String graphID= "Grafico";
	public static String graphFile= "Mapa.fxml";
	
	private static Stage stage;
    private static ScreensController mainContainer;
    
	public static void main1(String[] args) {
		Mapa mapa = new Mapa();
		
		mapa.addNodo("A", 0, 0);
		mapa.addNodo("B", 0, 1);
		mapa.addNodo("C", 0, 7);
		mapa.addNodo("D", 10, 10);
		mapa.addNodo("E", 20, 20);
		mapa.addNodo("F", 40, 20);
		
		mapa.addConexion("B", "C");
		mapa.addConexion("F", "D");
		mapa.addConexion("F", "D");
		//94
		mapa.encontrarCamino("A");
	}
	
	@Override
    public void start(Stage primaryStage) {
		
       	mainContainer = new ScreensController();
        
        mainContainer.loadScreen(datosID, datosFile);
        
        
        mainContainer.setScreen(datosID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage = primaryStage;
        stage.setScene(scene);
//        stage.getIcons().add(new Image("/Logo.jpg")); 
        setTitle(datosID);
        stage.setResizable(true);
        stage.show();
    }
	
    public static void setTitle(String name) {
    	stage.setTitle(name);
    }
    
	public static void cargarNuevas(Mapa mapa) {
		mainContainer.loadScreen(graphID, graphFile, mapa);
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}