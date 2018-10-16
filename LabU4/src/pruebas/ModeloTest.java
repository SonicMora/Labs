package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.PokemonNoEncontrado;
import excepciones.UsuarioNoEncontrado;
import modelo.Gimnasio;
import modelo.Jugador;

class ModeloTest {

	private Gimnasio gym;
	private Jugador player;
	
	public void escenario1() {
		gym=new Gimnasio();
	}
	
	public void escenario2() {
		player=new Jugador("William");
	}
	
	@Test
	void testCompareTo() {
		escenario2();
		player.setPuntos(20);
		Jugador j2=new Jugador("Anderson");
		j2.setPuntos(10);
		assertEquals(-1, player.compareTo(j2));
	}
	
	@Test
	void testOrdenNombres() {
		escenario1();
		ArrayList<Jugador> nea=new ArrayList<Jugador>();
		Jugador j3=new Jugador("Zarama");
		Jugador j1=new Jugador("Jurado");
		Jugador j2=new Jugador("Arana");
		nea.add(j3);
		nea.add(j1);
		nea.add(j2);
		ArrayList<Jugador> nea1=new ArrayList<Jugador>();
		nea1.add(nea.get(2));
		nea1.add(nea.get(1));
		nea1.add(nea.get(0));
		assertEquals(nea1, gym.ordenNombresJ(nea));
	}
	
	@Test
	void tesOrdenPuntos() {
		escenario1();
		ArrayList<Jugador> nea=new ArrayList<Jugador>();
		Jugador j1=new Jugador("Zarama");
		j1.setPuntos(10);
		Jugador j2=new Jugador("Jurado");
		j2.setPuntos(30);
		Jugador j3=new Jugador("Arana");
		j3.setPuntos(20);
		nea.add(j1);
		nea.add(j2);
		nea.add(j3);
		ArrayList<Jugador> nea1=new ArrayList<Jugador>();
		nea1.add(nea.get(1));
		nea1.add(nea.get(2));
		nea1.add(nea.get(0));
		assertEquals(nea1, gym.ordenPuntos(nea));
	}

	@Test
	void testBusquedaBinariaJ() {
		escenario1();
		ArrayList<Jugador> nea=new ArrayList<Jugador>();
		Jugador j1=new Jugador("Zarama");
		j1.setPuntos(10);
		Jugador j2=new Jugador("Jurado");
		j2.setPuntos(30);
		Jugador j3=new Jugador("Arana");
		j3.setPuntos(20);
		nea.add(j1);
		nea.add(j2);
		nea.add(j3);
		try {
			assertEquals(nea.get(0).toString(), gym.busquedaBinariaJ("Zarama", nea));
		} catch (UsuarioNoEncontrado e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void testBusquedaBinariaJExcepcion() {
		escenario1();
		ArrayList<Jugador> nea=new ArrayList<Jugador>();
		Jugador j1=new Jugador("Zarama");
		j1.setPuntos(10);
		Jugador j2=new Jugador("Jurado");
		j2.setPuntos(30);
		Jugador j3=new Jugador("Arana");
		j3.setPuntos(20);
		nea.add(j1);
		nea.add(j2);
		nea.add(j3);
		try {
			assertEquals(UsuarioNoEncontrado.class, gym.busquedaBinariaJ("Carlos", nea));
		} catch (UsuarioNoEncontrado e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void testBusquedaBinariaP() {
		escenario1();
		try {
			assertEquals("Joelton", gym.busquedaBinariaP("Joelton"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void testBusquedaBinariaPExcepcion() {
		escenario1();
		try {
			assertEquals(PokemonNoEncontrado.class, gym.busquedaBinariaP("Lucario"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
