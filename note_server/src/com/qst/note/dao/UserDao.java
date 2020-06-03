package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

//有几张表就有几个dao文件
//具体的业务逻辑（对数据库的操作）
public class UserDao {

	
	
	//1,注册接口：
	//注册用户名：（向User表中插入一条数据）
	public Boolean regist(UserBean user){
		
		Connection conn=DBUtil.getConnection();
		
		try {
			//预编译：安0全高效，占位符（在运行之前编译好）
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
			return false;//如果出异常，就插入失败
		}
		
		
	}
	
	
//2,登陆用户：
	public Boolean login(String tel,String pass){
		
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet  rs=null;
        
		try {
		    ps = conn.prepareStatement("select pass from user where tel=?");
	        ps.setString(1, tel);
	        
	        //返回 查询结果集
	       rs=ps.executeQuery();
	       if(rs.first()){
				//数据库存储的密码：
				String pass_db=rs.getString("pass");
				if(pass.equals(pass_db))		
					
				//System.out.println("登陆成功！");
				
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
	
//用老师的这种方式很简洁。	
	

	
//3,注销用户名：
	
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
	

	//4,通过tel找到user表中的id赋给note_table中的user_id:
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
