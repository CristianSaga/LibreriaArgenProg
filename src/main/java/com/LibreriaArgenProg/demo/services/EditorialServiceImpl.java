package com.LibreriaArgenProg.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibreriaArgenProg.demo.models.Editorial;
import com.LibreriaArgenProg.demo.repositories.IEditorialRepository;

@Service
public class EditorialServiceImpl implements IEditorialService{

	@Autowired
	private IEditorialRepository editorialRepository;
	
	@Override
	public void crearEditorial(Editorial editorial) {
		editorialRepository.save(editorial);
		
	}

	@Override
	public List<Editorial> obtenerEditoriales() {
		List<Editorial> allSales = editorialRepository.findAll();
		return allSales;
	}

	@Override
	public Editorial obtenerEditorialPorId(Integer id) {
		Editorial editorial = editorialRepository.findById(id).orElse(null);
		return editorial;
	}

	@Override
	public void actualizarEditorial(Editorial editorial) {
		this.crearEditorial(editorial);
		
	}

	@Override
	public void borrarEditorial(Integer id) {
		editorialRepository.deleteById(id);
		
	}

	
	
}
