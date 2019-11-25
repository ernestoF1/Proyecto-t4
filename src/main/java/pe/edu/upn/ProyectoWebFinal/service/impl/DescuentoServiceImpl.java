package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Descuento;
import pe.edu.upn.ProyectoWebFinal.model.repository.DescuentoRepository;
import pe.edu.upn.ProyectoWebFinal.service.DescuentoService;

@Service
public class DescuentoServiceImpl implements DescuentoService{

	@Autowired
	private DescuentoRepository descuentoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Descuento> findAll() throws Exception {
		// TODO Auto-generated method stub
		return descuentoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Descuento> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return descuentoRepository.findById(id);
	}

	@Transactional
	@Override
	public Descuento save(Descuento entity) throws Exception {
		// TODO Auto-generated method stub
		return descuentoRepository.save(entity);
	}

	@Transactional
	@Override
	public Descuento update(Descuento entity) throws Exception {
		// TODO Auto-generated method stub
		return descuentoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		descuentoRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		descuentoRepository.deleteAll();
		
	}

	
}
