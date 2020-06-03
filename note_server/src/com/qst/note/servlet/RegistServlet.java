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


@WebServlet("/Regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserBean user=new UserBean();
		//ͨ����ַ���ķ�ʽ���ݣ�
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		user.setTel(request.getParameter("tel"));
	   
		UserDao dao=new UserDao();
		Boolean b=dao.regist(user);
	    
		Result result=new Result();
		result.isSuccess=b;
		if(b){
			
			result.msg="��ϲ����ע��ɹ�!";
		}else {
			
			result.msg="ע��ʧ�ܣ����ֻ����ѱ�ע�ᣡ";
		}
		
		//��Result����ת���ɷ���json��ʽ���ַ������ظ��ͻ���
		Gson gson=new Gson();
		//��������java����ת����json�ַ���
		String str_result=gson.toJson(result); 
		//��Ӧ���ͻ���
		response.getWriter().append(str_result);
		
	    
	}

}
