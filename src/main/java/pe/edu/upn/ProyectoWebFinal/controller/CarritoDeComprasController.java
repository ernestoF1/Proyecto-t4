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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;

/*
@Controller
@RequestMapping("/venta")
@SessionAttributes("venta")
*/
@Controller
@RequestMapping("/carrito")
@SessionAttributes("carrito")
public class CarritoDeComprasController {

	@Autowired
	private CarritoDeComprasService carritoDeComprasService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")int id, Model model) {
		try {
			Optional<CarritoDeCompras>carrito=carritoDeComprasService.findById(id);
			if(carrito.isPresent()) {
				carritoDeComprasService.deleteById(id);
			}
		} catch (Exception e) {
			 model.addAttribute("dangerDel","ERROR");
			 try {
				List<CarritoDeCompras>carritoDeCompras=carritoDeComprasService.findAll();
				model.addAttribute("carritoDeCompras", carritoDeCompras);
				/*model.addAttribute("ventas", ventas);*/
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/usuario";
		}
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable ("id") int id,Model model) {
		try {
			Optional<CarritoDeCompras>carrito=carritoDeComprasService.findById(id);
			if(carrito.isPresent()) {
				
				model.addAttribute("carrito", carrito.get());
			}
			else {
				return "redirect:/";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/carrito/edit";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("carrito")CarritoDeCompras carrito, Model model,SessionStatus status) {
	     try {
	    	 
	    	 
	    	 carritoDeComprasService.save(carrito);
			
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	     return "redirect:/carrito/info/"+carrito.getUsuario().getId();
	}
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") long id, Model model) {
		float total=0;
		
		try {
			
			Optional<Usuario>optional=usuarioService.findById(id);
			if(optional.isPresent()) {
				List<CarritoDeCompras>listacompra=carritoDeComprasService.findAll();
				
				for (CarritoDeCompras v:listacompra)
					if(v.getUsuario().equals(optional.get()))
					total+=v.getTotal();
					
				model.addAttribute("listacompra", listacompra);
				model.addAttribute("usuario", optional.get());
				model.addAttribute("total", total);
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/carrito/info";
	}
}
