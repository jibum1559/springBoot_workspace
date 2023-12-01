package com.kh.spring.shop.service;

import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.OrderRepository;
import com.kh.spring.shop.repository.PaymentRepository;
import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Cart;
import com.kh.spring.shop.vo.Order;
import com.kh.spring.shop.vo.Payment;
import com.kh.spring.shop.vo.Product;

@Service
public class ShopService {
	//최종적으로 만들어준 
	//order, cart 정보를 가지고 
	//결제를 하거나 장바구니에 상품을 추가해서 주문을 생성해주는 메서드
	public Cart cart = new Cart();
	private ProductRepository productRepository;
	private PaymentRepository paymentRepository;
	private OrderRepository orderRepository;
	public Order placeOrder(Product product, int quantity) {
		//장바구니에 상품추가
		cart.addToCart(product, quantity);
		
		//주문번호 생성
		Order order = createOrder(product, quantity);
		
		//만약에 데이터베이스에 주문 정보를 저장하고 반환할 수 있으므로
		// saveOrder(order);
		return order;
	}
	// 주문정보 생성 메서드
	public Order createOrder(Product product, int quantity) {
		Order order = new Order();
		order.setProduct(product);
		order.setQuantity(quantity);
		return order;	
	}
	
	// 주문 정보 조회 메서드
	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
		// 주문한 수량 추가
	}
	
	// *****위와 같이 쓰던 방법을 밑의 방법으로 바꿔서 사용할 수 있는 부분 확인 *****
	
	//상품 정보 조회 메서드
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
		//orElse(null); //Optional 사용하지 않고 그냥 null 값으로 처리하라는 의미로 사용가능
	}
	
	//결제 처리 메서드
	private Payment savePayment(Order order, String payment) {
		//결제 정보 처리
		Payment payments = new Payment();
		payments.setOrder(order);
		payments.setPaymentStatus(payment);
		return payments;
	}
}
