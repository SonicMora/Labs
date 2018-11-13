package excepciones;

@SuppressWarnings("serial")
public class ParticipanteNoExisteException extends Exception {

	public ParticipanteNoExisteException(long id) {
		super("No se encontró el participante con el id: "+id);
	}
	
}
