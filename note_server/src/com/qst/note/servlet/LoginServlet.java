package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.qst.note.bean.UserBean;
import com.qst.note.dao.UserDao;
import com.qst.note.result.Result;

//每一servlet都要配置一个请求地址 ：使用注解的方式：
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//通过地址栏的方式传递：
		String tel=request.getParameter("tel");
		String pass=request.getParameter("pass");
		
		UserDao dao=new UserDao();
		
    	Result result=new Result();
		result.isSuccess=dao.login(tel, pass);
			
		//使用三元运算符：代码更加简洁
		result.msg=result.isSuccess?"恭喜您，登陆成功!":"登陆失败，该手机号或密码不正确！";
		
		
		
		//将Result对象转化成符合json格式的字符串返回给客户端
		Gson gson=new Gson();
		//解析：将java对象转化成json字符串
		String str_result=gson.toJson(result); 
		//响应给客户端
		response.getWriter().append(str_result);
		
	    
	}

}
