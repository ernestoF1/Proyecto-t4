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
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;


@Controller
@RequestMapping("/modelo")
@SessionAttributes({"modelo","producto"})
public class ModeloController {

	@Autowired
	private ModeloService modeloService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping

	public String inicio(Model model) {
		try {
			List<Modelo> modelos = modeloService.findAll();
			model.addAttribute("modelos", modelos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/modelo/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Modelo> optional = modeloService.findById(id);
			if (optional.isPresent()) {
				
				
				model.addAttribute("modelo", optional.get());
			} else {
				return "redirect:/modelo";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/modelo/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("modelo") Modelo modelo, 
			Model model, SessionStatus status) {
		try {
			modeloService.save(modelo);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/modelo";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Modelo modelo = new Modelo();
		model.addAttribute("modelo", modelo);
		
		try {
			List<Categoria>categorias=categoriaService.findAll();
			model.addAttribute("categorias", categorias);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/modelo/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Modelo> modelo = modeloService.findById(id);
			if(modelo.isPresent()) {
				modeloService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Modelo> modelos = modeloService.findAll();
				model.addAttribute("modelos", modelos);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/modelo/inicio";
		}
		return "redirect:/modelo";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Modelo> modelo = modeloService.findById(id);
			if(modelo.isPresent()) {
				model.addAttribute("modelo", modelo.get());
			} else {
				return "redirect:/modelo";
			}
		} catch (Exception e) {

		}	
		
		return "/modelo/info";
	}
	
	@GetMapping("/{id}/nuevoproducto")
	public String nuevoProducto(@PathVariable("id") int id, Model model) {
		Producto producto = new Producto();
		try {
			Optional<Modelo> modelo = modeloService.findById(id);
			if(modelo.isPresent()) {
				producto.setModelo(modelo.get());
				model.addAttribute("producto", producto);
			} else {
				return "redirect:/modelo";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/modelo/nuevoproducto";
	}
	
	

	/*
	@GetMapping("{id}/agregarproducto")
	public String agregarproducto(@PathVariable("id")int id,Model model) {
	Modelo modelo=new Modelo();
		try {
			
			Optional<Modelo>modelo= modeloService.findById(id);
			if(modelo.isPresent()) {
				List<Producto>productos=productoService.findAll();
				model.addAttribute("modelo",optional.get());
				
				modelo.
				model.addAttribute("productos", productos);
				
				
				
			} else {
				return "redirect:/modelo";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/modelo/agregarproducto";
	}
	
	*/
	
	@PostMapping("/saveproducto")
	public String saveProducto(@ModelAttribute("producto") Producto producto, 
			Model model, SessionStatus status) {
		try {
			productoService.save(producto);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/modelo/info/" + producto.getModelo().getId();
	}
	
	@GetMapping("/buscar")
	public String buscarModelo(@RequestParam(defaultValue="")int id,Model model) {
		try {
			Optional<Modelo>modelos=modeloService.findById(id);
			model.addAttribute("modelos",modelos.get());
		} catch (Exception e) {
			return "redirect:/";
		}
		
			return "/modelo/buscar";
		
		
	}
}
