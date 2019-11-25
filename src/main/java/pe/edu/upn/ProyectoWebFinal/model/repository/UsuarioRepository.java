package pe.edu.upn.ProyectoWebFinal.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsername(String username);
}
