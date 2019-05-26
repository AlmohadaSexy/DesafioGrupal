package actividad;

public class Conexion {
	private Nodo nodo1, nodo2;

	public Conexion(Nodo nodo1, Nodo nodo2) {
		setNodo1(nodo1);
		setNodo2(nodo2);
	}

	public Nodo getNodo1() {
		return nodo1;
	}

	public String getName1() {
		return nodo1.getName();
	}

	public void setNodo1(Nodo nodo1) {
		this.nodo1 = nodo1;
	}

	public Nodo getNodo2() {
		return nodo2;
	}

	public String getName2() {
		return nodo2.getName();
	}

	public void setNodo2(Nodo nodo2) {
		this.nodo2 = nodo2;
	}

	public String toString() {
		return getNodo1() + "----------->" + getNodo2();
	}
}
