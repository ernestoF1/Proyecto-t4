package pe.edu.upn.ProyectoWebFinal.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import pe.edu.upn.ProyectoWebFinal.model.entity.Venta;
import pe.edu.upn.ProyectoWebFinal.model.entity.MetodoEntrega;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoPago;
import pe.edu.upn.ProyectoWebFinal.service.VentaService;
import pe.edu.upn.ProyectoWebFinal.service.MetodoEntregaService;
import pe.edu.upn.ProyectoWebFinal.service.TipoPagoService;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoUsuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;
import pe.edu.upn.ProyectoWebFinal.service.TipoUsuarioService;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;



@Controller
@RequestMapping("/usuario")
@SessionAttributes({"usuario","venta"})
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	@Autowired
	private CarritoDeComprasService carritoDeComprasService;
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private TipoPagoService tipoPagoService;
	
	@Autowired
	private MetodoEntregaService metodoEntregaService;
	
	@Autowired
	private VentaService VentaService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Usuario>usuarios=usuarioService.findAll();
			
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/usuario/inicio";
	}
	
	@GetMapping("/nuevo")
	public String register(Model model) {
		Usuario usuario=new Usuario();
		model.addAttribute("usuario", usuario);
		
		try {
			
			List<TipoUsuario>tipoUsuarios=tipoUsuarioService.findAll();
			model.addAttribute("tipoUsuarios", tipoUsuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/usuario/nuevo";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, Model model, SessionStatus status) {
		try {
			Optional<Usuario>optional=usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerNuevo", "Error en el username "+usuario.getUsername()+" </strong> ya existe");
				return "/usuario/nuevo";
			}
			else {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				
				
				
				if(usuario.getTipousuario().getNombre().equals("Cliente")) {
					usuario.addAuthority("ROLE_CUSTOMER");
					usuarioService.save(usuario);
					status.setComplete();
				}
				else {
				   if(usuario.getTipousuario().getNombre().equals("Trabajador Manager")){
					usuario.addAuthority("ROLE_MANAGER");
					usuarioService.save(usuario);
					status.setComplete();
				   } else{
					 usuario.addAuthority("ROLE_EMPLOYEE");
					 usuarioService.save(usuario);
					 status.setComplete();
				   }
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/login";
		
	}
	@GetMapping("/agregarproducto/{id}")
public String agregarCarrito(@PathVariable("id") int id,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.addAttribute("currentPrincipalName", currentPrincipalName);
		
		CarritoDeCompras carrito = new CarritoDeCompras();
		try {
			Optional<Usuario> usuario = usuarioService.findByUsername(currentPrincipalName);
			Optional<Producto> producto=productoService.findById(id);
			if(usuario.isPresent()) {
				List<Producto>productos=productoService.findAll();
				
				float cantidad=1;
				carrito.setUsuario(usuario.get());
				carrito.setProducto(producto.get());
				carrito.setCantidad(cantidad);
				model.addAttribute("carrito", carrito);
				model.addAttribute("productos", productos);
				carritoDeComprasService.save(carrito);
				
				Producto produ=producto.get();
				produ.restarExistencia(Math.round(carrito.getCantidad()));;
			    productoService.save(produ);
				/*
				  
			 
				 
		
				
				
				for(Producto p:productos)
					if(p.getId().equals(carrito.getProducto().getId()))
						p.restarExistencia(carrito.getProducto().getStock());
				productoService.save(p);
				
				
				for(Producto p:productos) {
					if(p.getId().equals(carrito.getProducto().getId())) 
						
						 p.restarExistencia(carrito);
					p.setStock(carrito.getCantidad());
				productoService.save(p);
				}
				
				*/
				
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/";
	}
	/*
	@GetMapping("/agregarproductodetalle/{id}")
	public String agregarCarritoDetalle(@PathVariable("id") int id,Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			model.addAttribute("currentPrincipalName", currentPrincipalName);
			
			float cantidad=1;
			CarritoDeCompras carrito = new CarritoDeCompras();
			try {
				Optional<Usuario> usuario = usuarioService.findByUsername(currentPrincipalName);
				Optional<Producto> producto=productoService.findById(id);
				if(usuario.isPresent()) {
					List<Producto>productos=productoService.findAll();
					
					
					carrito.setUsuario(usuario.get());
					carrito.setProducto(producto.get());
					carrito.setCantidad(cantidad);
					
					model.addAttribute("carrito", carrito);
					model.addAttribute("productos", productos);
					carritoDeComprasService.save(carrito);
					
				} else {
					return "redirect:/";
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return "redirect:/";
		}
	
	*/
	
	/*@GetMapping("/ventanuevo/{id}")
	public String ventaRegistrar(@PathVariable("id")long id,Model model) {
		
		
		float T=0;
		
		Venta venta=new Venta();
		String estado="no entregado";
		try {
			Optional<Usuario>usuario=usuarioService.findById(id);
		
			if(usuario.isPresent()) {
				
				List<CarritoDeCompras>carrito=carritoDeComprasService.findAll();
				List<TipoPago>tipoPagos=tipoPagoService.findAll();
				List<MetodoEntrega>metodoEntregas=metodoEntregaService.findAll();
				for (CarritoDeCompras v:carrito) 
					if(v.getUsuario().equals(usuario.get())) 
						T+=v.getTotal();
						venta.setUsuario(usuario.get());
						venta.setEstado(estado);
						venta.setNombre(usuario.get().getNombres());
						
						
				        model.addAttribute("usuario", usuario.get());
						model.addAttribute("venta", venta);
						model.addAttribute("tipoPagos", tipoPagos);
						model.addAttribute("metodoEntregas", metodoEntregas);
						 venta.setTotal(T);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/usuario/nuevocarrito";
	}

	@PostMapping("/saveventa")
	public String saveVenta(@ModelAttribute("venta") Venta venta, 
			Model model, SessionStatus status) {
	
		try {
		
			venta.calcularTotalFinal(venta.getTotal());
			VentaService.save(venta);
			
			status.setComplete();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/ventamostrar/{id}")
	public String mostrarMiCompra(@PathVariable("id") long id, Model model) {
		
		
		
		try {
			
			Optional<Usuario>optional=usuarioService.findById(id);
			if(optional.isPresent()) {
				List<Venta>venta=VentaService.findAll();
				
				for(Venta d:venta)
				if(d.getUsuario().equals(optional.get()))
					
		
					model.addAttribute("venta", venta);
				model.addAttribute("usuario", optional.get());
					
				
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/usuario/mostrarcompra";
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
	*/
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")long id, Model model) {
		try {
			Optional<Usuario>usuario=usuarioService.findById(id);
			if(usuario.isPresent()) {
				usuarioService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			 try {
				List<Usuario>usuarios=usuarioService.findAll();
				model.addAttribute("usuarios",usuarios);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/lista";
		}
		return "redirect:/usuario";
	}
	
	@GetMapping("/info/{username}")
	public String informacionUsuario(@PathVariable("username")String username, Model model) {
		try {
			Optional<Usuario>usuario=usuarioService.findByUsername(username);
			if(usuario.isPresent()) {
				model.addAttribute("usuario", usuario.get());
				
			}
		} catch (Exception e) {
			
		}
		return "/usuario/info";
	}
	
}
