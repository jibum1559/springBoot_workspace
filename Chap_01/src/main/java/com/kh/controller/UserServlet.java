package com.kh.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.dao.DAO;
import com.kh.model.vo.DTO;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자로부터 입력 받은 데이터 처리 및 DAO 호출 작업을 수행
		
		try {
			
			List<DTO> userList = null;
			String userId = request.getParameter("userId");
			
			//빈값 검색 > 전체 조회 결과
			if(userId == null || userId.isEmpty()) {
				
				userList = DAO.selectAllUsers();
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
				
			} else {
				//else : 검색 키워드가 존재할 경우
				
				userList = DAO.searchUsersById(request.getParameter("userId"));
				
				//일치하는 값이 존재할 경우
				if(userList != null && !userList.isEmpty()) {
					request.setAttribute("userList", userList);
					request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
					
				} else {
					//검색 키워드와 일치하는 결과가 없을 때
					request.getRequestDispatcher("/searchError.jsp").forward(request, response);
					//response.sendRedirect(request.getContextPath() + "/searchError.jsp");
				}
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}