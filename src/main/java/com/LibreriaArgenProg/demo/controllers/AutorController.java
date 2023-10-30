package com.LibreriaArgenProg.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibreriaArgenProg.demo.models.Autor;
import com.LibreriaArgenProg.demo.services.AutorServiceImpl;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorServiceImpl autorService;
	
	@PostMapping("/crear")
	public ResponseEntity<String> crearAutor(@RequestBody Autor autor) {

		try {
			autorService.crearAutor(autor);
			return new ResponseEntity<>("El autor se creo correctamente", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("")
	public ResponseEntity<List<Autor>> obtenerAutores() {
		List<Autor> allAutores = autorService.obtenerAutores();

		if (!allAutores.isEmpty()) {
			return new ResponseEntity<>(allAutores, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{autorId}")
	public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable("autorId") Integer id) {
		Autor autor = autorService.obtenerAutorPorId(id);

		if (autor != null) {
			return new ResponseEntity<>(autor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Autor> actualizarAutor(@RequestBody Autor autor) {
		
		try {
			autorService.actualizarAutor(autor);
			Autor autorActualizado = autorService.obtenerAutorPorId(autor.getAutorId());

			if (autorActualizado != null) {
				return new ResponseEntity<>(autorActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/borrar/{autorId}")
	public ResponseEntity<String> borrarAutor(@PathVariable("autorId") Integer id) {
		try {
			Autor autor = autorService.obtenerAutorPorId(id);

			if (autor != null) {
				autorService.borrarAutor(id);
				return new ResponseEntity<>("El autor se elimino correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
