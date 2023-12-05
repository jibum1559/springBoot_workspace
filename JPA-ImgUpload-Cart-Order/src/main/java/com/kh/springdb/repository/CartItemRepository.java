package com.kh.springdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.vo.Cart;
import com.kh.springdb.model.vo.CartItem;

public interface CartItemRepository extends JpaRepository<Cart, Integer> {
	//CartId와 itemId로 CartItem 찾는 메서드
	CartItem findByCartIdAndItemId(int cartId, int itemId);
	
	//Id 값에 해당하는 아이템을 찾는 메서드
	CartItem findCartItemById(int id);
	
	//모든 카트에 담긴 아이템을 반환하는 리스트
	List<CartItem> findCartItemByItemId(int id);
}