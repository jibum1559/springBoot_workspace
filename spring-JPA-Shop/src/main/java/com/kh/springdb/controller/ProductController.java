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



/*
@Controller 와 @RestController

@Controller : 
- 어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것
- view를 생성하고 반환하는 역할을 하기도 함
- @RequestMapping 과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄
- 데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달
  (VIEW 템플릿 안에 들어있는 html과 상호작용 할 수 있도록 제어하는 컨트롤러)

@RestController :
- 조금 더 RESTful 웹 서비스를 제공하는 데 특화된 어노테이션
- @Controller에 @ResponseBody 를 함께 사용한 것과 유사하게 동작하지만
- 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션
- html view로 안감
- 데이터의 값이 나오는지 안나오는지 view가 없어도 확인할 수 있게 해주기 위함
  (DB에서 받은 내용을 출력하거나 우리가 지정한 값을 화면에 보여줄 수 있도록 해주는 컨트롤러)

주로 @Controller View(html 파일)을 반환하는 데 사용되고 @RestController는 @Controller에 @ResponseBody를
추가로 사용하는 것을 대체할 수 있어 코드가 조금 더 간결해짐

@ResponseBody : 
- 메서드가 retur해서 반환해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌 java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션


@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		product.ifPresent(value -> model.addAttribute("product", value));
		return "product_form";

※ Optional<Product> product = productService.getProductById(id);
- Optional 안에는 productService.getProductById(id)로 id값을 가져와서 id에 해당하는 제품을 가지고 옴
- 그런데 여기서 만약 id에 해당하는 제품이 존재하지 않는다면 Optional은 비어있게 됨
- 만약에 Optional이 비어있게 된다면 에러가 발생할 수 있지만(현재는) 추후 비어있을 경우를 대비해서 예외 값을 처리해주는 것이 좋음
- 예외 값을 처리하는 방법 : orElse 를 이용해서 대체값을 제공하거나 페이지 이동 처리를 할 수 있음(ex:error.html)
				이외에 orElseGet 는 대체값을 생성하는 함수를 제공
				     orElseThrow 는 예외를 던짐
				     
※ product.ifPresent(value -> model.addAttribute("product", value));
- ifPresent : 
  -> Optional 객체 안에 값이 존재할 경우 람다식 표현을 실행하기 위한 메서드
  -> Optional 객체 안에 값이 존재할 경우 실행할 것임
  
- value 값이 존재하면 모델에 product 변수명을 사용해서 product 안에 value 값을 추가할 것
- 추가된 product는 html 템플릿 안에서 product를 thymeLeaf를 통해 호출해서 value 값을 사용할 수 있음

※ 람다식
- 간결하게 함수를 표현하는 방법으로 간단하게 결과를 표현할 때 사용
- 기본코드 : (변수값) -> 변수값이 존재하거나 어떤 일을 발생할 경우의 결과를 작성
- value 값이 존재하면 -> model.addAttribute 여기에 값을 넣어주겠다

if (value != null) {
			model.addAttribute("product", value)
}

*/

