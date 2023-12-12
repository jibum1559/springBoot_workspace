package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.kh.springdb.model.SecurityUser;

public interface UserRepository extends JpaRepository<SecurityUser, Long> {

	//login을 위한 옵션활용 repo 생성
	Optional<SecurityUser> findByusername(String username);
}
