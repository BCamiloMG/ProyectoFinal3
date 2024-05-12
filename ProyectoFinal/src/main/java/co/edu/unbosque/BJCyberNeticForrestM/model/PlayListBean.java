package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

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
		listaSeleccionada = new PlayListDTO();
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Este es un mensaje de prueba");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public PlayListDTO buscarPlayListPorNombre(String nombre) {
        if (listasReproduccion != null) {
            for (PlayListDTO playlist : listasReproduccion) {
                if (playlist.getNombre().equals(nombre)) {
                    return playlist;
                }
            }
        }
        return null; // Si no se encuentra ninguna lista de reproducción con ese nombre
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
	
	public void reproducirCancionesEnOrden() {
	    if (listaSeleccionada != null && !listaSeleccionada.getCanciones().isEmpty()) {
	        // Obtener la lista de canciones de la lista seleccionada
	        List<Cancion> canciones = listaSeleccionada.getCanciones();
	        
	        // Iterar sobre las canciones y establecer autoplay en true
	        for (Cancion cancion : canciones) {
	            cancion.setAutoplay(true);
	        }
	        
	        // Llamar a la función JavaScript para reproducir la primera canción automáticamente
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reproducción automática iniciada.", "Reproduciendo la primera canción automáticamente."));
	        PrimeFaces.current().executeScript("playFirstSong();");
	    } else {
	        System.out.println("La lista de reproducción está vacía.");
	    }
	}
	
	public void actualizarCanciones() {
	    // Verificar si se ha seleccionado una lista de reproducción
	    if (listaSeleccionada != null) { // Utilizando el atributo playListSeleccionada
	        // Obtener la lista de canciones de la lista seleccionada
	        List<Cancion> cancionesEnLista = listaSeleccionada.getCanciones();

	        // Debugging: Verificar las canciones en la lista seleccionada
	        System.out.println("Canciones en la lista seleccionada:");
	        for (Cancion cancion : cancionesEnLista) {
	            System.out.println(cancion.getNombre_cancion());
	        }

	        // Actualizar la tabla de canciones en la interfaz de usuario mediante AJAX
	        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:tablaCanciones");
	    } else {
	        // Si no se ha seleccionado una lista de reproducción, mostrar un mensaje de error
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una lista de reproducción.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	}
	
	public String volver() {
		return "menu.xhtml";
	}
	
	public String getCancionesJSON() {
	    List<Cancion> canciones = getCancionesSeleccionadas();
	    StringBuilder json = new StringBuilder();
	    json.append("[");
	    for (Cancion cancion : canciones) {
	        json.append("{");
	        json.append("\"nombre\": \"" + cancion.getNombre_cancion() + "\",");
	        json.append("\"src\": \"" + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/recursos/canciones/" + cancion.getNombre_cancion() + ".mp3\"");
	        json.append("},");
	    }
	    if (canciones.size() > 0) {
	        json.deleteCharAt(json.length() - 1); // Eliminar la coma final
	    }
	    json.append("]");
	    return json.toString();
	}
	
	public List<Cancion> getCancionesSeleccionadas() {
	    List<Cancion> canciones = listaSeleccionada != null ? listaSeleccionada.getCanciones() : new ArrayList<>();
	    System.out.println("Cantidad de canciones obtenidas: " + canciones.size());
	    for (Cancion cancion : canciones) {
	        System.out.println("Nombre de la canción: " + cancion.getNombre_cancion());
	    }
	    return canciones;
	}
}
