package com.LibreriaArgenProg.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Long isbn;
	private String titulo;
	private Integer anio;
	private Integer ejemplares;
	private Integer ejemplaresPrestados;
	private Integer ejemplaresRestantes;
	private Boolean alta;
	
	
	@OneToOne
	@JoinColumn(name = "editorialId")
	private Editorial editorialLibro;
	
	@OneToOne
	@JoinColumn(name = "autorId")
	private Autor autorLibro;

	public Libro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, Boolean alta) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.anio = anio;
		this.ejemplares = ejemplares;
		this.ejemplaresPrestados = ejemplaresPrestados;
		this.ejemplaresRestantes = ejemplaresRestantes;
		this.alta = alta;
	}
	
	
	
	
}
