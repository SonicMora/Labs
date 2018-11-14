package modelo;

public class ListaParticipante {

	private Programador cabeza;
	private int size;
	
	public ListaParticipante() {
		cabeza=null;
		size=0;
	}
	
	public Programador getCabeza() {
		return cabeza;
	}
	
	public void setCabeza(Programador cabeza) {
		this.cabeza=cabeza;
	}
	
	public void add(Programador actual, Programador nuevo) {
		if(cabeza==null) {
			cabeza=nuevo;
			size++;
		}else if(actual.getDerecho()==null) {
			actual.setDerecho(nuevo);
		}else {
			add(actual.getDerecho(), nuevo);
		}
	}

	public Programador buscar(Programador actual, long id) {
		if(actual==null) {
			return null;
		}else if(actual.getId()==id) {
			return actual;
		}else {
			return buscar(actual.getDerecho(), id);
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
