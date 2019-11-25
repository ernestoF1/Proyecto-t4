package pe.edu.upn.ProyectoWebFinal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.ProyectoWebFinal.model.entity.Modelo;


@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
