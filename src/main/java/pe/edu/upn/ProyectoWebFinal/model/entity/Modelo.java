package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="modelos")
public class Modelo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre",length=60)
	private String nombre;
	
	@Column(name="imagen")
	private String imagen;
	
	@OneToMany(mappedBy="modelo",fetch=FetchType.LAZY)
	private List<Producto>lista_productos;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	public Modelo() {
		lista_productos=new ArrayList<>();
	}
	public void addProducto(Producto producto) {
		producto.setModelo(this);
		this.lista_productos.add(producto);
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
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<Producto> getLista_productos() {
		return lista_productos;
	}
	public void setLista_productos(List<Producto> lista_productos) {
		this.lista_productos = lista_productos;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
