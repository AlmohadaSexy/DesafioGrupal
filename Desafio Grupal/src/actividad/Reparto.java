package actividad;

public class Reparto {
	private Coordenadas origen, destino;

	public Reparto(int x1, int y1, int x2, int y2) {
		setOrigen(x1, y1);
		setDestino(x2, y2);
	}
	
	public Coordenadas getOrigen() {
		return origen;
	}

	public void setOrigen(int x, int y) {
		this.origen = new Coordenadas(x, y);
	}

	public Coordenadas getDestino() {
		return destino;
	}

	public void setDestino(int x, int y) {
		this.destino = new Coordenadas(x, y);
	}
	
}
