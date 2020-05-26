package com.uca.capas.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	LibroService ls;
	@Autowired
	CategoriaService cs;
	
	@RequestMapping("/index")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("mensaje", "");
		return mav;
	}
	
	@RequestMapping("/libro")
	public ModelAndView libro() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertL");
		List<Categoria> cats = null;
		try {
			cats = cs.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("categorias", cats);
		mav.addObject("libro", new Libro());
		return mav;
	}
	
	@RequestMapping("/categoria")
	public ModelAndView categoria() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertC");
		mav.addObject("categoria", new Categoria());
		return mav;
	}
	
	@RequestMapping("/insertc")
	public ModelAndView insertCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			try {
				cs.insert(categoria);
			} catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("index");
			mav.addObject("mensaje", "Categoria guardada con exito");
		}
		else {
			mav.setViewName("insertC");
		}
		return mav;
	}
	
	@RequestMapping("/insertl")
	public ModelAndView insertLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			Calendar c = Calendar.getInstance();
			String fecha = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH)+1) + "/" + Integer.toString(c.get(Calendar.DATE)) + " " +
					Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(c.get(Calendar.MINUTE));
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(fecha);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
			libro.setfIngreso(date);
			if(libro.getbEstado() == null) {
				libro.setbEstado(false);
			}
			try {
				ls.insert(libro);
			} catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("index");
			mav.addObject("mensaje", "Se ha ingresado el libro con exito");
		}
		else {
			List<Categoria> cats = null;
			try {
				cats = cs.findAll();
			} catch(Exception e) {
				e.printStackTrace();
			}
			mav.addObject("categorias", cats);
			mav.setViewName("insertL");
		}
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		try {
			libros = ls.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("listado");
		mav.addObject("libros", libros);
		return mav;
	}
}
