package pe.edu.upn.ProyectoWebFinal.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upn.ProyectoWebFinal.model.entity.TipoUsuario;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.repository.AuthorityRepository;
import pe.edu.upn.ProyectoWebFinal.model.repository.TipoUsuarioRepository;
import pe.edu.upn.ProyectoWebFinal.model.repository.UsuarioRepository;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Override
	public void run(String... args) throws Exception {
		/*
		this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		this.tipoUsuarioRepository.deleteAll();
		
		TipoUsuario tipo1=new TipoUsuario();
		tipo1.setNombre("Trabajador Empleado");
		
		TipoUsuario tipo2=new TipoUsuario();
		tipo2.setNombre("Trabajador Manager");
		
		TipoUsuario tipo3=new TipoUsuario();
		tipo3.setNombre("Cliente");
		
		List<TipoUsuario>tipos=Arrays.asList(tipo1,tipo2,tipo3);
        this.tipoUsuarioRepository.saveAll(tipos);
        
	
        Usuario customer = new Usuario();
		customer.setUsername("maria");
		customer.setPassword(passwordEncoder.encode("maria"));
		customer.setApellidos("Del Pilar");
		customer.setNombres("Maria Fernanda");
		customer.setFechaNacimiento("2019/05/10");
		customer.setLugarNacimiento("Lima");
		customer.setSexo('F');
		customer.setTipousuario(tipo3);
		customer.setEnable(true);
		
		Usuario manager = new Usuario();
		manager.setUsername("ernesto");
		manager.setPassword(passwordEncoder.encode("ernesto"));
		manager.setApellidos("Tarazona Espinoza");
		manager.setNombres("Ernesto Fidel Alejandro");
		manager.setFechaNacimiento("1998/05/25");
		manager.setLugarNacimiento("Lima");
		manager.setSexo('M');
	    manager.setTipousuario(tipo2);
		manager.setEnable(true);
		
		Usuario employee = new Usuario();
		employee.setUsername("pedro");
		employee.setPassword(passwordEncoder.encode("pedro"));
		employee.setApellidos("Ramos");
		employee.setNombres("Pedro Raul");
		employee.setFechaNacimiento("1985/07/10");
		employee.setLugarNacimiento("Lima");
		employee.setSexo('M');
		employee.setTipousuario(tipo1);
		employee.setEnable(true);
		
		customer.addAuthority("ROLE_CUSTOMER");
		manager.addAuthority("ROLE_MANAGER");
		employee.addAuthority("ROLE_EMPLOYEE");
        
        List<Usuario> usuarios = Arrays.asList(customer, manager,employee);        
        this.usuarioRepository.saveAll(usuarios);
        */
	}
}