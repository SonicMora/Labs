package exceptions;

@SuppressWarnings("serial")
public class ExistentOwner extends Exception {

	public ExistentOwner(int id) {
		super("Ya existe un dueño con la cedula especificada: "+id);
	}
	
}
