/**
 * 
 */
package com.asanchezt.springboot.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.asanchezt.springboot.models.entity.Producto;
import com.asanchezt.springboot.service.IProductoService;

/**
 * @author asancheztornero
 *
 */
@RestController
public class ProductoController {

	@Autowired
	private Environment env;

	@Autowired
	private IProductoService productoService;

	@Value("${server.port}")
	private Integer port;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			// producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws InterruptedException {
		if (id.equals(10L)) {
			throw new IllegalStateException("Producto no encontrado");
		}
		if (id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return producto;
	}

}
