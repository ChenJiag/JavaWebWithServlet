package com.cjglib.javawebmvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC操作的工具类
 * @author 13402
 *
 */


public class JdbcUtils {
	
	private static DataSource dataSource = null;
	
	//必须放在静态块里面，因为这个数据源只能被初始化一次.
	static{
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	
	
	/**
	 * 返回数据源的一个Connection对象
	 * @return
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * 释放Connection的一个对象
	 * @param connection
	 */
	public static void releaseConnerction(Connection connection){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	

}
