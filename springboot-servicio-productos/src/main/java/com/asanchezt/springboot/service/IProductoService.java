/**
 * 
 */
package com.asanchezt.springboot.service;

import java.util.List;

import com.asanchezt.springboot.models.entity.Producto;

/**
 * @author asancheztornero
 *
 */
public interface IProductoService {

	
	public List<Producto> findAll();
	public Producto findById(Long id);
}
