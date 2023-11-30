package com.kh.spring.cafe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.cafe.repository.CafeRepository;
import com.kh.spring.cafe.vo.Cafe;

@Service
public class CafeService {
	@Autowired
	private CafeRepository cafeRepository;
	
	//전체검색
	public List<Cafe> getAllCafes(){
		return cafeRepository.findAll();
	}
	//부분검색
	public Optional<Cafe> getCafeById(Long id) {
		return cafeRepository.findById(id);
	}
	//추가하기
	public Cafe saveCafe(Cafe cafe) {
		return cafeRepository.save(cafe);
	}
	
	//삭제하기
	public void deleteCafeById(Long id) {
		cafeRepository.deleteById(id);
	}
	
	public List<Cafe> findCafes(String keyword) {
		//return cafeRepository.findCafe(keyword);
		return cafeRepository.findByNameContaining(keyword);
	}
}