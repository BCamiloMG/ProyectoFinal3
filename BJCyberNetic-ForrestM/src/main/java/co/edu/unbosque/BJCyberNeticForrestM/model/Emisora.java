package co.edu.unbosque.BJCyberNeticForrestM.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;


@EntityScan
public class Emisora {
	@Id
	/** El nombre de la emisora. */
	private String nombre_emisora;
	
	/** El tipo de transmisión de la emisora (por ejemplo, AM, FM, en línea). */
	private String transmicion;
	
	/** El tipo de música que se transmite en la emisora. */
	private String tipo_musica;
	
	/**
	 * Obtiene el nombre de la emisora.
	 * @return El nombre de la emisora.
	 */
	public String getNombre_emisora() {
		return nombre_emisora;
	}
	
	/**
	 * Establece el nombre de la emisora.
	 * @param nombre_emisora El nombre de la emisora a establecer.
	 */
	public void setNombre_emisora(String nombre_emisora) {
		this.nombre_emisora = nombre_emisora;
	}
	
	/**
	 * Obtiene el tipo de transmisión de la emisora.
	 * @return El tipo de transmisión.
	 */
	public String getTransmicion() {
		return transmicion;
	}
	
	/**
	 * Establece el tipo de transmisión de la emisora.
	 * @param transmicion El tipo de transmisión a establecer.
	 */
	public void setTransmicion(String transmicion) {
		this.transmicion = transmicion;
	}
	
	/**
	 * Obtiene el tipo de música transmitida por la emisora.
	 * @return El tipo de música.
	 */
	public String getTipo_musica() {
		return tipo_musica;
	}
	
	/**
	 * Establece el tipo de música transmitida por la emisora.
	 * @param tipo_musica El tipo de música a establecer.
	 */
	public void setTipo_musica(String tipo_musica) {
		this.tipo_musica = tipo_musica;
	}

}
