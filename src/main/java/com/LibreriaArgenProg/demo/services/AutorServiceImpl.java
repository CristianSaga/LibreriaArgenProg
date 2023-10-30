package com.LibreriaArgenProg.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibreriaArgenProg.demo.models.Autor;
import com.LibreriaArgenProg.demo.repositories.IAutorRepository;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	private IAutorRepository autorRepository;
	
	@Override
	public void crearAutor(Autor autor) {
		autorRepository.save(autor);
		
	}

	@Override
	public List<Autor> obtenerAutores() {
		List<Autor> allAutores = autorRepository.findAll();
		return allAutores;
	}

	@Override
	public Autor obtenerAutorPorId(Integer id) {
		Autor autor = autorRepository.findById(id).orElse(null);
		return autor;
	}

	@Override
	public void actualizarAutor(Autor autor) {
		this.crearAutor(autor);
		
	}

	@Override
	public void borrarAutor(Integer id) {
		autorRepository.deleteById(id);
		
	}

}
