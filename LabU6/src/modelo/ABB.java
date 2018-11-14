package modelo;

import excepciones.ProgramadorNoExisteException;

public class ABB {

	private Programador raiz;
	
	private int peso;
	
	public ABB() {
		raiz=null;
		peso=0;
	}
	
	public void add(Programador actual, Programador nuevo) {
		if(raiz==null) {
			nuevo.setPosX(500);
			nuevo.setPosY(25);
			raiz=nuevo;
			peso+=1;
		}else {
			if(nuevo.getId()<=actual.getId()) {
				if(actual.getIzquierdo()==null) {
					nuevo.setPosX(actual.getPosX()-actual.getDistancia());
					nuevo.setPosY(actual.getPosY()+50);
					actual.setIzquierdo(nuevo);
					peso+=1;
				}else {
					add(actual.getIzquierdo(), nuevo);
				}
			}else {
				if(actual.getDerecho()==null) {
					nuevo.setPosX(actual.getPosX()+actual.getDistancia());
					nuevo.setPosY(actual.getPosY()+50);
					actual.setDerecho(nuevo);
					peso+=1;
				}else {
					add(actual.getDerecho(), nuevo);
				}
			}
		}
	}

	public Programador buscar(Programador actual, long id) throws ProgramadorNoExisteException {
		if(actual.getId()==id) {
			return actual;
		}else {
			if(id<actual.getId()) {
				if(actual.getIzquierdo()!=null) {
					return buscar(actual.getIzquierdo(), id);
				}else {
					throw new ProgramadorNoExisteException(id);
				}
			}else {
				if(actual.getDerecho()!=null) {
					return buscar(actual.getDerecho(), id);
				}else {
					throw new ProgramadorNoExisteException(id);
				}
			}
		}
	}
	
	public Programador getRaiz() {
		return raiz;
	}

	public void setRaiz(Programador raiz) {
		this.raiz = raiz;
	}
	
	public boolean vacio(Programador a) {
		return (a==null);
	}
	
	public Programador getIzqArbol(Programador a) {
		if(a.getIzquierdo() == null){
			return null;
		}else{
			return a.getIzquierdo();
		}
	}
	
	public Programador getDerArbol(Programador a) {
		if(a.getDerecho() == null){
			return null;
		}else{
			return a.getDerecho();
		}
	}
	
	public void inorden(Programador a){
		if(!vacio(a)){
			inorden(getIzqArbol(a));
			System.out.print(a.getNombre()+" ");
			inorden(getDerArbol(a));
		}           
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
		
	
}