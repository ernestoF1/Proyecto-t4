package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.Venta;
import pe.edu.upn.ProyectoWebFinal.model.repository.VentaRepository;
import pe.edu.upn.ProyectoWebFinal.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{

	@Autowired
	private VentaRepository VentaRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Venta> findAll() throws Exception {
		// TODO Auto-generated method stub
		return VentaRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Venta> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return VentaRepository.findById(id);
	}

	@Transactional
	@Override
	public Venta save(Venta entity) throws Exception {
		// TODO Auto-generated method stub
		return VentaRepository.save(entity);
	}

	@Transactional
	@Override
	public Venta update(Venta entity) throws Exception {
		// TODO Auto-generated method stub
		return VentaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		VentaRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		VentaRepository.deleteAll();
		
	}

}
