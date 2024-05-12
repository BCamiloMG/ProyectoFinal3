package co.edu.unbosque.BJCyberNeticForrestM;

public class Cancion {
	
	private int numCancion;
	private String genero_musical;
	private String nombre_cancion;
	private String nombre_artista;
	private String url;
	private String playList;
	private boolean autoplay;
	
	public Cancion() {
		// TODO Auto-generated constructor stub
	}

	public Cancion(int numCancion, String genero_musical, String nombre_cancion, String nombre_artista, String url) {
		super();
		this.numCancion = numCancion;
		this.genero_musical = genero_musical;
		this.nombre_cancion = nombre_cancion;
		this.nombre_artista = nombre_artista;
		this.url = url;
	}
	
	public Cancion(String genero_musical, String nombre_cancion, String nombre_artista, String url, String playList) {
		super();
		this.genero_musical = genero_musical;
		this.nombre_cancion = nombre_cancion;
		this.nombre_artista = nombre_artista;
		this.url = url;
		this.playList = playList;
	}

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

	public String getPlayList() {
		return playList;
	}

	public void setPlayList(String playList) {
		this.playList = playList;
	}

	public boolean isAutoplay() {
		return autoplay;
	}

	public void setAutoplay(boolean autoplay) {
		this.autoplay = autoplay;
	}
	
}
