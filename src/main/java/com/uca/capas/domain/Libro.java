package com.uca.capas.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="cat_libro")
public class Libro {
	
	@Id
	@Column(name="c_libro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cLibro;
	
	@Column(name="s_titulo")
	@NotEmpty(message="El campo Titulo no puede ir vacio")
	@Size(max=30, message="El campo sobrepasa la cantidad de 30 caracteres")
	private String sTitulo;
	
	@Column(name="s_autor")
	@NotEmpty(message="El campo Autor no puede ir vacio")
	@Size(max=30, message="El campo sobrepasa la cantidad de 30 caracteres")
	private String sAutor;
	
	@Column(name="f_ingreso")
	private Date fIngreso;
	
	@Column(name="b_estado")
	private Boolean bEstado;
	
	@Column(name="s_isbn")
	@NotEmpty(message="El campo ISBN no puede ir vacio")
	@Pattern(regexp="^$|[0-9]{10}", message="ISBN no valido")
	private String isbn;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="c_categoria")
	private Categoria cat_categoria;
	
	public Libro() {}

	public Integer getcLibro() {
		return cLibro;
	}

	public void setcLibro(Integer cLibro) {
		this.cLibro = cLibro;
	}

	public String getsTitulo() {
		return sTitulo;
	}

	public void setsTitulo(String sTitulo) {
		this.sTitulo = sTitulo;
	}

	public String getsAutor() {
		return sAutor;
	}

	public void setsAutor(String sAutor) {
		this.sAutor = sAutor;
	}

	public String getfIngreso() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String newF = fIngreso.toString();
		Date date = df.parse(newF);
		df.applyPattern("dd/MM/yyyy hh:mm aa"); 
		String newDateS = df.format(date);
		
		return newDateS;
	}

	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}

	public Boolean getbEstado() {
		return bEstado;
	}

	public void setbEstado(Boolean bEstado) {
		this.bEstado = bEstado;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Categoria getCat_categoria() {
		return cat_categoria;
	}

	public void setCat_categoria(Categoria cat_categoria) {
		this.cat_categoria = cat_categoria;
	}
}
	
