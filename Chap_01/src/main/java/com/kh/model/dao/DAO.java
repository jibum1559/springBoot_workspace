package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.DTO;

public class DAO {

	//사용자를 조회하기 위해 작성할 SQL문
	
	//1. 사용자가 있는지 확인하기 위해 '전체' 사용자를 조회하는 SQL문
	public static List<DTO> selectAllUsers() throws SQLException{
		
		//1-1 커넥션 연결하기 위한 getConnection()
		Connection conn = JDBCTemplate.getConnection();
		
		//1-2 PrdparedStatement = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//1-3 List로 조회된 내용을 모두 담을 수 있는 배열 생성
		List<DTO> userList = new ArrayList<>();
		
		String query = "SELECT * FROM test_user";
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		//전체 출력 > while문 이용
		while(rs.next()) {
			DTO user = new DTO();
			user.setUser_number(rs.getInt("user_number"));
			user.setUser_id(rs.getString("user_id"));
			user.setUser_name(rs.getString("user_name"));
			user.setUser_age(rs.getInt("user_age"));
			userList.add(user);
		}
		
		return userList;
	}//selectAllUsers()
	
	//2. user_id 검색을 통해 사용자가 있는지 확인하는 SQL문
		public static List<DTO> selectUserById(String user_id) throws SQLException{
			
			//1-1 커넥션 연결하기 위한 getConnection()
			Connection conn = JDBCTemplate.getConnection();
			
			//1-2 PrdparedStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//1-3 List로 조회된 내용을 모두 담을 수 있는 배열 생성
			List<DTO> userList = new ArrayList<>();
			
			String query = "SELECT * FROM test_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			//전체 출력 > while문 이용
			while(rs.next()) {
				DTO user = new DTO();
				user.setUser_number(rs.getInt("user_number"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_age(rs.getInt("user_age"));
				userList.add(user);
			}
			
			rs.close();
			pstmt.close();
			
			return userList;
		}//searchUsersById()
	
}