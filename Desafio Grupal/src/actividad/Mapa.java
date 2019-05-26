package actividad;

import java.util.ArrayList;

public class Mapa {
	private ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	private ArrayList<Conexion> conexiones = new ArrayList<Conexion>();
	private ArrayList<String> todosRecorridos = new ArrayList<String>();

	private ArrayList<String> recorridos = new ArrayList<String>();
	private ArrayList<Double> distanciaRecorridos = new ArrayList<Double>();

	private String finalRecorrido;
	private double finalDistancia;

	private String permutate = "";

	public Mapa() {

	}

	/**
	 * Añade un nodo <code>(String, int, int)</code> al ArrayList
	 * <code>nodos</code>, de donde sacaremos los puntos para realizar nuestro
	 * camino. el nombre es aconsejable que solo tenga un caracter.
	 * 
	 * @param name nombre con el cual nos referiremos al nodo
	 * @param x    coordenada x
	 * @param y    coordenada y
	 */
	public void addNodo(String name, int x, int y) {
		nodos.add(new Nodo(name, x, y));
	}

	/**
	 * Añade una conexion de reparto "Origen -> Destino" que deberá cumplirse en el
	 * problema
	 * 
	 * @param name1 nombre del nodo1, donde se recoge el pedido
	 * @param name2 nombre del nodo2, donde se entrega el pedido
	 */
	public void addConexion(String name1, String name2) {
		System.out.println(new Conexion(getNode(name1), getNode(name2)));
		conexiones.add(new Conexion(getNode(name1), getNode(name2)));
	}

	/**
	 * A este metodo se le llama desde el main, encuentra el camino mas eficaz para
	 * repartir todos los pedidos
	 * 
	 * @param inicio nombre del nodo desde donde empiezas y terminas el reparto
	 */
	public void encontrarCamino(String inicio) {
		if (existsNode(inicio)) {
			for (Nodo nodo : nodos) {
				if (!nodo.getName().equals(inicio) && existsConex(nodo.getName())) {
					permutate += nodo.getName();
				}
			}

			permutaciones(permutate, 0, permutate.length() - 1);
			addStart(inicio);
			System.out.println(todosRecorridos);
			retirarConex();
			addDistancias();
			for (int ii = 0; ii < recorridos.size(); ii++) {
				System.out.println(
						"Recorrido: " + recorridos.get(ii) + ", Distancia: " + distanciaRecorridos.get(ii) + " km.");
			}
			System.out.println();
			System.out.println("Camino mas eficaz");
			System.out.println("Recorrido: " + recorridos.get(calcularCaminoMasEficaz()) + ", Distancia: "
					+ distanciaRecorridos.get(calcularCaminoMasEficaz()) + " km.");
			// hacer
		} else {
			System.err.println("El nodo no existe");
		}
	}

	/**
	 * Este metodo recursivo se llama una y otra vez a si mismo con diferentes
	 * strings. Cuando los dos contadores son iguales, dicha string se añade al
	 * ArrayList <code>todosRecorridos</code> Esto consigue eficientemente todas las
	 * iteraciones nuestra String para conseguir todos los caminos posibles
	 * 
	 * @param permutate String de la permutacion
	 * @param iniInd    indice inicial
	 * @param finInd    indice final
	 */
	public void permutaciones(String permutate, int iniInd, int finInd) {
		if (iniInd == finInd) {
			todosRecorridos.add(permutate);
		} else {
			for (int ii = iniInd; ii <= finInd; ii++) {
				permutate = swap(permutate, iniInd, ii);
				permutaciones(permutate, iniInd + 1, finInd);
				permutate = swap(permutate, iniInd, ii);
			}
		}
	}

	/**
	 * este metodo es auxiliar para el metodo <code>permutaciones</code> que cambia
	 * de orden los caracteres del String
	 * 
	 * @param permutate
	 * @param ii
	 * @param jj
	 * @return
	 */
	private String swap(String permutate, int ii, int jj) {
		char temp;
		char[] charArray = permutate.toCharArray();
		temp = charArray[ii];
		charArray[ii] = charArray[jj];
		charArray[jj] = temp;
		return String.valueOf(charArray);
	}

	/**
	 * Este metodo selecciona el nodo donde empieza el recorrido
	 * 
	 * @param start
	 */
	private void addStart(String start) {
		for (int ii = 0; ii < todosRecorridos.size(); ii++) {
			String caminoFin = start + todosRecorridos.get(ii) + start;
			todosRecorridos.set(ii, caminoFin);
		}
	}

	private void retirarConex() {
		for (int ii = 0; ii < todosRecorridos.size(); ii++) {
			comprobarConexion(todosRecorridos.get(ii).toCharArray(), ii);
		}
		for (String recorrido : todosRecorridos) {
			if (recorrido.charAt(recorrido.length() - 1) != '-') {
				recorridos.add(recorrido);
			}
		}
		System.out.println(todosRecorridos);
		System.out.println(recorridos);
	}

	private void comprobarConexion(char[] str, int index) {
		int intaux1 = 0;
		int intaux2 = 0;
		for (int ii = 0; ii < conexiones.size(); ii++) {
			for (int jj = 0; jj < str.length; jj++) {
				String aux1 = conexiones.get(ii).getName1();
				String aux2 = conexiones.get(ii).getName2();
				if (str[jj] == aux1.charAt(0)) {
					intaux1 = jj;
				}
				if (str[jj] == aux2.charAt(0)) {
					intaux2 = jj;
				}
			}
			if (intaux1 > intaux2) {
				String aux = "";
				for (int kk = 0; kk < str.length; kk++) {
					aux += str[kk];
				}
				todosRecorridos.set(index, aux + "-");
			}
		}
	}

	private void addDistancias() {
		for (int ii = 0; ii < recorridos.size(); ii++) {
			distanciaRecorridos.add(calcularDistancia(recorridos.get(ii)));
		}
	}

	public double calcularDistancia(String recorrido) {
		Nodo aux1, aux2;
		double distancia = 0;
		char[] strArr = recorrido.toCharArray();
		for (int ii = 0; ii < strArr.length - 1; ii++) {
			aux1 = getNode(String.valueOf(strArr[ii]));
			aux2 = getNode(String.valueOf(strArr[ii + 1]));
			distancia += Math.sqrt(Math.pow(aux1.getX() - aux2.getX(), 2) + Math.pow(aux1.getY() - aux2.getY(), 2));
		}
		return distancia;
	}

	private int calcularCaminoMasEficaz() {
		int aux = 0;
		for (int ii = 0; ii < distanciaRecorridos.size(); ii++) {
			if (distanciaRecorridos.get(aux) > distanciaRecorridos.get(ii)) {
				aux = ii;
			}
		}
		setFinalRecorrido(recorridos.get(aux));
		setFinalDistancia(distanciaRecorridos.get(aux));
		return aux;
	}

	public Nodo getNode(String name) {
		for (Nodo nodo : nodos) {
			if (nodo.getName().equals(name)) {
				return nodo;
			}
		}
		return null;
	}

	private boolean existsNode(String name) {
		for (Nodo nodo : nodos) {
			if (nodo.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	private boolean existsConex(String name) {
		for (Conexion conexion : conexiones) {
			if (conexion.getName1().equals(name) || conexion.getName2().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String getFinalRecorrido() {
		return finalRecorrido;
	}

	private void setFinalRecorrido(String finalRecorrido) {
		this.finalRecorrido = finalRecorrido;
	}

	public double getFinalDistancia() {
		return finalDistancia;
	}

	private void setFinalDistancia(double finalDistancia) {
		this.finalDistancia = finalDistancia;
	}
}
