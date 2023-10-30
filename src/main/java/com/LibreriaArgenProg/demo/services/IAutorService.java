package com.LibreriaArgenProg.demo.services;

import java.util.List;

import com.LibreriaArgenProg.demo.models.Autor;

public interface IAutorService {

	public void crearAutor(Autor autor);
	
	public List<Autor> obtenerAutores();
	
	public Autor obtenerAutorPorId(Integer id);
	
	public void actualizarAutor(Autor autor);
	
	public void borrarAutor(Integer id);
}
