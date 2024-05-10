package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.edu.unbosque.BJCyberNeticForrestM.Cancion;
import co.edu.unbosque.BJCyberNeticForrestM.PlayListDTO;


@ManagedBean
@SessionScoped
public class PlayListBean {
	
	private String nombre;
	private List<PlayListDTO> listasReproduccion;
	private PlayListDTO listaSeleccionada;
	
	public PlayListBean() {
		listasReproduccion = new ArrayList<>();
	}

	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<PlayListDTO> getListasReproduccion() {
		return listasReproduccion;
	}



	public void setListasReproduccion(List<PlayListDTO> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}



	public PlayListDTO getListaSeleccionada() {
		return listaSeleccionada;
	}



	public void setListaSeleccionada(PlayListDTO listaSeleccionada) {
		this.listaSeleccionada = listaSeleccionada;
	}



	public void agregarListaReproduccion(String nombre) {
		PlayListDTO nuevaLista = new PlayListDTO(nombre);
		listasReproduccion.add(nuevaLista);
	}
	
	public void agregarCancionALista(Cancion cancion) {
	    if (listaSeleccionada != null) {
	        boolean cancionYaEstaEnLista = false;
	        
	        // Obtener la lista de canciones de la lista seleccionada
	        List<Cancion> cancionesEnLista = listaSeleccionada.getCanciones();
	        
	        // Iterar sobre las canciones en la lista seleccionada
	        for (Cancion cancionEnLista : cancionesEnLista) {
	            // Comparar los atributos relevantes de la canción que quieres agregar con los de cada canción en la lista
	            if (cancionEnLista.getNombre_cancion().equals(cancion.getNombre_cancion()) &&
	                cancionEnLista.getNombre_artista().equals(cancion.getNombre_artista()) 
	                // Añade más condiciones de comparación si es necesario
	                ) {
	                // La canción ya está en la lista
	                cancionYaEstaEnLista = true;
	                System.out.println("La canción ya está en la lista.");
	                break; // Salir del bucle, ya que encontramos la canción en la lista
	            }
	        }
	        
	        // Si la canción ya está en la lista, mostrar un mensaje al usuario y evitar agregarla nuevamente
	        if (cancionYaEstaEnLista) {
	            System.out.println("La canción ya está en la lista.");
	        } else {
	            // La canción no está en la lista, así que agrégala
	            listaSeleccionada.agregarCancion(cancion);
	            System.out.println("La canción se ha agregado a la lista de reproducción.");
	        }
	    }
	}
	
	public List<PlayListDTO> mostrarPlayLists() {
        return listasReproduccion;
    }
	

}
