package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.qst.note.bean.NoteBean;
import com.qst.note.util.DBUtil;

public class NoteDao {

//1.新建一条备忘录：
public Boolean CreateNote(String tel,String title,String content,String note_time){
	
	int id=new UserDao().getIdByTel(tel);
	if(id<1){
		return false;
	}
	
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
	String nowTime=format.format(new Date());
	
	
	
	Connection conn=DBUtil.getConnection();
	PreparedStatement ps=null;
	
	try {
		//预编译：安全高效，可用占位符（在运行之前编译好）
		ps=conn.prepareStatement("insert into note_table(title,content,create_time,note_time,user_id) "
				+ "values(?,?,?,?,?)");
	   
	    ps.setString(1,title);
	    ps.setString(2,content);
	    ps.setString(3,nowTime);
	    ps.setString(4,note_time);
	    ps.setInt(5,id);
	    
	    ps.execute();
	    
	    
	    return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}finally {
		DBUtil.close(conn, ps, null);
	}
	
	
}	
	
//2,根据id查询一条备忘录数据:
public NoteBean getNodeById(int id){
	
	Connection conn=DBUtil.getConnection();
	PreparedStatement ps=null;
	ResultSet rs=null;
	NoteBean note=new NoteBean();
	
	try {
		//预编译：安全高效，可用占位符（在运行之前编译好）
		ps=conn.prepareStatement("select * from note_table where id=? ");
	   
	    ps.setInt(1,id); //从1开始计算
	    rs=ps.executeQuery();
	    if(rs.first()){
	    	
	    	note.setId(id);
	    	note.setTitle(rs.getString("title"));
	    	note.setContent(rs.getString("content"));
	    	note.setNote_time(rs.getString("note_time"));
	    	note.setUpdate_time(rs.getString("update_time"));
	    	note.setCreate_time(rs.getString("create_time"));
	    	note.setUser_id(rs.getInt("user_id"));
        
	    	
	    }
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}finally {
		DBUtil.close(conn, ps, rs);
	}
	
	return note;
	
}


//3.根据id更新修改一条备忘录：
public Boolean UpdateNote(int id,String content,String title,String note_time){
	if(id<1)
		return false;
	
	Connection conn=DBUtil.getConnection();
	
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
	String nowTime=format.format(new Date());
	
	try {
		//预编译：安全高效，可用占位符（在运行之前编译好）
		PreparedStatement ps=conn.prepareStatement("update note_table set content=?,update_time=?,title=?,note_time=? where id=?");
	    
	    ps.setString(1,content);
	    ps.setString(2,nowTime);
	    ps.setString(3,title);
	    ps.setString(4,note_time); 
	    ps.setInt(5,id);
	    ps.execute();
	    DBUtil.close(conn, ps, null);
	    
	    return true;
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;//如果出异常，就插入失败
	}
	
	
}	
	
	

//4.根据id删除一条备忘录：
public Boolean DeleteNote(int id){
	
	if(id<1)
		return false;
	
	Connection conn=DBUtil.getConnection();
	PreparedStatement ps=null;
	
	try {
		//预编译：安全高效，可用占位符（在运行之前编译好）
		ps=conn.prepareStatement("delete from note_table where id=?");
	    
	    ps.setInt(1, id);
	    
	    ps.execute();
	    
	    DBUtil.close(conn, ps, null);
	    
	    return true;
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;//如果出异常，就插入失败
	}
	
	
}	
	


//5.获取指定用户的所有的备忘录：
public ArrayList<NoteBean> GetAllNotes(String tel){
	
	int user_id=new UserDao().getIdByTel(tel);
	ArrayList<NoteBean> notes=null;
	
	Connection conn=DBUtil.getConnection();
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	try {
		//预编译：安全高效，可用占位符（在运行之前编译好）
		ps=conn.prepareStatement("select * from note_table where user_id=?");
	    
	    ps.setInt(1, user_id);
	    rs=ps.executeQuery();
	    notes=new ArrayList<>();
	    while(rs.next()){
	    	
	    	NoteBean note=new NoteBean();
	    	note.setId(rs.getInt("id"));
	    	note.setTitle(rs.getString("title"));
	    	note.setContent(rs.getString("content"));
	    	note.setUpdate_time(rs.getString("update_time"));
	    	note.setCreate_time(rs.getString("create_time"));
	    	note.setNote_time(rs.getString("note_time"));
	    	note.setUser_id(rs.getInt("user_id"));
	    	
	    	notes.add(note);
	    	
	    }
	    return notes;
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return notes;//如果出异常，就插入失败
	}finally {
		 DBUtil.close(conn, ps, rs);
	}
	
	
}	
	
}
