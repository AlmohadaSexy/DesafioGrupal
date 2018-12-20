package actividad;

public class Main {

	public static void main(String[] args) {
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
		
		mapa.encontrarPutoCamino("A");
	}
}