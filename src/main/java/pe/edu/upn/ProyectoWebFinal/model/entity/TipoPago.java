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

@Entity
@Table(name="tipopagos")
public class TipoPago {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "nombre",length = 25,nullable = false)
	private String nombre;

	@OneToMany(mappedBy="tipoPago",fetch=FetchType.LAZY)
	private List<Venta>listadetalle;
	
	public TipoPago() {
		listadetalle=new ArrayList<>();
	}
	
	
	
	public void addDetalle(Venta detalle) {
		detalle.setTipoPago(this);
		this.listadetalle.add(detalle);
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



	public List<Venta> getListadetalle() {
		return listadetalle;
	}



	public void setListadetalle(List<Venta> listadetalle) {
		this.listadetalle = listadetalle;
	}
	
	
	
	
	
}
