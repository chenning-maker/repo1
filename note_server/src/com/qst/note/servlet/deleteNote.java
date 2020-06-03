package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.dao.NoteDao;
import com.qst.note.result.Result;


@WebServlet("/deleteNote")
public class deleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public deleteNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式，防止中文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//传参
		int id=Integer.parseInt(request.getParameter("id")); //通过地址栏获得的参数都是String类型的！！！
		
        //结果类
		Result res=new Result();
		res.isSuccess=new NoteDao().DeleteNote(id);
		res.msg=res.isSuccess?"删除该备忘录成功！":"删除该备忘录失败！";
		
		//将结果转化为字符串，返回给客户端：
		Gson gson=new Gson();		
		String str_result=gson.toJson(res); 
		response.getWriter().append(str_result);
	}

}
