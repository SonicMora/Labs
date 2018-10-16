package excepciones;

@SuppressWarnings("serial")
public class UsuarioNoEncontrado extends Exception {

	public UsuarioNoEncontrado(String aja) {
		super("No pudimos encontrar al usuario: "+aja);
	}
	
}
