package actividad;

import java.util.ArrayList;

public class Mapa {
	private ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	private ArrayList<Conexion> conexiones = new ArrayList<Conexion>();
	private ArrayList<String> recorridoSinStart = new ArrayList<String>();
	private ArrayList<String> recorridoConStart = new ArrayList<String>();
	private ArrayList<String> recorridoFin = new ArrayList<String>();
	private ArrayList<Double> recorridoFinD = new ArrayList<Double>();
	
	private String permutate = "";
	public Mapa() {
		
	}
	
	public void addNodo(String name, int x, int y) {
		nodos.add(new Nodo(name, x, y));
	}
	
	public void addConexion(String name1, String name2) {
		System.out.println(new Conexion(getNode(name1), getNode(name2)));
		conexiones.add(new Conexion(getNode(name1), getNode(name2)));
	}
	
	private void retirarConex() {
		for(int ii = 0; ii < recorridoConStart.size(); ii++) {
			comprobarConexion(recorridoConStart.get(ii).toCharArray(), ii);
		}
		for(String recorrido: recorridoConStart) {
			if(recorrido.charAt(recorrido.length() - 1) != '-') {
				recorridoFin.add(recorrido);
			}
		}
		System.out.println(recorridoConStart);
		System.out.println(recorridoFin);
	}
	
	private void comprobarConexion(char[] str, int index) {
		int intaux1 = 0;
		int intaux2 = 0;
		for(int ii = 0; ii < conexiones.size(); ii++) {
			for(int jj = 0; jj < str.length; jj++) {
				String aux1 = conexiones.get(ii).getName1();
				String aux2 = conexiones.get(ii).getName2();
				if(str[jj] == aux1.charAt(0)) {
					intaux1 = jj;
				}
				if(str[jj] == aux2.charAt(0)) {
					intaux2 = jj;
				}
			}
			if(intaux1 > intaux2) {
				String aux = "";
				for(int kk = 0; kk < str.length; kk++) {
					aux += str[kk];
				}
				recorridoConStart.set(index, aux + "-");
			}
		}
	}
	
	private Nodo getNode(String name) {
		for(Nodo nodo : nodos) {
			if(nodo.getName().equals(name)) {
				return nodo;
			}
		}
		return null;
	}
	private boolean existsNode(String name) {
		for(Nodo nodo : nodos) {
			if(nodo.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void encontrarPutoCamino(String inicio) {
		if(existsNode(inicio)) {
			for(Nodo nodo : nodos) {
            	if(!nodo.getName().equals(inicio)) {
                	permutate += nodo.getName();
                }
            }
			
			//permutaciones
			permutaciones(permutate, 0, permutate.length()-1);
			addStart(inicio);
			System.out.println(recorridoConStart);
			retirarConex();
			addDistancias();
			for(int ii = 0; ii < recorridoFin.size(); ii++) {
				System.out.println("Recorrido: " + recorridoFin.get(ii) + ", Distancia: " + recorridoFinD.get(ii) + " km.");
			}
			System.out.println();
			System.out.println("Camino mas eficaz");
			System.out.println("Recorrido: " + recorridoFin.get(calcularCaminoMasEficaz()) + ", Distancia: " + recorridoFinD.get(calcularCaminoMasEficaz()) + " km.");
			//hacer
        } else {
            System.err.println("El nodo no existe");
        }
	}
	
	private int calcularCaminoMasEficaz() {
		int aux = 0;
		for(int ii = 0; ii < recorridoFinD.size(); ii++) {
			if(recorridoFinD.get(aux) > recorridoFinD.get(ii)) {
				aux = ii;
			}
		}
		return aux;
	}

	private void addDistancias() {
		for(int ii = 0; ii < recorridoFin.size(); ii++) {
			recorridoFinD.add(calcularDistancia(recorridoFin.get(ii)));
		}
	}

	private void addStart(String start) {
		for(int ii = 0; ii < recorridoSinStart.size(); ii++) {
			String finalCheck = start + recorridoSinStart.get(ii) + start;
			recorridoConStart.add(finalCheck);
		}
	}

	public void permutaciones(String permutate, int iniInd, int finInd) {
		if(iniInd == finInd) {
			recorridoSinStart.add(permutate);
		} else {
			for (int i = iniInd; i <= finInd; i++) { 
                permutate = swap(permutate, iniInd, i); 
                permutaciones(permutate, iniInd + 1, finInd); 
                permutate = swap(permutate, iniInd, i); 
            } 
		}
	}
	
	private String swap(String permutate, int ii, int jj) {
		char temp; 
        char[] charArray = permutate.toCharArray(); 
        temp = charArray[ii] ; 
        charArray[ii] = charArray[jj]; 
        charArray[jj] = temp; 
        return String.valueOf(charArray); 
	}

	public double calcularDistancia(String recorrido) {
		Nodo aux1, aux2;
		double distancia = 0;
		char[] strArr = recorrido.toCharArray();
		for(int ii = 0; ii < strArr.length - 1; ii++) {
			aux1 = getNode(String.valueOf(strArr[ii]));
			aux2 = getNode(String.valueOf(strArr[ii + 1]));
			distancia += Math.sqrt(Math.pow(aux1.getX() - aux2.getX(), 2) + Math.pow(aux1.getY() - aux2.getY(), 2));
		}
		return distancia;
	}
}
