package exceptions;

@SuppressWarnings("serial")
public class ExistentOwner extends Exception {

	public ExistentOwner(int id) {
		super("Ya existe un due�o con la cedula especificada: "+id);
	}
	
}
