package pe.edu.upn.ProyectoWebFinal.rescontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upn.ProyectoWebFinal.model.entity.Categoria;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.service.CategoriaService;
import pe.edu.upn.ProyectoWebFinal.service.ModeloService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaRestController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ModeloService modeloService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria> >fetchCategorias(){
		try {
			List<Categoria>categorias=categoriaService.findAll();
			return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria>saveCategoria(@RequestBody Categoria categoria){
		try {
			Categoria newCategoria=categoriaService.save(categoria);
		    return new ResponseEntity<Categoria>(newCategoria,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(path="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria>updateCategoria(@PathVariable("id")Integer id,
			@RequestBody Categoria categoria){
		try {
			if(id.equals(categoria.getId())) {
				Optional<Categoria> optional = categoriaService.findById(id);
				if(optional.isPresent()) {
					Categoria updateCategoria = categoriaService.update(categoria);
					return new ResponseEntity<Categoria>(updateCategoria, 
							HttpStatus.OK);
				} else {
					return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
				}				
			} else {
				return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable("id") Integer id) {
		try {			
			Optional<Categoria> optional = categoriaService.findById(id);
			if(optional.isPresent()) {
				categoriaService.deleteById(id);
				return new ResponseEntity<Categoria>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/{id}/modelos")
	public ResponseEntity<List<Modelo>> fetchModelo(
			@PathVariable("id") Integer id){
		try {
			Optional<Categoria> optional = categoriaService.findById(id);
			if(optional.isPresent()) {
				return new ResponseEntity<List<Modelo>>(
						optional.get().getLista_modelos(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Modelo>>(
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Modelo>>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping( path = "/{id}/modelos",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< Modelo> saveModelo(
			@PathVariable("id") Integer id,
			@RequestBody Modelo modelo) {
		try {
			Optional<Categoria> optional = categoriaService.findById(id);
			if(optional.isPresent()) {	
				modelo.setCategoria(optional.get());
				Modelo nuevoModelo = modeloService.save(modelo);
				return new ResponseEntity<Modelo>(nuevoModelo, 
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Modelo>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity< Modelo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
