package com.asanchezt.springboot.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asanchezt.springboot.models.entity.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long> {

}
