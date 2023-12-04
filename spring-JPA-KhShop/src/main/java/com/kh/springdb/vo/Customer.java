package com.kh.springdb.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_SEQ")
	@SequenceGenerator(name = "customer_SEQ", sequenceName = "customer_SEQ", allocationSize = 1)
	
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="nick_name")
	private String nickName;
	@Column(name="customer_pw")
	private String customerPw;
	@Column(name="customer_email")
	private String customerEmail;

}
