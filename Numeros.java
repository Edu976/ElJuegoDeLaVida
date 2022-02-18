package elJuegoDeLaVida;

public class Numeros {

	private int generacion = 0;
	private int celulasVivas = 0;
	private int celulasNuevas = 0;

	public Numeros(int generacion, int celulasVivas, int celulasNuevas) {
		super();
		this.generacion = generacion;
		this.celulasVivas = celulasVivas;
		this.celulasNuevas = celulasNuevas;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public int getCelulasVivas() {
		return celulasVivas;
	}

	public void setCelulasVivas(int celulasVivas) {
		this.celulasVivas = celulasVivas;
	}

	public int getCelulasNuevas() {
		return celulasNuevas;
	}

	public void setCelulasNuevas(int celulasNuevas) {
		this.celulasNuevas = celulasNuevas;
	}
	@Override
	public String toString() {
		String datos = "generacion: " + generacion + " celulas vivas: " 
	+ celulasVivas + " celulas nacidas: " + celulasNuevas;
		return datos;
	}
	
}
