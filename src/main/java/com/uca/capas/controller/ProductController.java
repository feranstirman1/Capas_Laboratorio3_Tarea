package com.uca.capas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Product;

@Controller
public class ProductController {

	private List<Product> productos = new ArrayList<Product>();
	
	@GetMapping("/producto")
	public ModelAndView producto() {
		productos.add(new Product(0,"SPIDERMAN HOMECOMING",23));
		productos.add(new Product(1,"AVENGERS ENDGAME",17));
		productos.add(new Product(2,"JOKER",9));
		productos.add(new Product(3,"SHAZAM",4));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("producto", new Product());
		mav.addObject("productos", productos);
		mav.setViewName("productos");
		return mav;
	}
	
	@PostMapping("/validar")
	public ModelAndView validar(Product producto) {
		Product productoComprado = productos.get(producto.getId());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("nombre", productoComprado.getNombre());
		
		Integer cantidadComprada = producto.getCantidad();
		Integer cantidadDisponible = productoComprado.getCantidad();
		
		if(cantidadComprada>cantidadDisponible) {
			mav.setViewName("error");
			return mav;
		}else {
			mav.setViewName("compra");
			return mav;
		}
	}
	
}
