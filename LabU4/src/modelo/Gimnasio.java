package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import excepciones.PokemonNoEncontrado;
import excepciones.UsuarioNoEncontrado;

public class Gimnasio {
	
	private Pokemon pokemon;
	
	private Pokebola pokeBall;
	
	private static Jugador actual;
	
	private ArrayList<Pokemon> pokemones;
 	private ArrayList<Jugador> jugadores;

	private ComparadorNombre comparator;
	private ComparadorPokemon comparatorP;
	
	public Gimnasio() {		
		pokemones=new ArrayList<Pokemon>();
		pokemones.add(new Pokemon("Pikachu",500, 300));
		pokemones.get(0).setRuta("src/data/pikachu.gif");
		pokemones.add(new Pokemon("Joelton",500, 300));
		pokemones.get(1).setRuta("src/data/weird.gif");
		pokemones.add(new Pokemon("Pato",500, 300));
		pokemones.get(2).setRuta("src/data/pato.gif");
		pokemones.add(new Pokemon("Charizard",500, 300));
		pokemones.get(3).setRuta("src/data/charizard.gif");
		pokeBall=new Pokebola("/data/pokeBall.png", new Pokemon("aja", 500, 300));
//		pokeBall.getPokemon().setRuta("/data/charizard.gif");
	}
	
	public Pokemon darPokemon() {		
		return this.pokemon;
	}
	
	public Pokebola getPokeBall() {
		return pokeBall;
	}

	public void setPokeBall(Pokebola pokeBall) {
		this.pokeBall = pokeBall;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public static Jugador getActual() {
		return actual;
	}

	public static void setActual(Jugador actual2) {
		actual = actual2;
	}
	
	@SuppressWarnings("unchecked")
	public void aja(String nickname) {
		try {
			ObjectInputStream mostrar=new ObjectInputStream(new FileInputStream("src/partidas/Jugadores.dat"));
			jugadores=(ArrayList<Jugador>) mostrar.readObject();
			mostrar.close();
		}catch(Exception e) {
			jugadores=new ArrayList<Jugador>();
		}
		if(verificarNick(nickname)==false) {
			if(jugadores.size()<20) {
				jugadores.add(new Jugador(nickname));
			}else {
				jugadores.remove(jugadores.size()-1);
				jugadores.add(new Jugador(nickname));
			}
		}else {
			for(int i=0;i<jugadores.size();i++) {
				if(nickname.equalsIgnoreCase(jugadores.get(i).getNombre())){
					jugadores.get(i).setPuntos(jugadores.get(i).getPuntos());
				}
			}
		}
		actual=new Jugador(nickname);
	}
	
	public boolean verificarNick(String m){
		for(int i=0;i<jugadores.size();i++) {
			if(m.equalsIgnoreCase(jugadores.get(i).getNombre())){
				return true;
			}
		}
		return false;
	}
		
	public void guardar() {
		try {
			jugadores.get(jugadores.size()-1).setPuntos(actual.getPuntos());
			ObjectOutputStream escribir=new ObjectOutputStream(new FileOutputStream("src/partidas/Jugadores.dat"));
			escribir.writeObject(jugadores);
			escribir.close();
		}catch(IOException e) {
		
		}
	}
	
	public ArrayList<Jugador> ordenNombresJ(ArrayList<Jugador> arreglo) {
		comparator=new ComparadorNombre();
		for(int i=0;i<arreglo.size()-1;i++) {
			Jugador menor=arreglo.get(i);
			int cual=i;
			for(int j=i+1;j<arreglo.size();j++) {
				if(comparator.compare(arreglo.get(j), menor)<1) {
					menor=arreglo.get(j);
					cual=j;
				}
			}
			Jugador temp=arreglo.get(i);
			arreglo.set(i, menor);
			arreglo.set(cual, temp);
		}
		return arreglo;
	}
	
	public ArrayList<Pokemon> ordenNombresP(ArrayList<Pokemon> arreglo) {
		comparatorP=new ComparadorPokemon();
		for(int i=0;i<arreglo.size()-1;i++) {
			Pokemon menor=arreglo.get(i);
			int cual=i;
			for(int j=i+1;j<arreglo.size();j++) {
				if(comparatorP.compare(arreglo.get(j), menor)<1) {
					menor=arreglo.get(j);
					cual=j;
				}
			}
			Pokemon temp=arreglo.get(i);
			arreglo.set(i, menor);
			arreglo.set(cual, temp);
		}
		return arreglo;
	}
	
	public ArrayList<Jugador> ordenPuntos(ArrayList<Jugador> arreglo) {
		for(int i=1;i<arreglo.size();i++) {
			Jugador porInsertar=(Jugador)arreglo.get(i);
			boolean termino=false;
			for(int j=i;j>0 && !termino;j--) {
				Jugador actual=(Jugador)arreglo.get(j-1);
				if(actual.compareTo(porInsertar)>0) {
					arreglo.set(j, actual);
					arreglo.set(j-1, porInsertar);
				}else {
					termino=true;
				}
			}
		}
		return arreglo;
	}
	
	public String busquedaBinariaJ(String valor, ArrayList<Jugador> arreglo)  throws UsuarioNoEncontrado{
		comparator=new ComparadorNombre();
		ArrayList<Jugador> arregloAcomodado=ordenNombresJ(arreglo);
		boolean encontre=false;
		int inicio=0;
		int medio=0;
		int fin=arreglo.size()-1;
		while(inicio<=fin && !encontre){
				medio=(inicio+fin)/2;
			if(arregloAcomodado.get(medio).getNombre().equals(valor)){
				encontre=true;
			}else if(comparator.compare(arregloAcomodado.get(medio), new Jugador(valor))>1){
				fin=medio-1;
			}else{
				inicio=medio+1;
			}
		}
		if(encontre==false) {
			throw new UsuarioNoEncontrado(valor);
		}else {
			return arregloAcomodado.get(medio).toString();
		}
		
	}
	
	public String busquedaBinariaP(String valor) throws PokemonNoEncontrado{
		ArrayList<Pokemon> arreglo=ordenNombresP(getPokemones());
		boolean encontre=false;
		int inicio=0;
		int medio=0;
		int fin=arreglo.size()-1;
		while(inicio<=fin && !encontre){
				medio=(inicio+fin)/2;
			if(arreglo.get(medio).getNombre().equals(valor)){
				encontre=true;
			}else if(arreglo.get(medio).getNombre().equals(valor)){
				fin=medio-1;
			}else{
				inicio=medio+1;
			}
		}
		if(encontre==false) {
			throw new PokemonNoEncontrado(valor);
		}else {
			return arreglo.get(medio).toString();
		}
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(ArrayList<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}
	
	
	
}
