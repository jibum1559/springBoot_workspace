package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.vo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
