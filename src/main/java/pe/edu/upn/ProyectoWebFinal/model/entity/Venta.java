package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@Column(name="nombre")
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_envio",length=30)
	private Date fecha_envio;
	
	@Column(name="fecha_emision",length=30)
	 private String fechaYHora;
	
	@Column(name="total")
	private Float total;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="descuento")
	private String descuento_nombre;
	
	@ManyToOne
    @JoinColumn
    private Usuario usuario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoPago_id")
	private TipoPago tipoPago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metodoEntrega_id")
	private MetodoEntrega metodoentrega;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="descuento_id")
	private Descuento descuento;

	public Venta() {
		 this.fechaYHora = Utiles.obtenerFechaYHoraActual();
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


	
	
	

	public Date getFecha_envio() {
		return fecha_envio;
	}


	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}


	
	
	public String getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public Float getTotal() {
		return total;
	}


	public void setTotal(Float total) {
		this.total = total;
		
	}





	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	

	public String getDescuento_nombre() {
		return descuento_nombre;
	}
	public void setDescuento_nombre(String descuento_nombre) {
		this.descuento_nombre = descuento_nombre;
	}
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public TipoPago getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}


	public MetodoEntrega getMetodoentrega() {
		return metodoentrega;
	}


	public void setMetodoentrega(MetodoEntrega metodoentrega) {
		this.metodoentrega = metodoentrega;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	
	public void calcularTotalFinal(Float T) {
		float tota=0;
		tota=T-(T*this.descuento.getPrecio());
		/* this.total=T-(this.metodoentrega.getComision()*T); */
		this.total=tota-(tota*this.metodoentrega.getComision());
	}
	
	
	
	
	
	 
	 
	
}
