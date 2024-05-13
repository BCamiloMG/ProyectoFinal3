package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.servlet.ServletContext;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.BJCyberNeticForrestM.Cancion;
import co.edu.unbosque.BJCyberNeticForrestM.PlayListDTO;


@ManagedBean
@SessionScoped
public class CancionBean {

	private int numCancion;
	private String genero_musical;
	private String nombre_cancion;
	private String nombre_artista;
	private String url;
	private ArrayList<Cancion> listaCanciones;
	private Part archivoMP3;
	private String nombreListaSeleccionada;
	private PlayListDTO playListSeleccionada;
	
	@ManagedProperty(value="#{playListBean}")
	private PlayListBean playListBean;
	
	@ManagedProperty("#{applicationBean}")
	private ApplicationBean applicationBean;
	
	public int getNumCancion() {
		return numCancion;
	}
	public void setNumCancion(int numCancion) {
		this.numCancion = numCancion;
	}
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
	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}
	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	public Part getArchivoMP3() {
		return archivoMP3;
	}
	public void setArchivoMP3(Part archivoMP3) {
		this.archivoMP3 = archivoMP3;
	}
	public PlayListDTO getPlayListSeleccionada() {
		return playListSeleccionada;
	}
	public void setPlayListSeleccionada(PlayListDTO playListSeleccionada) {
		this.playListSeleccionada = playListSeleccionada;
	}
	public PlayListBean getPlayListBean() {
		return playListBean;
	}
	public void setPlayListBean(PlayListBean playListBean) {
		this.playListBean = playListBean;
	}
	public String getNombreListaSeleccionada() {
		return nombreListaSeleccionada;
	}
	public void setNombreListaSeleccionada(String nombreListaSeleccionada) {
		this.nombreListaSeleccionada = nombreListaSeleccionada;
	}
	public ApplicationBean getApplicationBean() {
		return applicationBean;
	}
	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}
	public void crearCancion () {
		this.numCancion = 1;
		Cancion cancion = new Cancion(this.numCancion, this.genero_musical, this.nombre_cancion, this.nombre_artista, this.url);
		int respuesta = 0;
		try {
			respuesta = EmisoraJSON.postJSONCancion(cancion);
			if (respuesta == 200) {
				System.out.println("Registro Agregado!");
			} else {
				System.out.println("Error: " + respuesta);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inicializarCancion() {
		try {
			this.listaCanciones = EmisoraJSON.getJSONCancion();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void subirCancion() throws IOException {
	    FacesMessage message = null;

	    // Verificar si se ha seleccionado una lista de reproducción
	    if (playListSeleccionada == null) {
	        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una lista de reproducción.");
	    } else {
	        if (archivoMP3 != null) {
	            String fileName = Paths.get(archivoMP3.getSubmittedFileName()).getFileName().toString();
	            this.url = fileName;
	            crearCancion();
	            ServletContext context = applicationBean.getServletContext();
	            String folderPath = context.getRealPath("/canciones/");
	            
	            // Obtener el nombre de la canción del formulario
	            String nombreArchivo = this.nombre_cancion.trim().replaceAll("\\s+", ""); // Elimina espacios en blanco y los reemplaza con ""

	            // Cambiar el nombre del archivo MP3
	            String[] fileNameParts = fileName.split("\\."); // Separar el nombre del archivo y la extensión
	            String fileExtension = fileNameParts[fileNameParts.length - 1]; // Obtener la extensión del archivo
	            String nuevoNombreArchivo = nombreArchivo + "." + fileExtension; // Crear el nuevo nombre del archivo MP3

	            // Guardar el archivo MP3 con el nuevo nombre en la carpeta especificada
	            Path destinationPath = Paths.get(folderPath + nuevoNombreArchivo);
	            
	            Files.copy(archivoMP3.getInputStream(), destinationPath);
	            
	            // Setear la lista seleccionada en PlayListBean
	            playListBean.setListaSeleccionada(playListSeleccionada);

	            // Crear una instancia de la canción y agregarla a la lista de reproducción seleccionada
	            Cancion cancion = new Cancion(genero_musical, nombre_cancion, nombre_artista, destinationPath.toString(), playListSeleccionada.getNombre());
	            playListBean.agregarCancionALista(cancion);

	            // Actualizar la lista seleccionada después de agregar la canción
	            playListSeleccionada = playListBean.getListaSeleccionada();
	            
	            // Debugging: Verificar la lista seleccionada
	            System.out.println("Lista seleccionada: " + playListSeleccionada);
	        
	            // Mostrar el contenido de la lista de reproducción
	            if (playListSeleccionada != null && !playListSeleccionada.getCanciones().isEmpty()) {
	                StringBuilder playlistContent = new StringBuilder("Contenido de la lista de reproducción:\n");
	                for (Cancion song : playListSeleccionada.getCanciones()) {
	                    playlistContent.append(song.getNombre_cancion()).append("\n");
	                }
	                FacesMessage playlistMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contenido de la lista de reproducción" + playlistContent.toString(), playlistContent.toString());
	                FacesContext.getCurrentInstance().addMessage("playlistContent", playlistMessage);

	                // Mensaje de éxito
	                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La canción se ha agregado a la lista de reproducción.");
	            } else {
	                // Si la lista de reproducción está vacía
	                FacesMessage emptyListMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "La lista de reproducción está vacía.");
	                FacesContext.getCurrentInstance().addMessage("playlistContent", emptyListMessage);
	                return; // Evitar mostrar el mensaje de éxito si la lista está vacía
	            }
	        } else {
	            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un archivo MP3.");
	        }
	    }

	    // Mostrar el mensaje de error o éxito
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	@SuppressWarnings("unused")
	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
	
	public String volver() {
		return "menu.xhtml";
	}
}
