package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cart {
	
	//비회원 아이디값 비회원이 주문한 장바구니 리스트
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="carts_seq")
	@SequenceGenerator(name="carts_seq", sequenceName = "carts_seq", allocationSize = 1)
	private Long Id;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL)  //그래서 어지간해서는 안쓴다고 했는데 쓰는 이유가 뭐야
	private List<CartItem> cartItems = new ArrayList<>();
	
	//Order 객체 생성으로 인한 추가 mapped
	//만약에 Entity에 설정한 name 이 있다면
	//@JoinColumn(name="customer_order") 참고하기!
	@OneToOne(mappedBy="cart")
	@JoinColumn(name="order_id")
	private Order order;
	
	
	public int getTotalAmount() {
		return cartItems.stream().mapToInt(item -> item.getCount() * Integer.parseInt(item.getItem().getPrice())).sum();
	}
	
	public int getTotalCount() {
		return cartItems.stream().mapToInt(CartItem::getCount).sum();
		/*
		stream() : 리스트를 받아서 스트림으로 변환하겠다.
		- List나 Map 배열처리를 해서 총 가격 합을 받아야 하지만 
		- stream을 이용해서 List나 Map 대신 한 번에 받을 수 있도록 처리해주는 메서드
		mapToInt(CartItem::getCount) : CartItem 객체를 int로 감싸주는 작업을 함
		- CartItem 객체에서 getCount 메서드를 호출해서 각각 
		- CartItem에 연결된 count 값을 가지고 오고 이 값을 int로 감싸주는 역할을 함
		- nt로 감싸진 count 값을 sum이라는 메서드를 활용해서 모두 더해주겠다 선언
		 */
		 
	}
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cart_seq")
	@SequenceGenerator(name="cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	private int id;
	
	//카트에 담긴 총 상품 개수
	private int count;
	
	//카트에 담긴 상품 리스트를 넣기 위한 배열 생성
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems = new ArrayList<>();
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private LocalDate createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	
	public static Cart createCart() {
		Cart cart = new Cart();
		cart.setCount(0); //장바구니에 상품 개수가 없기 때문에 0으로 초기화
		return cart;
	}
	*/
	
}
