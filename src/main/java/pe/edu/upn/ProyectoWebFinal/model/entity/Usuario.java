package pe.edu.upn.ProyectoWebFinal.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "users")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username",length = 30, nullable = false)
    private String username;

    @Column(name = "password",length = 60, nullable = false)
    private String password;

    private boolean enable;
    
    @Column(name = "apellidos",length = 40, nullable = false)
    private String apellidos;
    
    @Column(name = "nombres",length = 40, nullable = false)
    private String nombres;
    
  
	@Column(name = "fecha_Nacimiento",length = 40)	
	private String fechaNacimiento;
    
    @Column(name = "lugar_Nacimiento", length = 50)
	private String lugarNacimiento;
    
    @Column(name = "sexo", length = 1)
	private char sexo;
    /*
    @Column(name = "cargo",length = 30, nullable = false)
    private String cargo;
    */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipousuario_id")
    private TipoUsuario tipousuario;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
    private List<Authority> authorities;
    
    @OneToMany(mappedBy="usuario")
	private List<CarritoDeCompras>listacarrito;
    
    
	@OneToMany(mappedBy="usuario")
	private List<Venta>listaVenta;
    
    public Usuario() {
		this.enable = true;
		this.authorities = new ArrayList<>();
		listacarrito=new ArrayList<>();
		listaVenta=new ArrayList<>();
		
	}
    public Usuario( String username, String password ) {
		this.username = username;
		this.password = password;
		this.enable = true;
		this.authorities = new ArrayList<>();
	}
    
    public void addAuthority( String _authority ) {
		Authority authority = new Authority();
		authority.setAuthority( _authority );
		authority.setUsuario(this);
		this.authorities.add(authority);
	}
    public void addCarrito(CarritoDeCompras carrito) {
		carrito.setUsuario(this);
		this.listacarrito.add(carrito);
	}
    public void addDetalle(Venta venta) {
    	venta.setUsuario(this);
    	this.listaVenta.add(venta);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
	
	
	public TipoUsuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public List<CarritoDeCompras> getListacarrito() {
		return listacarrito;
	}
	public void setListacarrito(List<CarritoDeCompras> listacarrito) {
		this.listacarrito = listacarrito;
	}
	public List<Venta> getListaVenta() {
		return listaVenta;
	}
	public void setListaVenta(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}
	
	
    
    
}
