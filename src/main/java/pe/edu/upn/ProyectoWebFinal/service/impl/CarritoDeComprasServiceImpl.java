package pe.edu.upn.ProyectoWebFinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWebFinal.model.entity.CarritoDeCompras;
import pe.edu.upn.ProyectoWebFinal.model.repository.CarritoDeComprasRepository;
import pe.edu.upn.ProyectoWebFinal.service.CarritoDeComprasService;

@Service
public class CarritoDeComprasServiceImpl implements CarritoDeComprasService{

	@Autowired
	private CarritoDeComprasRepository carritoDeComprasRepository;
	@Transactional(readOnly=true)
	@Override
	public List<CarritoDeCompras> findAll() throws Exception {
		// TODO Auto-generated method stub
		return carritoDeComprasRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<CarritoDeCompras> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return carritoDeComprasRepository.findById(id);
	}

	@Transactional
	@Override
	public CarritoDeCompras save(CarritoDeCompras entity) throws Exception {
		// TODO Auto-generated method stub
		return carritoDeComprasRepository.save(entity);
	}

	@Transactional
	@Override
	public CarritoDeCompras update(CarritoDeCompras entity) throws Exception {
		// TODO Auto-generated method stub
		return carritoDeComprasRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		carritoDeComprasRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		carritoDeComprasRepository.deleteAll();
		
	}

}
