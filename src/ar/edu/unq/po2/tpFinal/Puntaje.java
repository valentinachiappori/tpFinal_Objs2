package ar.edu.unq.po2.tpFinal;

public enum Puntaje {
	
	UNO(1), DOS(2), TRES(3), CUATRO(4), CINCO(5);
	
	private int nivel;

	private Puntaje(int nivel) {
		setNivel(nivel);
	}

	void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
		
	}
	
}
