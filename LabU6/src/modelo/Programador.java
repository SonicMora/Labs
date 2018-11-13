package modelo;

public class Programador {

	private long id;
	
	private String nombre;
	private String apellido;
	private String correo;
	private String genero;
	private String avatar;
	
	private boolean participante;
	
	private Programador derecho;
	private Programador izquierdo;
	
	private int posX;
	private int posY;
	private int distancia;
	
	public Programador(long id, String nombre, String apellido, String correo, String genero, String avatar) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.genero = genero;
		this.avatar = avatar;
		
		setParticipante(false);
		
		posX=0;
		posY=0;
		distancia=100;
		
		derecho=null;
		izquierdo=null;
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

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Programador getDerecho() {
		return derecho;
	}

	public void setDerecho(Programador derecho) {
		this.derecho = derecho;
	}

	public Programador getIzquierdo() {
		return izquierdo;
	}

	public void setIzquierdo(Programador izquierdo) {
		this.izquierdo = izquierdo;
	}
	
	public boolean esHoja() {
		return (getDerecho()==null && getIzquierdo()==null);
	}

	public boolean isParticipante() {
		return participante;
	}

	public void setParticipante(boolean participante) {
		this.participante = participante;
	}
	
}
