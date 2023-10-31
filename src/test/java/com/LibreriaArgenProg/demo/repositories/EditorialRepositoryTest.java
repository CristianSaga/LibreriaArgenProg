package com.LibreriaArgenProg.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.LibreriaArgenProg.demo.models.Editorial;

@DataJpaTest
public class EditorialRepositoryTest {

	@Autowired
	private IEditorialRepository editorialRepository;

	private Editorial editorialSetup;

	@BeforeEach
	void setup() {
		editorialSetup = new Editorial("Editorial DeBolsillo", true);
	}

	@Test
	@DisplayName("Test de creado de editorial")
	void testCrearEditorial() {

		// GIVEN = setup()

		// WHEN
		Editorial editorialBD = editorialRepository.save(editorialSetup);

		// THEN
		assertThat(editorialBD).isNotNull();
		assertThat(editorialBD.getEditorialId()).isGreaterThan(0);

	}

	@Test
	@DisplayName("Test para ver todos las editoriales")
	void testObtenerEditoriales() {

		// GIVEN
		Editorial editorialDos = new Editorial("EDG Ediciones", true);
		Editorial editorialTres = new Editorial("Editorial Salamandra", true);

		editorialRepository.save(editorialSetup);
		editorialRepository.save(editorialDos);
		editorialRepository.save(editorialTres);

		// WHEN
		List<Editorial> allEditorialesBD = editorialRepository.findAll();

		// THEN
		assertThat(allEditorialesBD).isNotNull();
		assertThat(allEditorialesBD.size()).isEqualTo(3);

	}

	@Test
	@DisplayName("Test para obtener editorial por Id")
	void testObtenerEditorialPorId() {

		// GIVEN
		editorialRepository.save(editorialSetup);

		// WHEN
		Editorial editorialBD = editorialRepository.findById(editorialSetup.getEditorialId()).get();

		// THEN
		assertThat(editorialBD).isNotNull();
	}

	@Test
	@DisplayName("Test para actualizar una editorial")
	void testActualizarEditorial() {

		// GIVEN
		editorialRepository.save(editorialSetup);

		Editorial editorialBD = editorialRepository.findById(editorialSetup.getEditorialId()).get();

		editorialBD.setNombre("Nueva editorial");
		editorialBD.setAlta(false);

		// WHEN
		Editorial editorialActualizado = editorialRepository.save(editorialSetup);

		// THEN
		assertThat(editorialActualizado.getNombre()).isEqualTo("Nueva editorial");
		assertThat(editorialActualizado.getAlta()).isEqualTo(false);

	}

	@Test
	@DisplayName("Test para borrar una editorial")
	void testBorrarEditorial() {

		// GIVEN
		editorialRepository.save(editorialSetup);

		// WHEN
		editorialRepository.deleteById(editorialSetup.getEditorialId());

		// THEN
		Optional<Editorial> editorialBorrado = editorialRepository.findById(editorialSetup.getEditorialId());

		assertThat(editorialBorrado).isEmpty();
	}
}
