package com.kh.springdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.repository.CustomerRepository;
import com.kh.springdb.vo.Customer;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	//고객 전체 조회하기
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	//가입하기
	public Customer insertCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	//정보 상세보기
	public Optional<Customer> getCustomerById(Long CustomerId) {
		return customerRepository.findById(CustomerId);
	}
	
	//탈퇴하기
	public void deleteCustomerById(Long CustomerId) {
		customerRepository.deleteById(CustomerId);
	}
}
