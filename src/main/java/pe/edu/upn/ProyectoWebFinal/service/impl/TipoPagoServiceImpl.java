package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.TipoPago;
import pe.edu.upn.ProyectoWebFinal.model.repository.TipoPagoRepository;
import pe.edu.upn.ProyectoWebFinal.service.TipoPagoService;

@Service
public class TipoPagoServiceImpl implements TipoPagoService{

	@Autowired
	private TipoPagoRepository tipoPagoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<TipoPago> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tipoPagoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<TipoPago> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tipoPagoRepository.findById(id);
	}

	@Transactional
	@Override
	public TipoPago save(TipoPago entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoPagoRepository.save(entity);
	}

	@Transactional
	@Override
	public TipoPago update(TipoPago entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoPagoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		tipoPagoRepository.deleteById(id);
	}

	@Transactional
	@Override
	
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		tipoPagoRepository.deleteAll();
	}

}
