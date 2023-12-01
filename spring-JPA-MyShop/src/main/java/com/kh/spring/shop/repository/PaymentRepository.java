package com.kh.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
