package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="cat_categoria")
public class Categoria {
	
	@Id
	@Column(name="c_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cCategoria;
	
	@Column(name="s_categoria")
	@NotEmpty(message="El campo Categoria no puede ir vacio")
	@Size(max=50, message="El campo sobrepasa la cantidad de 50 caracteres")
	private String sCategoria;
	
	@OneToMany(mappedBy="cat_categoria", fetch=FetchType.EAGER)
	private List<Libro> libros;
	
	public Categoria() {}

	public Integer getcCategoria() {
		return cCategoria;
	}

	public void setcCategoria(Integer cCategoria) {
		this.cCategoria = cCategoria;
	}

	public String getsCategoria() {
		return sCategoria;
	}

	public void setsCategoria(String sCategoria) {
		this.sCategoria = sCategoria;
	}
}
