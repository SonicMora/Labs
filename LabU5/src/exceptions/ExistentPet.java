package exceptions;

@SuppressWarnings("serial")
public class ExistentPet extends Exception {

	public ExistentPet(String name) {
		super("Este due�o ya tiene una mascota registrada con el nombre especificado: "+name);
	}
	
}
