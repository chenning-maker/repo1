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


@WebServlet("/UpdateNote")
public class UpdateNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateNote() {
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
				int id=Integer.parseInt(request.getParameter("id"));
				String content=request.getParameter("content");
				String title=request.getParameter("title");
				String note_time=request.getParameter("note_time");
		      
		        //�����
				Result res=new Result();
				res.isSuccess=new NoteDao().UpdateNote(id,content,title,note_time);
				res.msg=res.isSuccess?"�޸ı���¼�ɹ���":"�޸ı���¼ʧ�ܣ�";
				
				//�����ת��Ϊ�ַ��������ظ��ͻ��ˣ�
				Gson gson=new Gson();		
				String str_result=gson.toJson(res); 
				response.getWriter().append(str_result);
	}

}
