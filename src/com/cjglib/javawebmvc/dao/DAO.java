package com.cjglib.javawebmvc.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.runners.Parameterized;

import com.cjglib.javawebmvc.db.JdbcUtils;
import com.sun.glass.ui.CommonDialogs.Type;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 该DAO封装了CRDU（增删改查）的方法
 * @Param<T> 为该DAO层处理的类型
 */
public class DAO<T> {

	private Class<T> clazz;

	private QueryRunner queryRunner = new QueryRunner(); // dbutils

	public DAO() {
		java.lang.reflect.Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			java.lang.reflect.Type[] typeArgs = parameterizedType.getActualTypeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}

			}
		}

	}

	/**
	 * 返回对应的T的一个实体对象
	 *   
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnerction(connection);
		}
		return null;
	}

	/**
	 * 返回对应的T的一个List集合
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnerction(connection);
		}
		return null;
		
	}

	/**
	 * 该方法封装了INSERT,DELETE,Update
	 * 
	 * @Param:sql-> SQL语句
	 * @Param:args->同于填充SQL语句的占位符
	 */
	public void update(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnerction(connection);
		}

	}

	/**
	 * 返回某一个字段的值
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnerction(connection);
		}
		return null;
	}

}
