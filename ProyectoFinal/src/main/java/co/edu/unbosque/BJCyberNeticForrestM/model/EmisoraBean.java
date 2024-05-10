package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.io.IOException;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.BJCyberNeticForrestM.Emisora;


@ManagedBean 
public class EmisoraBean {

	private int numEmisora;
	/** El nombre de la emisora. */
	private String nombre_emisora;
	
	/** El tipo de transmisión de la emisora (por ejemplo, AM, FM, en línea). */
	private String transmision;
	
	/** El tipo de música que se transmite en la emisora. */
	private String tipo_musica;
	
	private ArrayList<Emisora> listaEmisoras;

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

	public ArrayList<Emisora> getListaEmisoras() {
		return listaEmisoras;
	}

	public void setListaEmisoras(ArrayList<Emisora> listaEmisoras) {
		this.listaEmisoras = listaEmisoras;
	}
	
	public void crearEmisora () {
		this.numEmisora = 1;
		Emisora emisora = new Emisora(this.numEmisora, this.nombre_emisora, this.transmision, this.tipo_musica);
		int respuesta = 0;
		try {
			respuesta = EmisoraJSON.postJSON(emisora);
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
	
	public void inicializarEmisora() {
		try {
			this.listaEmisoras = EmisoraJSON.getJSONEmisora();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
