package com.kh.springdb.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "customer_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orders_seq")
	@SequenceGenerator(name="orders_seq", sequenceName = "orders_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	private LocalDate orderDate;
	
	@PrePersist
	public void orderDate() { //날짜가 바로 나와야하기 때문에 void
		this.orderDate = LocalDate.now();
	}
}
