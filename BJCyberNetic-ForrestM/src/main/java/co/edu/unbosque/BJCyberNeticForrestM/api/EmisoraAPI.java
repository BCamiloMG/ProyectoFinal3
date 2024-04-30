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

import co.edu.unbosque.BJCyberNeticForrestM.dao.EmisoraDAO;
import co.edu.unbosque.BJCyberNeticForrestM.model.Emisora;

@RestController //esta es una clase REST
@RequestMapping("emisora")
public class EmisoraAPI {
	
	@Autowired //inyecta la dependencia de todos los métodos del JPA para usuarioDAO
	private EmisoraDAO emisoraDAO;
	
	@PostMapping("/guardar")//Request convierte en un objeto Java desde un JSon
	public void guardar(@RequestBody Emisora emisora) {
		emisoraDAO.save(emisora);
	}
	
	@GetMapping("/listar")
	public List<Emisora> listar(){
		return emisoraDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		emisoraDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Emisora emisora) {
		emisoraDAO.save(emisora);
	}

}
