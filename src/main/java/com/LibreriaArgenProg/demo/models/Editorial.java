package com.LibreriaArgenProg.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer editorialId;
	
	private String nombre;
	private Boolean alta;
	
	public Editorial(String nombre, Boolean alta) {
		this.nombre = nombre;
		this.alta = alta;
	}
	
	
}
