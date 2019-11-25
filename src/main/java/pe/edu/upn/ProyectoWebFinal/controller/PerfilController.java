package pe.edu.upn.ProyectoWebFinal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	@GetMapping
	public String Index(Model model) {
		
		// Para obtener el username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.addAttribute("currentPrincipalName", currentPrincipalName);
		
		return "/perfil/index";
	}
}
