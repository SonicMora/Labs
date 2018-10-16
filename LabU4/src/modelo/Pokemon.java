package modelo;

public class Pokemon {
	
	private String nombre;
	private String ruta;

	private boolean detenido;
	
	private int posX;
	private int posY;
		
	public Pokemon(String nombre, int posX, int posY) {
		this.setNombre(nombre);
		this.ruta = "./data/pikachu.gif";
		this.detenido = false;
		this.posX = posX;
		this.posY = posY;
	}
		
	public String darRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public boolean isDetenido() {
		return detenido;
	}

	public void setDetenido(boolean detenido) {
		this.detenido = detenido;
	}
	
	public void avanzar(int dist) {
		setPosX(posX-=dist);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean verificarposicion(int x) {
		return false;
	}
	
	public boolean atrapado(int x, int y) {
		if(x-getPosX()>20 && getPosX()>80 && y-getPosY()>5) {
			return true;
		}
		return false;
	}
	
	public void reiniciar() {
		setPosX(500);
	}
	
	public void encapsularPokemon() {
		this.ruta = "/data/pokeBall.png";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
	
}
