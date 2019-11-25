package pe.edu.upn.ProyectoWebFinal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
/*
	Optional<Producto> findByName(String nombre);
	
*/
}
