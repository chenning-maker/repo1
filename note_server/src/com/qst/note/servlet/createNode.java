package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.dao.NoteDao;
import com.qst.note.dao.UserDao;
import com.qst.note.result.Result;


@WebServlet("/createNode")
public class createNode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public createNode() {
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
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String note_time=request.getParameter("note_time");
		
		NoteDao dao=new NoteDao();
    	Result result=new Result();
		result.isSuccess=dao.CreateNote(tel, title, content, note_time);
			
		
		result.msg=result.isSuccess?"恭喜您，录入备忘录成功!":"录入失败，不存在该用户！";
		
		
		//将Result对象转化成符合json格式的字符串返回给客户端
		Gson gson=new Gson();
		//解析：将java对象转化成json字符串
		String str_result=gson.toJson(result); 
		//响应给客户端
		response.getWriter().append(str_result);
		
	    
	}

}
