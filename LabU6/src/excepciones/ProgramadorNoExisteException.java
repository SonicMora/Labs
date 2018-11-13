package excepciones;

@SuppressWarnings("serial")
public class ProgramadorNoExisteException extends Exception {

	public ProgramadorNoExisteException(long id) {
		super("No se encontr� en programador con el id: "+id);
	}
	
}
