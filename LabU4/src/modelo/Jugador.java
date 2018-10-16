package modelo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Jugador implements Serializable, Comparable<Jugador> {

	private String nombre;
	
	private int puntos;

	public Jugador(String nombre) {
		this.nombre = nombre;
		puntos=0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Jugador: "+this.getNombre()+" Puntaje: "+this.getPuntos();
	}
	
	@Override
	public int compareTo(Jugador o) {
		if(this.getPuntos()<o.getPuntos()) {
			return 1;
		}else if(this.getPuntos()>o.getPuntos()){
			return -1;
		}else {
			return 0;	
		}
	}
	
	
	
}
