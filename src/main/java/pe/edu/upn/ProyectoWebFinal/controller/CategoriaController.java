package pe.edu.upn.ProyectoWebFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.ProyectoWebFinal.model.entity.Categoria;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.service.CategoriaService;
import pe.edu.upn.ProyectoWebFinal.service.ModeloService;


@Controller
@RequestMapping("/categoria")
@SessionAttributes({"categoria","modelo"})
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ModeloService modeloService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Categoria>categorias=categoriaService.findAll();
			model.addAttribute("categorias", categorias);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable ("id") int id,Model model) {
		try {
			Optional<Categoria>optional=categoriaService.findById(id);
			if(optional.isPresent()) {
				
				
				model.addAttribute("categoria", optional.get());
				
			}
			else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("categoria")Categoria categoria, Model model,SessionStatus status) {
	     try {
			categoriaService.save(categoria);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	     return "redirect:/categoria";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		
		return "/categoria/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				categoriaService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Categoria> categorias = categoriaService.findAll();
				model.addAttribute("categorias", categorias);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/categoria/inicio";
		}
		return "redirect:/categoria";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				List<Modelo>modelos=modeloService.findAll();
				model.addAttribute("categoria", categoria.get());
			    model.addAttribute("modelos", modelos);
			} else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {

		}	
		
		return "/categoria/info";
	}
	
	@GetMapping("/{id}/nuevomodelo")
	public String nuevoProducto(@PathVariable("id") int id, Model model) {
		Modelo modelo= new Modelo();
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				modelo.setCategoria(categoria.get());
				model.addAttribute("modelo", modelo);
			} else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/nuevomodelo";
	}
	
	@PostMapping("/savemodelo")
	public String saveModelo(@ModelAttribute("modelo") Modelo modelo, 
			Model model, SessionStatus status) {
		try {
			modeloService.save(modelo);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/categoria/info/" + modelo.getCategoria().getId();
	}
	@GetMapping("/buscar")
	public String buscarCategoria(@RequestParam(defaultValue="")int id,Model model) {
		try {
			Optional<Categoria>categorias=categoriaService.findById(id);
			model.addAttribute("categorias",categorias.get());
		} catch (Exception e) {
			return "redirect:/";
		}
		
			return "/categoria/buscar";
		
		
	}
	
}
