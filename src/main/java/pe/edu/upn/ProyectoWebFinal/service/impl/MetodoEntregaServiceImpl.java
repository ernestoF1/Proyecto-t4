package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.MetodoEntrega;
import pe.edu.upn.ProyectoWebFinal.model.repository.MetodoEntregaRepository;
import pe.edu.upn.ProyectoWebFinal.service.MetodoEntregaService;

@Service
public class MetodoEntregaServiceImpl implements MetodoEntregaService{

	@Autowired
	private MetodoEntregaRepository metodoEntregaRepository;
	@Transactional(readOnly=true)
	
	@Override
	public List<MetodoEntrega> findAll() throws Exception {
		// TODO Auto-generated method stub
		return metodoEntregaRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<MetodoEntrega> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return metodoEntregaRepository.findById(id);
	}

	@Transactional
	@Override
	public MetodoEntrega save(MetodoEntrega entity) throws Exception {
		// TODO Auto-generated method stub
		return metodoEntregaRepository.save(entity);
	}

	@Transactional
	@Override
	public MetodoEntrega update(MetodoEntrega entity) throws Exception {
		// TODO Auto-generated method stub
		return metodoEntregaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		metodoEntregaRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		metodoEntregaRepository.deleteAll();
	}

}
