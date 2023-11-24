package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.Product;

@Mapper
public interface ProductMapper {
	List<Product> getAllProducts();

}
