package co.edu.unbosque.BJCyberNeticForrestM.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.BJCyberNeticForrestM.Cancion;
import co.edu.unbosque.BJCyberNeticForrestM.Emisora;



public class EmisoraJSON {

	private static URL url;
	private static String sitio = "http://localhost:8088/";
	
	public static ArrayList<Emisora> getJSONEmisora() throws IOException, ParseException{
		url = new URL(sitio+"emisora/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Emisora> lista = new ArrayList<Emisora>();
		lista = parsingEmisoras(json);
		http.disconnect();
		return lista;
	}
	
	private static ArrayList<Emisora> parsingEmisoras(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Emisora> lista = new ArrayList<Emisora>();
        JSONArray emisoras = (JSONArray) jsonParser.parse(json);
        Iterator i = emisoras.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Emisora emisora = new Emisora();
            emisora.setNumEmisora(Integer.parseInt(innerObj.get("num_emisora").toString())); 
            emisora.setNombre_emisora(innerObj.get("nombre_emisora").toString());
            emisora.setTipo_musica(innerObj.get("tipo_musica").toString());
            emisora.setTransmision(innerObj.get("transmision").toString());
            lista.add(emisora);
        }
        return lista;
	}
	
	public static int postJSON(Emisora emisora) throws IOException {
		url = new URL(sitio+"emisora/guardar");

		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"num_emisora\":\""+ emisora.getNumEmisora()
				+"\",\"nombre_emisora\": \""+emisora.getNombre_emisora()
				+"\",\"tipo_musica\": \""+emisora.getTipo_musica()
				+"\",\"transmicion\":\""+emisora.getTransmision()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	public static ArrayList<Cancion> getJSONCancion() throws IOException, ParseException{
		url = new URL(sitio+"cancion/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		lista = parsingCanciones(json);
		http.disconnect();
		return lista;
	}
	
	private static ArrayList<Cancion> parsingCanciones(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		JSONArray emisoras = (JSONArray) jsonParser.parse(json);
		Iterator i = emisoras.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Cancion emisora = new Cancion();
			emisora.setNumCancion(Integer.parseInt(innerObj.get("num_cancion").toString())); 
			emisora.setGenero_musical(innerObj.get("genero_musical").toString());
			emisora.setNombre_artista(innerObj.get("nombre_artista").toString());
			emisora.setNombre_cancion(innerObj.get("nombre_cancion").toString());
			emisora.setUrl(innerObj.get("url").toString());
			lista.add(emisora);
		}
		return lista;
	}
	
	public static int postJSONCancion(Cancion cancion) throws IOException {
		url = new URL(sitio+"cancion/guardar");
		
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
				+ "\"num_cancion\":\""+ cancion.getNumCancion()
				+"\",\"genero_musical\": \""+cancion.getGenero_musical()
				+"\",\"nombre_artista\": \""+cancion.getNombre_artista()
				+"\",\"nombre_cancion\":\""+cancion.getNombre_cancion()
				+"\",\"url\":\""+cancion.getUrl()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
}
