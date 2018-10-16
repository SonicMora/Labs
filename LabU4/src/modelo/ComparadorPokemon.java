package modelo;

import java.util.Comparator;

public class ComparadorPokemon implements Comparator<Pokemon> {

	@Override
	public int compare(Pokemon arg0, Pokemon arg1) {
		return arg0.getNombre().compareToIgnoreCase(arg1.getNombre());
	}

}
