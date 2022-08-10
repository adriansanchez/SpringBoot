package com.asanchezt.springboot.models.service;

import java.util.List;

import com.asanchezt.springboot.models.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
}
