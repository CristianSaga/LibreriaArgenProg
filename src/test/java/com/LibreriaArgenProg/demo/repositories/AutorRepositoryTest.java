package com.LibreriaArgenProg.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.LibreriaArgenProg.demo.models.Autor;

@DataJpaTest
public class AutorRepositoryTest {

	
	@Autowired
	private IAutorRepository autorRepository;
	
	private Autor autorSetup;
	
	@BeforeEach
	void setup() {
		autorSetup = new Autor("J.K.Rowling",true);
	}
	
	@Test
	@DisplayName("Test de creado de autor")
	void testCrearAutor() {
		
		//GIVEN = setup()
		
		//WHEN
		Autor autorBD = autorRepository.save(autorSetup);
		
		//THEN
		assertThat(autorBD).isNotNull();
		assertThat(autorBD.getAutorId()).isGreaterThan(0);
		
	}
	
	@Test
	@DisplayName("Test para ver todos los autores")
	void testObtenerAutores() {
		
		//GIVEN
		Autor autorDos = new Autor("Anne Frank",true);
		Autor autorTres = new Autor("George Orwell",true);
		
		autorRepository.save(autorSetup);
		autorRepository.save(autorDos);
		autorRepository.save(autorTres);
		
		//WHEN
		List<Autor> allAutoresBD = autorRepository.findAll();
		
		//THEN
		assertThat(allAutoresBD).isNotNull();
		assertThat(allAutoresBD.size()).isEqualTo(3);
		
	}
	
	@Test
	@DisplayName("Test para obtener autor por Id")
	void testObtenerAutorPorId() {
		
		//GIVEN
		autorRepository.save(autorSetup);
		
		//WHEN
		Autor autorBD = autorRepository.findById(autorSetup.getAutorId()).get();
		
		//THEN
		assertThat(autorBD).isNotNull();
	}
	
	
	@Test
	@DisplayName("Test para actualizar un autor")
	void testActualizarAutor() {
		
		//GIVEN
		autorRepository.save(autorSetup);
		
		Autor autorBD = autorRepository.findById(autorSetup.getAutorId()).get();
		
		autorBD.setNombre("Nuevo autor");
		autorBD.setAlta(false);
		
		//WHEN
		Autor autorActualizado = autorRepository.save(autorSetup);
		
		//THEN
		assertThat(autorActualizado.getNombre()).isEqualTo("Nuevo autor");
		assertThat(autorActualizado.getAlta()).isEqualTo(false);
		
	}
	
	@Test
	@DisplayName("Test para borrar un autor")
	void testBorrarAutor() {
		
		//GIVEN
		autorRepository.save(autorSetup);
		
		//WHEN
		autorRepository.deleteById(autorSetup.getAutorId());
		
		//THEN
		Optional<Autor> autorBorrado = autorRepository.findById(autorSetup.getAutorId());
		
		assertThat(autorBorrado).isEmpty();
	}
}
