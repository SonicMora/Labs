package exceptions;

@SuppressWarnings("serial")
public class NoSuchPet extends Exception {

	public NoSuchPet(String flaw) {
		super("No logramos encontrar la mascota especificada: "+flaw);
	}
	
}
