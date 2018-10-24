package exceptions;

@SuppressWarnings("serial")
public class NoSuchOwner extends Exception {

	public NoSuchOwner(String flaw) {
		super("No logramos encontrar el dueño especificado: "+flaw);
	}
	
}
