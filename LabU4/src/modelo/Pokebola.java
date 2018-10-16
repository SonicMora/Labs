package modelo;

public class Pokebola {

	private String imagen;
	private Pokemon pokemon;
	
	public Pokebola(String imagen, Pokemon pokemon) {
		this.imagen = imagen;
		this.pokemon = pokemon;
	}
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	
}
