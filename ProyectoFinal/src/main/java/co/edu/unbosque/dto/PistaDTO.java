package co.edu.unbosque.dto;

/**
 * Esta clase representa la información de una pista musical.
 */
public class PistaDTO {

	/** El género musical de la pista. */
	private String genero_musical;
	
	/** El nombre de la canción. */
	private String nombre_cancion;
	
	/** El nombre del artista de la canción. */
	private String nombre_artista;
	
	/** La URL del archivo de la pista. */
	private String url_archivo;
	
	/**
	 * Obtiene el género musical de la pista.
	 * @return El género musical.
	 */
	public String getGenero_musical() {
		return genero_musical;
	}
	
	/**
	 * Establece el género musical de la pista.
	 * @param genero_musical El género musical a establecer.
	 */
	public void setGenero_musical(String genero_musical) {
		this.genero_musical = genero_musical;
	}
	
	/**
	 * Obtiene el nombre de la canción.
	 * @return El nombre de la canción.
	 */
	public String getNombre_cancion() {
		return nombre_cancion;
	}
	
	/**
	 * Establece el nombre de la canción.
	 * @param nombre_cancion El nombre de la canción a establecer.
	 */
	public void setNombre_cancion(String nombre_cancion) {
		this.nombre_cancion = nombre_cancion;
	}
	
	/**
	 * Obtiene el nombre del artista de la canción.
	 * @return El nombre del artista.
	 */
	public String getNombre_artista() {
		return nombre_artista;
	}
	
	/**
	 * Establece el nombre del artista de la canción.
	 * @param nombre_artista El nombre del artista a establecer.
	 */
	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}
	
	/**
	 * Obtiene la URL del archivo de la pista.
	 * @return La URL del archivo de la pista.
	 */
	public String getUrl_archivo() {
		return url_archivo;
	}
	
	/**
	 * Establece la URL del archivo de la pista.
	 * @param url_archivo La URL del archivo de la pista a establecer.
	 */
	public void setUrl_archivo(String url_archivo) {
		this.url_archivo = url_archivo;
	}
}
