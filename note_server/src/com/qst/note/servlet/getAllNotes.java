package com.qst.note.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

 
@WebServlet("/getAllNotes")
public class getAllNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public getAllNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        
		        //���ñ����ʽ����ֹ��������
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//����
				String tel=request.getParameter("tel");
				
		        //�����
				ArrayList<NoteBean> notes=new ArrayList<>();
				notes=new NoteDao().GetAllNotes(tel);
				
				//�����ת��Ϊ�ַ��������ظ��ͻ��ˣ�
				Gson gson=new Gson();		
				String str_result=gson.toJson(notes); 
				response.getWriter().append(str_result);
			}
	}


