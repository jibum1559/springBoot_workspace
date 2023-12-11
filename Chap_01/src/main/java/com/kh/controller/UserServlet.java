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

@WebServlet("/selectUser")
public class UserServlet extends HttpServlet {
	//doGet ->
	//전체조회 : List<DTO> userList = DAO.selectAllUsers();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//사용자로부터 입력받은 데이터 처리 및 DAO 호출 작업을 수행
		//list로 조회된 결과를 가지고 옴
		try {
			//전체조회 : List<DTO> userList = DAO.selectAllUsers();
			//아이디1개조회 : 
				//사용자가 입력한 ID를 가지고오기
			String userId =request.getParameter("userId");
			List<DTO> userList = DAO.selectUserById(userId);
			
			// 1. 비어있지 않거나 null값이 아닐 때는 전체조회
			if (userList != null && !userList.isEmpty()) {
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
				
			} else {
				// 3. 검색 결과가 없을 때
				request.getRequestDispatcher("/searchError.jsp").forward(request, response);
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

}