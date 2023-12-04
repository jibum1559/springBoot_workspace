package com.kh.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.service.CustomerService;
import com.kh.springdb.vo.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//고객 전체 조회하기
	@GetMapping
	public String getAllCustomers(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customerList";
	}
	
	//가입하기
	@GetMapping("/new")
	public String showCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customerForm";
	}
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerService.insertCustomer(customer);
		return "redirect:/customers";
	}
	
	//정보 상세보기
	
	//탈퇴하기
}