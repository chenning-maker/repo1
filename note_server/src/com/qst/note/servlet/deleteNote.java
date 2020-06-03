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
		//���ñ����ʽ����ֹ��������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//����
		int id=Integer.parseInt(request.getParameter("id")); //ͨ����ַ����õĲ�������String���͵ģ�����
		
        //�����
		Result res=new Result();
		res.isSuccess=new NoteDao().DeleteNote(id);
		res.msg=res.isSuccess?"ɾ���ñ���¼�ɹ���":"ɾ���ñ���¼ʧ�ܣ�";
		
		//�����ת��Ϊ�ַ��������ظ��ͻ��ˣ�
		Gson gson=new Gson();		
		String str_result=gson.toJson(res); 
		response.getWriter().append(str_result);
	}

}
