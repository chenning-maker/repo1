package com.qst.note.util;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

//数据库连接的工具类
public class DBUtil {

	static String dbName="note";  //静态方法中使用非静态变量是不允许的，静态方法是在程序运行之前就加载到内存中，比非静态变量早，会空指针异常！！
	static String name="root";
	static String pass="root";
	
	//获取数据库连接：
	public static Connection getConnection() {
		
		Connection conn=null;
		try {
			//加载mysql的Jdbc驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			//连接字符串：确定是哪个电脑的Mysql：
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
	
	//释放资源，否则导致内存泄漏
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
	
	//测试方法，是否连接成功
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
