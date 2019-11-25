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


@Entity
@Table(name="descuentos")
public class Descuento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="precio")
	private Float precio;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tipodescuento_codigo")
	private TipoDescuento tipodescuento;
	
	@OneToMany(mappedBy="descuento")
	private List<Venta>listaVenta;

	public Descuento() {
		listaVenta=new ArrayList<>();
	}
	public void addDescuento(Venta venta) {
		venta.setDescuento(this);
		listaVenta.add(venta);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public TipoDescuento getTipodescuento() {
		return tipodescuento;
	}

	public void setTipodescuento(TipoDescuento tipodescuento) {
		this.tipodescuento = tipodescuento;
	}
	public List<Venta> getListaVenta() {
		return listaVenta;
	}
	public void setListaVenta(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}
	
	
	
}
