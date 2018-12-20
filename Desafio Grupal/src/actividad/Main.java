package actividad;

public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa();
		
		mapa.addNodo("A", 3, 25);
		mapa.addNodo("B", 23, 5);
		mapa.addNodo("C", 14, 20);
		mapa.addNodo("D", -23, 0);
		mapa.addNodo("E", 44, -31);
		
		mapa.addConexion("B", "D");
		mapa.addConexion("C", "E");
		mapa.encontrarPutoCamino("A");
	}
}
