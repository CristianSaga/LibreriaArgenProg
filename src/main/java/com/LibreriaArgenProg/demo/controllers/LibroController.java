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

import com.LibreriaArgenProg.demo.models.Libro;
import com.LibreriaArgenProg.demo.services.LibroServiceImpl;

@RestController
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	private LibroServiceImpl libroService;
	
	@PostMapping("/crear")
	public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {

		try {
			libroService.guardarLibro(libro);
			return new ResponseEntity<>("El libro se creo correctamente", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Libro>> obtenerLibros() {
		List<Libro> allLibros = libroService.obtenerLibros();

		if (!allLibros.isEmpty()) {
			return new ResponseEntity<>(allLibros, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{libroId}")
	public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("libroId") Integer id) {
		Libro libro = libroService.obtenerLibroPorId(id);

		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro libro) {
		
		try {
			libroService.actualizarLibro(libro);
			Libro libroActualizado = libroService.obtenerLibroPorId(libro.getId());

			if (libroActualizado != null) {
				return new ResponseEntity<>(libroActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/borrar/{libroId}")
	public ResponseEntity<String> borrarLibro(@PathVariable("libroId") Integer id) {
		try {
			Libro libro = libroService.obtenerLibroPorId(id);

			if (libro != null) {
				libroService.borrarLibro(id);
				return new ResponseEntity<>("El libro se elimino correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
