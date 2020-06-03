package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;


@WebServlet("/getNode")
public class getNode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public getNode() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//以地址栏的方式传参
		doPost(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id=Integer.parseInt(request.getParameter("id"));
        
		NoteBean note=new NoteDao().getNodeById(id);
		
		Gson gson=new Gson();		
		String str_result=gson.toJson(note); 
		response.getWriter().append(str_result);
		
		
	}

}
