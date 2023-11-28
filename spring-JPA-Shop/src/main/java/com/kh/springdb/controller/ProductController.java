package com.kh.springdb.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.kh.springdb.service.ProductService;
import com.kh.springdb.vo.Product;

@Controller
//@RestController
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
	}
	
	//delete는 @GetMapping
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/products";
	}
}



/*
@Controller 와 @RestController

@Controller : 
- 어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것
- view를 생성하고 반환하는 역할을 하기도 함
- @RequestMapping 과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄
- 데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달

@RestController :
- 조금 더 RESTful 웹 서비스를 제공하는 데 특화된 어노테이션
- @Controller에 @ResponseBody 를 함께 사용한 것과 유사하게 동작하지만
- 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션
- html view로 안감
- 데이터의 값이 나오는지 안나오는지 view가 없어도 확인할 수 있게 해주기 위함

주로 @Controller View(html 파일)을 반환하는 데 사용되고 @RestController는 @Controller에 @ResponseBody를
추가로 사용하는 것을 대체할 수 있어 코드가 조금 더 간결해짐

@ResponseBody : 
- 메서드가 retur해서 반환해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌 java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션
*/