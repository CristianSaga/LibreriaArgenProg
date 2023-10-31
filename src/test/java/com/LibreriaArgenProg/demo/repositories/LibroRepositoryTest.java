package com.LibreriaArgenProg.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.LibreriaArgenProg.demo.models.Libro;

@DataJpaTest
public class LibroRepositoryTest {

	@Autowired
	private ILibroRepository libroRepository;
	
	private Libro libroSetup;
	
	@BeforeEach
	void setup() {
		libroSetup = new Libro(9788408156574L,"El código Da Vinci",2003,100000,75000,25000,true);
	}
	
	@Test
	@DisplayName("Test de creado de libro")
	void testCrearLibro() {
		
		//GIVEN = setup()
		
		//WHEN
		Libro libroBD = libroRepository.save(libroSetup);
		
		//THEN
		assertThat(libroBD).isNotNull();
		assertThat(libroBD.getId()).isGreaterThan(0);
		
	}
	
	@Test
	@DisplayName("Test para ver todos los libros")
	void testObtenerLibros() {
		
		//GIVEN
		Libro libroDos = new Libro(9789871138678L,"Diario de Ana Frank",1947,100000,75000,25000,true);
		Libro libroTres = new Libro(97845896424818L,"El hobbit",1937,30000,15000,15000,true);
		Libro libroCuatro = new Libro(977789365415L,"1984",1949,26000,13000,13000,true);
		
		libroRepository.save(libroSetup);
		libroRepository.save(libroDos);
		libroRepository.save(libroTres);
		libroRepository.save(libroCuatro);
		
		//WHEN
		List<Libro> allLibrosBD = libroRepository.findAll();
		
		//THEN
		assertThat(allLibrosBD).isNotNull();
		assertThat(allLibrosBD.size()).isEqualTo(4);
		
	}
	
	@Test
	@DisplayName("Test para obtener libro por Id")
	void testObtenerLibroPorId() {
		
		//GIVEN
		libroRepository.save(libroSetup);
		
		//WHEN
		Libro libroBD = libroRepository.findById(libroSetup.getId()).get();
		
		//THEN
		assertThat(libroBD).isNotNull();
	}
	
	
	@Test
	@DisplayName("Test para actualizar un libro")
	void testActualizarLibro() {
		
		//GIVEN
		libroRepository.save(libroSetup);
		
		Libro libroBD = libroRepository.findById(libroSetup.getId()).get();
		
		libroBD.setIsbn(00000000L);
		libroBD.setTitulo("Nuevo titulo");;
		libroBD.setAnio(1900);
		libroBD.setEjemplares(1000);
		libroBD.setEjemplaresPrestados(500);
		libroBD.setEjemplaresRestantes(500);
		libroBD.setAlta(false);
		
		//WHEN
		Libro libroActualizado = libroRepository.save(libroSetup);
		
		//THEN
		assertThat(libroActualizado.getIsbn()).isEqualTo(00000000L);
		assertThat(libroActualizado.getTitulo()).isEqualTo("Nuevo titulo");
		assertThat(libroActualizado.getAnio()).isEqualTo(1900);
		assertThat(libroActualizado.getEjemplares()).isEqualTo(1000);
		assertThat(libroActualizado.getEjemplaresPrestados()).isEqualTo(500);
		assertThat(libroActualizado.getEjemplaresRestantes()).isEqualTo(500);
		assertThat(libroActualizado.getAlta()).isEqualTo(false);
		
	}
	
	@Test
	@DisplayName("Test para borrar un libro")
	void testBorrarLibro() {
		
		//GIVEN
		libroRepository.save(libroSetup);
		
		//WHEN
		libroRepository.deleteById(libroSetup.getId());
		
		//THEN
		Optional<Libro> libroBorrado = libroRepository.findById(libroSetup.getId());
		
		assertThat(libroBorrado).isEmpty();
	}
}
