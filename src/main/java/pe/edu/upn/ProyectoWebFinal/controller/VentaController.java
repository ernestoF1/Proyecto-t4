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
import pe.edu.upn.ProyectoWebFinal.model.entity.Descuento;
import pe.edu.upn.ProyectoWebFinal.model.entity.MetodoEntrega;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoDescuento;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoPago;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Venta;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;
import pe.edu.upn.ProyectoWebFinal.service.DescuentoService;
import pe.edu.upn.ProyectoWebFinal.service.MetodoEntregaService;
import pe.edu.upn.ProyectoWebFinal.service.TipoDescuentoService;
import pe.edu.upn.ProyectoWebFinal.service.TipoPagoService;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;
import pe.edu.upn.ProyectoWebFinal.service.VentaService;

@Controller
@RequestMapping("/venta")
@SessionAttributes("venta")
public class VentaController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CarritoDeComprasService carritoDeComprasService;
	
	@Autowired
	private TipoPagoService tipoPagoService;
	
	@Autowired
	private MetodoEntregaService metodoEntregaService;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private DescuentoService descuentoService;
	
	@Autowired
	private TipoDescuentoService tipoDescuentoService;
	
	@GetMapping("/info/{id}")
	public String listaCompras(@PathVariable("id") long id, Model model) {
		
		
		
		try {
			
			Optional<Usuario>optional=usuarioService.findById(id);
			if(optional.isPresent()) {
				List<Venta>venta=ventaService.findAll();
				List<Descuento>descuento=descuentoService.findAll();
				List<TipoDescuento>tipos=tipoDescuentoService.findAll();
				for(Venta d:venta)
				if(d.getUsuario().equals(optional.get()))
					
		
					model.addAttribute("venta", venta);
				model.addAttribute("tipos",tipos);
				model.addAttribute("usuario", optional.get());
					
				
				
			} else {
				return "redirect:/";
			}
		} catch (Exception e) {

		}	
		
		return "/venta/info";
	}
	
	
	@GetMapping("/nuevo/{id}")
	public String nuevo(@PathVariable("id")long id,Model model) {
		
		
		float T=0;
		
		Venta venta=new Venta();
		String estado="no entregado";
		try {
			Optional<Usuario>usuario=usuarioService.findById(id);
		
			if(usuario.isPresent()) {
				
				List<CarritoDeCompras>carrito=carritoDeComprasService.findAll();
				List<TipoPago>tipoPagos=tipoPagoService.findAll();
				List<MetodoEntrega>metodoEntregas=metodoEntregaService.findAll();
				List<Descuento>descuentos=descuentoService.findAll();
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
						model.addAttribute("descuentos", descuentos);
						 venta.setTotal(T);
						
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/venta/nuevo";
	}
	
	@PostMapping("/save")
	public String saveVenta(@ModelAttribute("venta") Venta venta, 
			Model model, SessionStatus status) {
	
		try {
			venta.setDescuento_nombre(venta.getDescuento().getTipodescuento().getNombre());
		
			venta.calcularTotalFinal(venta.getTotal());
			ventaService.save(venta);
			
			status.setComplete();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/";
	}
	
}
