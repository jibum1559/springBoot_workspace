package com.kh.springdb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class SecurityUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sec_user_seq")
	@SequenceGenerator(name = "sec_user_seq", sequenceName = "sec_user_seq", allocationSize = 1)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
}
