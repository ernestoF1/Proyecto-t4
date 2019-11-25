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
@Table(name="tipodescuentos")
public class TipoDescuento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="nombre",length = 40)
	private String nombre;

	@OneToMany (mappedBy = "tipodescuento", fetch = FetchType.LAZY)
	private List<Descuento> descuentos;
	
	public TipoDescuento() {
		descuentos=new ArrayList<>();
	}
	public void addDescuento(Descuento descuento) {
		descuento.setTipodescuento(this);
		this.descuentos.add(descuento);
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
	public List<Descuento> getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}
	
	
}
