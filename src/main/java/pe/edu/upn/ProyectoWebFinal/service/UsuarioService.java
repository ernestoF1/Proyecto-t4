package pe.edu.upn.ProyectoWebFinal.service;

import java.util.Optional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long>{

	Optional<Usuario> findByUsername(String username) throws Exception;
}
