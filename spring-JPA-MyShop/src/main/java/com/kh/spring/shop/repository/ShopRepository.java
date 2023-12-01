package com.kh.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Order;

public interface ShopRepository extends JpaRepository<Order, Long> {

}
