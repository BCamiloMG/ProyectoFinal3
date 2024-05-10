package co.edu.unbosque.BJCyberNeticForrestM;

public class Emisora {

	private int numEmisora;
	/** El nombre de la emisora. */
	private String nombre_emisora;
	
	/** El tipo de transmisión de la emisora (por ejemplo, AM, FM, en línea). */
	private String transmision;
	
	/** El tipo de música que se transmite en la emisora. */
	private String tipo_musica;
	
public Emisora() {
	// TODO Auto-generated constructor stub
}

public Emisora(int numEmisora, String nombre_emisora, String transmision, String tipo_musica) {
	super();
	this.numEmisora = numEmisora;
	this.nombre_emisora = nombre_emisora;
	this.transmision = transmision;
	this.tipo_musica = tipo_musica;
}

public int getNumEmisora() {
	return numEmisora;
}

public void setNumEmisora(int numEmisora) {
	this.numEmisora = numEmisora;
}

public String getNombre_emisora() {
	return nombre_emisora;
}

public void setNombre_emisora(String nombre_emisora) {
	this.nombre_emisora = nombre_emisora;
}

public String getTransmision() {
	return transmision;
}

public void setTransmision(String transmision) {
	this.transmision = transmision;
}

public String getTipo_musica() {
	return tipo_musica;
}

public void setTipo_musica(String tipo_musica) {
	this.tipo_musica = tipo_musica;
}


}
