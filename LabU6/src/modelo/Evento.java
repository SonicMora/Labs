package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Evento {

	private ABB arbol;
	private ListaParticipante lista;
	
	public Evento() {
		arbol=new ABB();
		lista=new ListaParticipante();
	}
	
	public void cargarDatos(File ruta) throws NumberFormatException, IOException{
		BufferedReader lector = new BufferedReader(new FileReader(ruta));
		String linea;			
		while((linea=lector.readLine())!=null) {
			String[] wepa=linea.split(",");
			arbol.add(arbol.getRaiz(), new Programador(Long.parseLong(wepa[0]), wepa[1], wepa[2], wepa[3], wepa[4], wepa[5]));
		}
		lector.close();
		agregarALista(0);
	}
	
//	public void crearLista(int contador) {
//		SecureRandom ran =new SecureRandom();
//		if(contador<(arbol.getPeso()/2)) {
//			try {
//				Programador a=arbol.buscar(arbol.getRaiz(), (ran.nextInt(1000)+1));
//				lista.add(lista.getCabeza(), a);
//			} catch (ProgramadorNoExisteException e) {
//				crearLista(contador);
//			}
//			crearLista(contador++);
//		}
//		System.out.println(lista.getSize());
//	}
	
	public void crearLista(int x) {
		SecureRandom ran=new SecureRandom();
		try {
			if(arbol.buscar(arbol.getRaiz(), (1+ran.nextInt(1000))).isParticipante()==false) {
				lista.add(lista.getCabeza(), arbol.buscar(arbol.getRaiz(), (1+ran.nextInt(1000))));
			}else {
				agregarALista(x);
			}
		}catch(Exception e) {
			agregarALista(x);
		}
		agregarALista(x++);
	}
	
	public void agregarALista(int c) {
		if(c<(arbol.getPeso()/2)) {
			crearLista(c);
		}
	}
	
	public void rutasProgramadores(Programador actual, ArrayList<Programador> nombres){
		if(actual!=null ) {
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

	public ListaParticipante getLista() {
		return lista;
	}

	public void setLista(ListaParticipante lista) {
		this.lista = lista;
	}
	
}
