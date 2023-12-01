package com.kh.spring.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.shop.service.ProductService;
import com.kh.spring.shop.vo.Product;

@Controller
@RequestMapping("/products")
public class ProductController {
private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//모든 제품을 보기 위한 제품 List 확인 메서드
	@GetMapping
	public String getAllProduct(Model model){
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		//return productService.getAllProducts();
		//return "products";
		return "product_list";
	}
	
	//제품 상세보기 메서드
	@GetMapping("/detail/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		product.ifPresent(value -> model.addAttribute("product",value));
		return "product_detail";
	}
	
	// 수정하기 메서드 추가
	@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		product.ifPresent(value -> model.addAttribute("product", value));
		return "product_form";
	}
	
	//작성한 내용을 저장하기 위한 메서드
	//save는 @GetMapping : 작성할 url을 불러오기 위한 주소값 설정
	@GetMapping("/new")
	public String displayProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "product_form";
	}
	//save는 @PostMapping : 작성한 내용을 저장할 url 설정
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/product";
	  //service를 불러서 view가 이거 전달해달래
	  //return("redirect:/다시 돌아갈 html파일이름")
	}
	
	
	//delete는 @GetMapping
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/products";
	}
}

