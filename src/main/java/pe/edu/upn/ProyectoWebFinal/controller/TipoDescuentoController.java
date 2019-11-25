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

import pe.edu.upn.ProyectoWebFinal.model.entity.Categoria;
import pe.edu.upn.ProyectoWebFinal.model.entity.Descuento;
import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;
import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.TipoDescuento;
import pe.edu.upn.ProyectoWebFinal.service.DescuentoService;
import pe.edu.upn.ProyectoWebFinal.service.TipoDescuentoService;

@Controller
@RequestMapping("/tipodescuento")
@SessionAttributes({"tipodescuento","descuento"})
public class TipoDescuentoController {

	@Autowired
	private TipoDescuentoService tipoDescuentoService;
	
	@Autowired
	private DescuentoService descuentoService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<TipoDescuento> tiposdescuentos = tipoDescuentoService.findAll();
			model.addAttribute("tiposdescuentos", tiposdescuentos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/tipodescuento/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<TipoDescuento> optional = tipoDescuentoService.findById(id);
			if (optional.isPresent()) {
				
				
				model.addAttribute("tipodescuento", optional.get());
			} else {
				return "redirect:/tipodescuento";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/tipodescuento/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("tipodescuento") TipoDescuento tipodescuento, 
			Model model, SessionStatus status) {
		try {
			tipoDescuentoService.save(tipodescuento);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/tipodescuento";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		TipoDescuento tipodescuento = new TipoDescuento();
		model.addAttribute("tipodescuento", tipodescuento);
		
		return "/tipodescuento/nuevo";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<TipoDescuento> tipodescuento = tipoDescuentoService.findById(id);
			if(tipodescuento.isPresent()) {
				tipoDescuentoService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<TipoDescuento> tipodescuento= tipoDescuentoService.findAll();
				model.addAttribute("tipodescuento", tipodescuento);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/tipodescuento/inicio";
		}
		return "redirect:/tipodescuento";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<TipoDescuento> tipodescuento = tipoDescuentoService.findById(id);
			if(tipodescuento.isPresent()) {
				model.addAttribute("tipodescuento", tipodescuento.get());
			} else {
				return "redirect:/tipodescuento";
			}
		} catch (Exception e) {

		}	
		
		return "/tipodescuento/info";
	}
	@GetMapping("/{id}/nuevodescuento")
	public String nuevoDescuento(@PathVariable("id") int id, Model model) {
		Descuento descuento=new Descuento();
		try {
			Optional<TipoDescuento> tipodescuento= tipoDescuentoService.findById(id);
			if(tipodescuento.isPresent()) {
				descuento.setTipodescuento(tipodescuento.get());
				
				model.addAttribute("descuento", descuento);
			} else {
				return "redirect:/tipodescuento";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/tipodescuento/nuevodescuento";
	}
	@PostMapping("/savedescuento")
	public String saveDescuento(@ModelAttribute("descuento") Descuento descuento, 
			Model model, SessionStatus status) {
		try {
			descuentoService.save(descuento);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/tipodescuento/info/" +descuento.getTipodescuento().getId();
	}
}
