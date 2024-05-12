package co.edu.unbosque.BJCyberNeticForrestM;

import java.util.ArrayList;
import java.util.List;

public class PlayListDTO {
	
	private String nombre;
	private List<Cancion> canciones;
	
	public PlayListDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PlayListDTO(String nombre) {
		super();
		this.nombre = nombre;
		this.canciones = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	public void agregarCancion(Cancion cancion) {
		this.canciones.add(cancion);
	}

}
