package com.LibreriaArgenProg.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LibreriaArgenProg.demo.models.Autor;

@Repository
public interface IAutorRepository extends JpaRepository<Autor,Integer>{

}
