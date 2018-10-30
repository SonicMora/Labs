package exceptions;

@SuppressWarnings("serial")
public class EmptyList extends Exception {

	public EmptyList() {
		super("No se puede realizar la operacion porque la lista esta vacia");
	}
	
}
