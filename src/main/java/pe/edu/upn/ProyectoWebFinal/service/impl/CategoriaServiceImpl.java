package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Categoria;
import pe.edu.upn.ProyectoWebFinal.model.repository.CategoriaRepository;
import pe.edu.upn.ProyectoWebFinal.service.CategoriaService;

@Service
public class CategoriaServiceImpl  implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRespository;

	@Transactional(readOnly=true)
	@Override
	public List<Categoria> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoriaRespository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Categoria> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRespository.findById(id);
	}

	@Transactional
	@Override
	public Categoria save(Categoria entity) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRespository.save(entity);
	}

	@Transactional
	@Override
	public Categoria update(Categoria entity) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRespository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		categoriaRespository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		categoriaRespository.deleteAll();
		
	}
	
	
}
