package com.cjglib.javawebmvc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.cjglib.javaweb.daoimlp.CustomerDAOJdbcImlp;
import com.cjglib.javaweb.domain.CriteriaCustomer;
import com.cjglib.javaweb.domain.Customer;
import com.cjglib.javawebmvc.dao.CustomerDAO;

public class CustomerDAOJdbcImlpTest {
	private CustomerDAO customerDAO = new CustomerDAOJdbcImlp();

	/*@Test
	
	public void testGetALL() {
		List<Customer> customers = customerDAO.getALL();
		System.out.println(customers);
	}
	*/
	@Test
	public void testGetForListWithCriteriaCustomer(){
		CriteriaCustomer ccCriteriaCustomer = new CriteriaCustomer("a", null, null);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(ccCriteriaCustomer);
		System.out.println(customers);
	}

/*	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setName("MIKE");
		customer.setAddress("hangzhou");
		customer.setPhone("1223312331");
	    customerDAO.save(customer);
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(1);
		System.out.println(cust);
	}

	@Test
	public void testDelete() {
		customerDAO.delete(1);
	}

	@Test
	public void testGetCountCustomer() {
		long count = customerDAO.getCountCustomer("ABV");
		System.out.println(count);
	}*/

}
