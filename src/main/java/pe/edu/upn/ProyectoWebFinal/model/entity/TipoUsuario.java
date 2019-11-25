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
@Table(name="tipousuarios")
public class TipoUsuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "nombre",length = 25,nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy="tipousuario",fetch = FetchType.LAZY)
	private List<Usuario>listausuario;

	public TipoUsuario() {
		listausuario=new ArrayList<>();
	}
	public void addUsuario(Usuario usuario) {
		usuario.setTipousuario(this);
		this.listausuario.add(usuario);
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

	public List<Usuario> getListausuario() {
		return listausuario;
	}

	public void setListausuario(List<Usuario> listausuario) {
		this.listausuario = listausuario;
	}
	
	
	
}
