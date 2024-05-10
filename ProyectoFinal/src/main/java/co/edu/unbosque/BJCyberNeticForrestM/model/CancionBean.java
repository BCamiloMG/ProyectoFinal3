package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

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
	private PlayListDTO playListSeleccionada;
	
	@ManagedProperty(value="#{playListBean}")
	private PlayListBean playListBean;
	
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
	
	public void subirCancion() {
	    if (archivoMP3 != null) {
	        try (InputStream input = archivoMP3.getInputStream()) {
	            String fileName = getFileName(archivoMP3);
	            String path = "../canciones" + fileName; // Ruta donde quieres guardar la canción
	            try (OutputStream output = new FileOutputStream(new File(path))) {
	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = input.read(buffer)) != -1) {
	                    output.write(buffer, 0, bytesRead);
	                }
	                // Agregar la canción a la lista de reproducción seleccionada
	                Cancion cancion = new Cancion(genero_musical, nombre_cancion, nombre_artista, path, playListBean.getListaSeleccionada().getNombre());
	                playListBean.agregarCancionALista(cancion);
	                FacesMessage message = new FacesMessage("Éxito", "La canción se ha agregado a la lista de reproducción.");
	                FacesContext.getCurrentInstance().addMessage(null, message);
	            } catch (IOException e) {
	                e.printStackTrace();
	                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar la canción.");
	                FacesContext.getCurrentInstance().addMessage(null, message);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al subir la canción.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }
	    }
	}
	
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
}
