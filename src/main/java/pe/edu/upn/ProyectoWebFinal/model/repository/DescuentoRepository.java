package pe.edu.upn.ProyectoWebFinal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.ProyectoWebFinal.model.entity.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{

}
