package com.LibreriaArgenProg.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LibreriaArgenProg.demo.models.Editorial;

@Repository
public interface IEditorialRepository extends JpaRepository<Editorial, Integer>{

}
