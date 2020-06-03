package com.qst.note.util;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

//���ݿ����ӵĹ�����
public class DBUtil {

	static String dbName="note";  //��̬������ʹ�÷Ǿ�̬�����ǲ�����ģ���̬�������ڳ�������֮ǰ�ͼ��ص��ڴ��У��ȷǾ�̬�����磬���ָ���쳣����
	static String name="root";
	static String pass="root";
	
	//��ȡ���ݿ����ӣ�
	public static Connection getConnection() {
		
		Connection conn=null;
		try {
			//����mysql��Jdbc����
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			//�����ַ�����ȷ�����ĸ����Ե�Mysql��
		    conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName+"?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT", name,pass);
		
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	//�ͷ���Դ���������ڴ�й©
	public static void close(Connection conn,PreparedStatement stat,ResultSet rs) {
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	//���Է������Ƿ����ӳɹ�
//	public static void main(String[] args) {
//		
//		Connection conn=getConnection();
//		try {
//			PreparedStatement stat=conn.prepareStatement("insert into user(name,pass) values('zhangsan','123456')");
//		    stat.execute();
//		    close(conn, stat, null);
//		    
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	
	
	
}
