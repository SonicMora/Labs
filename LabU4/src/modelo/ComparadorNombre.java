package modelo;

import java.util.Comparator;

public class ComparadorNombre implements Comparator<Jugador> {

	@Override		
	public int compare(Jugador jugador1, Jugador jugador2) {
		return jugador1.getNombre().compareToIgnoreCase(jugador2.getNombre());
	}

}
