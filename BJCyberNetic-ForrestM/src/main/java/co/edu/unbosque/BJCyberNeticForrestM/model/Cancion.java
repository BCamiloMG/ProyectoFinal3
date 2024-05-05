package co.edu.unbosque.BJCyberNeticForrestM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cancion {
	
	@Id
	private int numCancion;
	private String genero_musical;
	private String nombre_cancion;
	private String nombre_artista;
	private String url;
	
	public String getGenero_musical() {
		return genero_musical;
	}
	public void setGenero_musical(String genero_musical) {
		this.genero_musical = genero_musical;
	}
	public String getNombre_cancion() {
		return nombre_cancion;
	}
	public void setNombre_cancion(String nombre_cancion) {
		this.nombre_cancion = nombre_cancion;
	}
	public String getNombre_artista() {
		return nombre_artista;
	}
	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
