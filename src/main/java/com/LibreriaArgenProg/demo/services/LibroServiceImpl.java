package com.LibreriaArgenProg.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibreriaArgenProg.demo.models.Libro;
import com.LibreriaArgenProg.demo.repositories.ILibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroRepository libroRepository;

	@Override
	public void guardarLibro(Libro libro) {
		libroRepository.save(libro);

	}

	@Override
	public List<Libro> obtenerLibros() {
		List<Libro> allLibros = libroRepository.findAll();
		return allLibros;

	}

	@Override
	public Libro obtenerLibroPorId(Integer id) {
		Libro libro = libroRepository.findById(id).orElse(null);
		return libro;
	}

	@Override
	public void actualizarLibro(Libro libro) {
		this.guardarLibro(libro);
	}

	@Override
	public void borrarLibro(Integer id) {
		libroRepository.deleteById(id);

	}

}
