package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre",length=60)
	private String nombre;
	
	@JsonIgnoreProperties("categoria")
	@OneToMany(mappedBy="categoria",fetch=FetchType.LAZY)
	private List<Modelo>lista_modelos;
	
	public Categoria() {
		lista_modelos=new ArrayList<>();
	}
	public void addModelo(Modelo modelo) {
		modelo.setCategoria(this);
		this.lista_modelos.add(modelo);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Modelo> getLista_modelos() {
		return lista_modelos;
	}
	public void setLista_modelos(List<Modelo> lista_modelos) {
		this.lista_modelos = lista_modelos;
	}
	
	
}
