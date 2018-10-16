package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
	void compareToTest() {
		escenario2();
		player.setPuntos(20);
		Jugador j2=new Jugador("Anderson");
		j2.setPuntos(10);
		assertEquals(-1, player.compareTo(j2));
	}
	
	@Test
	void ordenNombresTest() {
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
	void ordenPuntos() {
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

}
