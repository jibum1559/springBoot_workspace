package com.kh.spring.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Product;


@Service
public class ProductService {
	
		private final ProductRepository productRepository;
		
		@Autowired 
		public ProductService(ProductRepository productRepository) {
			this.productRepository = productRepository;
		}
		
		//모든 상품을 조회하는 메서드 
		public List<Product> getAllProducts(){
			return productRepository.findAll();
		}
		
		//상품 1개만 조회하는 메서드
		public Optional<Product> getProductById(Long id){
			return productRepository.findById(id);
		}
		//저장하는 메서드 

		public Product saveProduct(Product product) {
			return productRepository.save(product); //이미 레포지토리에서 저장 작업이 끝난 상태
		}
		//삭제하는 메서드
		public void deleteProductById(Long id) {
			productRepository.deleteById(id);
		}
	}