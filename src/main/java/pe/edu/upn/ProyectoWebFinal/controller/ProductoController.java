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

import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.model.entity.MetodoEntrega;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoPago;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Venta;
import pe.edu.upn.ProyectoWebFinal.service.ModeloService;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;


@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ModeloService modeloService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/producto/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable ("id") int id,Model model) {
		try {
			Optional<Producto>optional=productoService.findById(id);
			if(optional.isPresent()) {
				List<Modelo> modelos=modeloService.findAll();
				
				model.addAttribute("producto", optional.get());
				model.addAttribute("modelos",modelos );
			}
			else {
				return "redirect:/producto";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("producto")Producto producto, Model model,SessionStatus status) {
	     try {
			productoService.save(producto);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	     return "redirect:/producto";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto producto=new Producto();
		model.addAttribute("producto", producto);
		
		try {
			List<Modelo>modelos=modeloService.findAll();
			model.addAttribute("modelos", modelos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/producto/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")int id, Model model) {
		try {
			Optional<Producto>producto=productoService.findById(id);
			if(producto.isPresent()) {
				productoService.deleteById(id);
			}
		} catch (Exception e) {
			 model.addAttribute("dangerDel","ERROR");
			 try {
				List<Producto>productos=productoService.findAll();
				model.addAttribute("productos", productos);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/producto/inicio";
		}
		return "redirect:/producto";
	}
	
	@GetMapping("/buscar")
	public String buscarProducto(@RequestParam(defaultValue="")int id,Model model) {
		try {
			Optional<Producto>productos=productoService.findById(id);
			model.addAttribute("productos",productos.get());
		} catch (Exception e) {
			return "redirect:/";
		}
		
			return "/producto/buscar";
		
		
	}
}
