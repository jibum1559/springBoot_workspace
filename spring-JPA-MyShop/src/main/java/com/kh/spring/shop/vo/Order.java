package com.kh.spring.shop.vo;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
	//데이터베이스에 주문 정보를 저장하기 위한 클래스
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orderId_Seq")
	@SequenceGenerator(name = "orderId_Seq", sequenceName="orderId_Seq", allocationSize=1)
	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id") //옆에 만들어 놓은 product 테이블의 product_id를 기준하여 조인
	private Product product;
	private int quantity;
}

/*
 ※ @JoinColumn(name = "조인하고자하는 컬럼명") 외래키(Foreign Key)
 -데이터베이스에 테이블로 존재하는 객체를 작성

 ※ private int quantity = 해당 제품의 수량을 표현
  
 ※ @ManyToOne(N : 1) 
 - 다대일(N : 1) 관계를 설정하는 어노테이션 
 - 주문이 하나의 제품에 속하는 경우 product_id 값이 중복으로 들어갈 수 있음을 나타내기 위해 N:1을 설정
 
 ※ @OneToMany(1 : N)

 ※ @OneToOne(1 : 1) = 1개 계정 : 1개 블로그
 
 ※ @ManyToMany(N : N)
 
 
 */