package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
	private int id;
	
	private String name;
	
	private String text;
	
	private String price;
	
	private int count;
	
	private int stock;
	
	private int isSoldout;
	
	//댓글 작성을 위한 Comment
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL) //상품을 삭제하면 댓글들 다 삭제되도록
	private List<Comments> comments;
	
	//상품 이미지를 위한 필드 설정
	
	private String imgName;
	private String imgPath;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	//자동으로 날짜 생성해서 넣기
	public void createDate() {
		this.createDate = LocalDate.now();
	}
}
