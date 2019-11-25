package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.TipoDescuento;
import pe.edu.upn.ProyectoWebFinal.model.repository.TipoDescuentoRepository;
import pe.edu.upn.ProyectoWebFinal.service.TipoDescuentoService;

@Service
public class TipoDescuentoServiceImpl implements TipoDescuentoService{

	@Autowired
	private TipoDescuentoRepository tipoDescuentoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<TipoDescuento> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tipoDescuentoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<TipoDescuento> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tipoDescuentoRepository.findById(id);
	}

	@Transactional
	@Override
	public TipoDescuento save(TipoDescuento entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoDescuentoRepository.save(entity);
	}

	@Transactional
	@Override
	public TipoDescuento update(TipoDescuento entity) throws Exception {
		// TODO Auto-generated method stub
		return tipoDescuentoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		tipoDescuentoRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		tipoDescuentoRepository.deleteAll();
		
	}

}
