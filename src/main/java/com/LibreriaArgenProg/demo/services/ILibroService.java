package com.LibreriaArgenProg.demo.services;

import java.util.List;

import com.LibreriaArgenProg.demo.models.Libro;


public interface ILibroService {
	
	public void guardarLibro(Libro libro);
	
	public List<Libro> obtenerLibros();
	
	public Libro obtenerLibroPorId(Integer id);
	
	public void actualizarLibro(Libro libro);
	
	public void borrarLibro(Integer id);

}