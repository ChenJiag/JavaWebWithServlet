package com.cjglib.javawebmvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC�����Ĺ�����
 * @author 13402
 *
 */


public class JdbcUtils {
	
	private static DataSource dataSource = null;
	
	//������ھ�̬�����棬��Ϊ�������Դֻ�ܱ���ʼ��һ��.
	static{
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	
	
	/**
	 * ��������Դ��һ��Connection����
	 * @return
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * �ͷ�Connection��һ������
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
