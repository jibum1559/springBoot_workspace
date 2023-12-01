package com.kh.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.spring.shop.service.ShopService;
import com.kh.spring.shop.vo.Product;

//월요일에 CartController를 따로 만들어줄거임
//주문과 결제 이것만 한다!!!!
@Controller
public class ShopController {
	@Autowired
	public ShopService shopservice;
	
	public ShopController(ShopService shopservice) {
		this.shopservice = shopservice;
	}
	
	//주문에 관련된 내용을 처리하는 메서드
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Product product, int quantity, Model model) {
		//주문을 처리하고 주문한 결과를 Order에 반환하겠다
		shopservice.placeOrder(product, quantity);
		//여기서 model에 필요한 데이터를 추가
		model.addAttribute("msg", "결제가 성공적으로 처리되었습니다.");
		//주문 확인 페이지로 이동
		return "redirect:/orderCheck";
	}
	
	@PostMapping("/paymentFinish")
	public String processPayMent(Long orderId, String paymentSatus, Model model) {
		//주문에 대한 결제를 처리하고 payment를 반환
		shopservice.savePayment(orderId, paymentSatus);
		model.addAttribute("msg", "결제가 성공적으로 처리되었습니다.");
		return "redirect:/paymentCheck";
	}
}


/*
 @RequestBody : 
 - 정보를 url이 아니라 자바 객체로 받음
 - 자바 객체로 받아서 요청했던 body에 위치하도록 할 때 사용
 
 @RequestParam : 정보를 url에 저장
 - localhost:8080/어떤 정보를 받고 받은 정보를 이 주소 위에다가 정보를 저장하는 것을 requestParam 이라고 한다.
 
  
 
 */