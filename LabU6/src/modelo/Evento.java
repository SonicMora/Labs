package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Evento {

	private ABB arbol;
	private Participantes lista;
	
	public Evento() {
		arbol=new ABB();
		lista=new Participantes();
	}
	
	public void cargarDatos(File ruta) throws NumberFormatException, IOException{
		BufferedReader lector = new BufferedReader(new FileReader(ruta));
		String linea;			
		while((linea=lector.readLine())!=null) {
			String[] wepa=linea.split(",");
			arbol.add(arbol.getRaiz(), new Programador(Long.parseLong(wepa[0]), wepa[1], wepa[2], wepa[3], wepa[4], wepa[5]));
		}
		lector.close();
		arbol.inorden(arbol.getRaiz());
//		cargarLista(0);
	}
	
	public void crearLista() {
		SecureRandom ran=new SecureRandom();
		int t=1+ran.nextInt(1000);
		if(arbol.buscarAleatorio(arbol.getRaiz(), t)!=null) {
			lista.add(lista.getCabeza(), arbol.buscarAleatorio(arbol.getRaiz(), t));
		}else{
			crearLista();
		}
	}
	
	public void cargarLista(int cont) {
		while(cont<500) {
			crearLista();
			cont++;
		}
	}
	
	public void rutasProgramadores(Programador actual, ArrayList<Programador> nombres){
		if(actual != null ) {
			nombres.add(actual);
			rutasProgramadores(actual.getIzquierdo(), nombres);
			rutasProgramadores(actual.getDerecho(), nombres);
		}
	}
	
	public ArrayList<Programador> programadores(){
		ArrayList<Programador> nombres = new ArrayList<Programador>();
		rutasProgramadores(getArbol().getRaiz(), nombres);
		return nombres;
	}
	
	public ABB getArbol() {
		return arbol;
	}

	public void setArbol(ABB arbol) {
		this.arbol = arbol;
	}

	public Participantes getLista() {
		return lista;
	}

	public void setLista(Participantes lista) {
		this.lista = lista;
	}
	
}
