package com.LibreriaArgenProg.demo.services;

import java.util.List;

import com.LibreriaArgenProg.demo.models.Editorial;

public interface IEditorialService {

	public void crearEditorial(Editorial editorial);
	
	public List<Editorial> obtenerEditoriales();
	
	public Editorial obtenerEditorialPorId(Integer id);
	
	public void actualizarEditorial(Editorial editorial);
	
	public void borrarEditorial(Integer id);
}
