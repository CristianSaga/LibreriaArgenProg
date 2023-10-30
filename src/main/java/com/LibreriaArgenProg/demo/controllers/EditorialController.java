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

import com.LibreriaArgenProg.demo.models.Editorial;
import com.LibreriaArgenProg.demo.services.EditorialServiceImpl;


@RestController
@RequestMapping("/editoriales")
public class EditorialController {

	@Autowired
	private EditorialServiceImpl editorialService;
	
	@PostMapping("/crear")
	public ResponseEntity<String> crearEditorial(@RequestBody Editorial editorial) {

		try {
			editorialService.crearEditorial(editorial);
			return new ResponseEntity<>("La editorial se creo correctamente", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Editorial>> obtenerEditoriales() {
		List<Editorial> allEditoriales = editorialService.obtenerEditoriales();

		if (!allEditoriales.isEmpty()) {
			return new ResponseEntity<>(allEditoriales, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{editorialId}")
	public ResponseEntity<Editorial> obtenerEditorialPorId(@PathVariable("editorialId") Integer id) {
		Editorial editorial = editorialService.obtenerEditorialPorId(id);

		if (editorial != null) {
			return new ResponseEntity<>(editorial, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Editorial> actualizarEditorial(@RequestBody Editorial editorial) {
		
		try {
			editorialService.actualizarEditorial(editorial);
			Editorial editorialActualizado = editorialService.obtenerEditorialPorId(editorial.getEditorialId());

			if (editorialActualizado != null) {
				return new ResponseEntity<>(editorialActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/borrar/{editorialId}")
	public ResponseEntity<String> borrarLibro(@PathVariable("editorialId") Integer id) {
		try {
			Editorial editorial = editorialService.obtenerEditorialPorId(id);

			if (editorial != null) {
				editorialService.borrarEditorial(id);
				return new ResponseEntity<>("La editorial se elimino correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
