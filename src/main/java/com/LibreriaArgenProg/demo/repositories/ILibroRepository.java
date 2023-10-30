package com.LibreriaArgenProg.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LibreriaArgenProg.demo.models.Libro;


@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer>{

	
}
