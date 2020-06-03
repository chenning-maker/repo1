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

//ÿһservlet��Ҫ����һ�������ַ ��ʹ��ע��ķ�ʽ��
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
		
		//ͨ����ַ���ķ�ʽ���ݣ�
		String tel=request.getParameter("tel");
		String pass=request.getParameter("pass");
		
		UserDao dao=new UserDao();
		
    	Result result=new Result();
		result.isSuccess=dao.login(tel, pass);
			
		//ʹ����Ԫ�������������Ӽ��
		result.msg=result.isSuccess?"��ϲ������½�ɹ�!":"��½ʧ�ܣ����ֻ��Ż����벻��ȷ��";
		
		
		
		//��Result����ת���ɷ���json��ʽ���ַ������ظ��ͻ���
		Gson gson=new Gson();
		//��������java����ת����json�ַ���
		String str_result=gson.toJson(result); 
		//��Ӧ���ͻ���
		response.getWriter().append(str_result);
		
	    
	}

}
