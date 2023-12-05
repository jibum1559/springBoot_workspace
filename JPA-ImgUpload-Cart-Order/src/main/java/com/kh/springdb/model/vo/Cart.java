package com.kh.springdb.model.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cart_seq")
	@SequenceGenerator(name="cart_seq", sequenceName="cart_seq", allocationSize=1)
	private int id;
	
	
	@OneToOne()//fetch에 관련된 타입을 작성해줄 것
	@JoinColumn(name = "user_id")
	User user; //구매자의 장바구니
	
	@OneToMany(mappedBy = "cart") //장바구니 안에 상품들이 담길 예정이기 때문에
	//장바구니 One : 상품들 Many
	private List<CartItem> cart_items = new ArrayList<>();
}