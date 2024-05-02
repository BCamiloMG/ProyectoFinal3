package co.edu.unbosque.BJCyberNeticForrestM.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.BJCyberNeticForrestM.dao.CancionDAO;
import co.edu.unbosque.BJCyberNeticForrestM.model.Cancion;

@RestController //esta es una clase REST
@RequestMapping("cancion")
public class CancionAPI {
	
	@Autowired //inyecta la dependencia de todos los métodos del JPA para usuarioDAO
	private CancionDAO cancionDAO;
	
	@PostMapping("/guardar")//Request convierte en un objeto Java desde un JSon
	public void guardar(@RequestBody Cancion cancion) {
		cancionDAO.save(cancion);
	}
	
	@GetMapping("/listar")
	public List<Cancion> listar(){
		return cancionDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		cancionDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Cancion cancion) {
		cancionDAO.save(cancion);
	}

}
