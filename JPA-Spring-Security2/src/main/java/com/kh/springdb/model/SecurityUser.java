package com.kh.springdb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class SecurityUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="siteUser_seq")
	@SequenceGenerator(name = "siteUser_seq", sequenceName="siteUser_seq",allocationSize=1)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}