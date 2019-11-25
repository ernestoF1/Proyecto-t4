package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Producto;
import pe.edu.upn.ProyectoWebFinal.model.repository.ProductoRepository;
import pe.edu.upn.ProyectoWebFinal.service.ProductoService;


@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;

	@Transactional(readOnly=true)
	@Override
	public List<Producto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Producto> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Transactional
	@Override
	public Producto save(Producto entity) throws Exception {
		// TODO Auto-generated method stub
		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public Producto update(Producto entity) throws Exception {
		// TODO Auto-generated method stub
		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		productoRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
	productoRepository.deleteAll();
		
	}

	

	
/*
	@Transactional(readOnly=true)
	@Override
	public Optional<Producto> findByName(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return productoRepository.findByName(nombre);
	}
	
	*/
	
	
}
