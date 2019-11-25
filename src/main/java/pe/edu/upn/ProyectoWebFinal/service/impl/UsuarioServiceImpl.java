package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Usuario;
import pe.edu.upn.ProyectoWebFinal.model.repository.UsuarioRepository;
import pe.edu.upn.ProyectoWebFinal.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Transactional(readOnly=true)
	@Override
	public List<Usuario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Usuario> findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Transactional
	@Override
	public Usuario save(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.save(entity);
	}

	@Transactional
	@Override
	public Usuario update(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		usuarioRepository.deleteAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Usuario> findByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

}
