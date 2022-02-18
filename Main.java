package elJuegoDeLaVida;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	/**
	 * pre:
	 * post: generamos un tablero para el juego de la vida introduciendo el numero de
	  filas y columnas en caso de querer un tablero aleatorio usando el
	  Mathrandom() para tirar un numero aleatorio que si es mayor que 2 la celda
	  del tablero estara vacia, sino llena. En caso de querer rellenarlo a mano
	  iremos marcando cada celda que queremos que esté rellena
	 */
	
	public static int[][] generarTablero() {
		Scanner sc = new Scanner(System.in);
		System.out.println("dime el numero de columnas");
		int filaX = sc.nextInt();
		System.out.println("dime el numero de filas");
		int filaY = sc.nextInt();
		int tabla[][] = new int[filaX][filaY];
		int celula = 0;
		System.out.println("Si quieres generar un tablero aleatorio pulse 1");
		System.out.println("Si quieres intorducir un tablero ya generado pulse 2");
		/*
		 * Genera un tablero aleatorio con un 20% de posibilidades de tener la celda rellena 
		 * y un 80 de que este vacia  
		 */
		while (true) {
			int generacionTablero = sc.nextInt();
			if (generacionTablero == 1) { // generar tablero aleatorio
				for (int i = 0; i < tabla.length; i++) {
					for (int j = 0; j < tabla.length; j++) {
						celula = 0;
						int aleatorio = (int) (Math.random() * (10 - 1)) + 1;
						if (aleatorio > 2) {
							// si el numero es mayor a 2 la celda estara vacia
							celula = 0;
							tabla[i][j] = celula;
						} else {
							// si el numero es menor que dos la celda estara ocupada
							celula = 1;
							tabla[i][j] = celula;
						}
					}
				}
				break;
				/*
				 * generar tablero predeterminiado metiendo las celdas en las filas y 
				 * columnas deseadas, para dejar de poner celulas hay que escribir "no"
				 * cuando nos pregunte si deseamos añadir otra celula mas, en cualquier 
				 * otro caso nos dejara añadir otra celula más
				 */
			} else if (generacionTablero == 2) { 
				while (true) {
					System.out.println("en que casilla deseas poner la celula");
					System.out.println("dime la fila");
					int celdarellenaX = sc.nextInt();
					System.out.println("dime la columna");
					int celdarellenaY = sc.nextInt();
					sc.nextLine();
					tabla[celdarellenaX][celdarellenaY] = 1;
					System.out.println("¿Quieres poner otra?");
					String otracelula = sc.nextLine();
					if (otracelula.equals("no")) {
						break;
					}
				}
				break;
			} else {
				System.out.println("elija entre 1 o 2");
			}
		}
		return tabla;
	}
	
	/** 
	 * pre:
	 * post: En este metodo recorremos la tabla y vamos pintando * en caso de que la celda
	 * tenga un 1 o espacios vacios si contiene un 0
	 */

	public static void imprimirTablero(int[][] tabla) throws InterruptedException {
		Thread.sleep(400);
		for (int i = 0; i < tabla.length; i++) {
			System.out.println();
			for (int j = 0; j < tabla[i].length; j++) {
				if (tabla[i][j] == 1) {
					System.out.print(" * ");
				} else {
					System.out.print(" - ");
				}
			}
		}
	}

	/**
	 * pre:
	 * post: en este metodo copiamos la tabla2 en tabla 1 para poder imprimirla por
	 * pantalla
	 */
	public static int[][] copiartabla(int[][] tabla2) {
		int tablacopia[][] = new int[tabla2.length][tabla2[0].length];
		for (int i = 0; i < tabla2.length; i++) {
			for (int j = 0; j < tabla2.length; j++) {
				tablacopia[i][j] = tabla2[i][j];
			}
		}
		return tablacopia;
	}

	/**
	 * pre:
	 * post: en este metodo controlaremos el numero de generaciones que pasara nuestra
	 * colonia, comprobando cada celda del tablero y contando el numero de vecinos
	 * que tiene cada celda y segun este numero la celula morira, se mantendra igual
	 * o se reproducira. tambien iremos contando el numero de generaciones, el
	 * numero de celulas que hay vivas y el numero de celulas que han nacido nuevas
	 * por generacion
	 */
	public static void generaciones(int[][] tabla) throws InterruptedException {
		int tabla2[][] = new int[tabla.length][tabla[0].length];
		ArrayList <Numeros> l = new ArrayList <Numeros>();
		Numeros n; // llamada a la clase de la tripleta
		Scanner sc = new Scanner(System.in);
		int vecinos = 0;  // numero de vecinos de cada celda
		int contador = 0; // contador de generaciones
		int generaciones = sc.nextInt(); // numero de generaciones que se van a ejecutar
		int celulasVivas = 0;  // celulas vivas en cada generacion
		int celulasNuevas = 0; //celulas nacidas en cada generacion
		// bucle de nº generaciones
		for (int h = 0; h <= generaciones - 1; h++) {
			/*
			 * en estos bucles recorremos la tabla para comparar cada celda que hay 
			 * alrededor de la celda que esta seleccionada y con los ifs se va 
			 * comprobando si la celda vecina esta libre y ocupada 
			 */
			for (int i = 0; i < tabla.length; i++) {
				for (int j = 0; j < tabla[i].length; j++) {
					// vecina i-1 j-1
					if ((i - 1 >= 0) && (j - 1 >= 0)) {
						if (tabla[i - 1][j - 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i j-1
					if ((i >= 0) && (j - 1 >= 0)) {
						if (tabla[i][j - 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i+1 j-1
					if ((i + 1 < tabla.length) && (j - 1 >= 0)) {
						if (tabla[i + 1][j - 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i+1 j
					if ((i + 1 < tabla.length) && (j >= 0)) {
						if (tabla[i + 1][j] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i+1 j+1
					if ((i + 1 < tabla.length) && (j + 1 < tabla[j].length)) {
						if (tabla[i + 1][j + 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i j+1
					if ((i >= 0) && (j + 1 < tabla[j].length)) {
						if (tabla[i][j + 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i-1 j+1
					if ((i - 1 >= 0) && (j + 1 < tabla[j].length)) {
						if (tabla[i - 1][j + 1] == 1) {
							vecinos = vecinos + 1;
						}
					}
					// vecina i-1 j
					if ((i - 1 >= 0) && (j < tabla[j].length)) {
						if (tabla[i - 1][j] == 1) {
							vecinos = vecinos + 1;
						}
					
					}
					if (tabla[i][j] == 1) { // contador celulas vivas (tripleta)
						celulasVivas++;
					}
					
					/*
					 * condiciones de accion de las celulas es decir segun las condiciones
					 * la celda tendra un 1 y por lo tanto una celula o un 0 y estara vacia
					 */
						
					if (vecinos < 2) {
						// celula muere
						tabla2[i][j] = 0;
					} else if (vecinos == 2 && tabla[i][j] == 0) {
						// sobrevive
						tabla2[i][j] = 0;
					} else if (vecinos == 2 && tabla[i][j] == 1) {
						// sobrevive
						tabla2[i][j] = 1;
					} else if (vecinos == 3 && tabla[i][j] == 1) {
						// sobrevive
						tabla2[i][j] = 1;
					} else if (vecinos == 3 && tabla[i][j] == 0) {
						// se reproduce
						tabla2[i][j] = 1;
						celulasNuevas++;
					} else {
						// muere superpoblecion
						tabla2[i][j] = 0;
					}
					vecinos = 0;
				}
			}
			
			/*
			 * copiamos la tabla de la siguiente generacion en la tabla original para poder
			 * imprimirla y asi sucesivamente hasta terminar las generaciones indicadas
			 */
			
			tabla = copiartabla(tabla2);
			System.out.println();
			System.out.println("Generacion " + (contador + 1));
			imprimirTablero(tabla);
			System.out.println();
			contador++;
			/*
			 * rellenamos el array list con el numero de generaciones, el numero de celulas
			 * vivas y el numero de celulas nuevas y lo inicializamso a 0 para contar la
			 * siguiente generacion
			 */
			n = new Numeros(contador, celulasVivas, celulasNuevas);
			l.add(n);
			if(celulasVivas == 0) {
				for (int i = 0; i < l.size(); i++) {
					System.out.println();
					System.out.println(l.get(i).toString());
				}
				System.out.println("tu colonia ha cascao");
				System.exit(0);	
			}
			celulasVivas = 0;
			celulasNuevas = 0;
		}
		for (int i = 0; i < l.size(); i++) {
			System.out.println();
			System.out.println(l.get(i).toString());
		}
	}

	/**
	 * pre:
	 * post: 	en el main creamos el menu del juego en el que llamaremos a cada metodo para
	 * que se ejecute en orden
	 */
	public static void main(String[] Args) throws InterruptedException {
		System.out.println("-----Bienvenido al juego de la vida-----");
		int tabla[][] = generarTablero();
		ArrayList <Numeros> l = new ArrayList <Numeros> ();
		System.out.println("Generacion 0");
		System.out.println();
		imprimirTablero(tabla);
		System.out.println();
		System.out.println();
		System.out.println("dime el numero de generaciones que quieres realizar");
		generaciones(tabla);
	}
}
