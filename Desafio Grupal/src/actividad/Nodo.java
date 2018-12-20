package actividad;

public class Nodo {
	private int x, y;
	private String name;
	
	public Nodo(String name, int x, int y) {
		setName(name);
		setX(x);
		setY(y);
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
