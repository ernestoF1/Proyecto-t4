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
@Table(name="metodoentregas")
public class MetodoEntrega {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "nombre",length = 25,nullable = false)
	private String nombre;
	
	@Column(name="comision",length=23)
	private float comision;
	
	@OneToMany(mappedBy="metodoentrega",fetch = FetchType.LAZY)
	private List<Venta>listadetalle2;
	
	public MetodoEntrega() {
		listadetalle2=new ArrayList<>();
	}
	
	
	
	
	
	public void addDetalle2(Venta detalle) {
		detalle.setMetodoentrega(this);
		this.listadetalle2.add(detalle);
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
	
	
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}





	public List<Venta> getListadetalle2() {
		return listadetalle2;
	}





	public void setListadetalle2(List<Venta> listadetalle2) {
		this.listadetalle2 = listadetalle2;
	}
	
	
	
	
}
