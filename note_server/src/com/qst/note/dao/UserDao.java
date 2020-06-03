package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

//�м��ű���м���dao�ļ�
//�����ҵ���߼��������ݿ�Ĳ�����
public class UserDao {

	
	
	//1,ע��ӿڣ�
	//ע���û���������User���в���һ�����ݣ�
	public Boolean regist(UserBean user){
		
		Connection conn=DBUtil.getConnection();
		
		try {
			//Ԥ���룺��0ȫ��Ч��ռλ����������֮ǰ����ã�
			PreparedStatement ps=conn.prepareStatement("insert into user(name,pass,tel,qq,wechat) values(?,?,?,?,?)");
		 
		    ps.setString(1,user.getName());
		    ps.setString(2,user.getPass());
		    ps.setString(3,user.getTel());
		    ps.setString(4,user.getQq());
		    ps.setString(5,user.getWechat());
		
		    ps.execute();
		    
		    DBUtil.close(conn, ps, null);
		    
		    return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;//������쳣���Ͳ���ʧ��
		}
		
		
	}
	
	
//2,��½�û���
	public Boolean login(String tel,String pass){
		
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet  rs=null;
        
		try {
		    ps = conn.prepareStatement("select pass from user where tel=?");
	        ps.setString(1, tel);
	        
	        //���� ��ѯ�����
	       rs=ps.executeQuery();
	       if(rs.first()){
				//���ݿ�洢�����룺
				String pass_db=rs.getString("pass");
				if(pass.equals(pass_db))		
					
				//System.out.println("��½�ɹ���");
				
				return true;			        			
			}	        
	   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
						
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		 
		
		return false;
			
	}
	
//����ʦ�����ַ�ʽ�ܼ�ࡣ	
	

	
//3,ע���û�����
	
	public Boolean deleteName(UserBean user){
		
		 Connection conn=DBUtil.getConnection();
			
			try {
				PreparedStatement ps=conn.prepareStatement("delete from user where name=?");
				ps.setString(1, user.getName());
				
		        Boolean result=ps.execute();
		        return result;
		        
		        
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
					
					
				}
		
		
	}
	

	//4,ͨ��tel�ҵ�user���е�id����note_table�е�user_id:
	public int getIdByTel(String tel){
		
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id=0;
		
		try {
			
		    ps= conn.prepareStatement("select id from user where tel=?");
		    ps.setString(1, tel);
		    
		    rs=ps.executeQuery();
			if(rs.first()){
				
				id=rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return id;
			
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		
		return id;
	               }
	
	
}
