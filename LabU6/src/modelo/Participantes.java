package modelo;

public class Participantes {

	private Programador cabeza;
	
	public Participantes() {
		cabeza=null;
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
	
}
