package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.TipoUsuario;
import pe.edu.upn.ProyectoWebFinal.model.repository.TipoUsuarioRepository;
import pe.edu.upn.ProyectoWebFinal.service.TipoUsuarioService;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<TipoUsuario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<TipoUsuario> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.findById(id);
	}

	@Transactional
	@Override
	public TipoUsuario save(TipoUsuario entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.save(entity);
	}

	@Transactional
	@Override
	public TipoUsuario update(TipoUsuario entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		tipoUsuarioRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		tipoUsuarioRepository.deleteAll();
	}

}
