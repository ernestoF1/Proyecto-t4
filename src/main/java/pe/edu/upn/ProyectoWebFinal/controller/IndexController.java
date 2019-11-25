package pe.edu.upn.ProyectoWebFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;


@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public String index(Model model) {
		
		try {
			
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "index";	// Archivo html que se devuelve
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			
			Optional<Producto> producto = productoService.findById(id);
			if(producto.isPresent()) {
				model.addAttribute("producto", producto.get());
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/producto/detalle";
	}
	@GetMapping("login")
	public String login() {
		return "login";
	}
	@GetMapping("access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
