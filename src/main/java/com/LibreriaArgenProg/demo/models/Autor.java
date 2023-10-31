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
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer autorId;
	
	private String nombre;
	private Boolean alta;
	
	public Autor(String nombre, Boolean alta) {
		this.nombre = nombre;
		this.alta = alta;
	}
	
	
	
}
