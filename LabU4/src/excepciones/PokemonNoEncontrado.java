package excepciones;

@SuppressWarnings("serial")
public class PokemonNoEncontrado extends Exception {

	public PokemonNoEncontrado(String aja) {
		super("No pudimos encontrar el pokemon: "+aja);
	}
	
}
