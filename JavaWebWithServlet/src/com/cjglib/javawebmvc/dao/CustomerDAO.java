package com.cjglib.javawebmvc.dao;

import java.util.List;

import com.cjglib.javaweb.domain.CriteriaCustomer;
import com.cjglib.javaweb.domain.Customer;

public interface CustomerDAO {
	
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
	
	public List<Customer> getALL();
	
	public void save(Customer customer);
	
	public Customer get(Integer id );
	
	public void delete(Integer id);
	
	public long getCountCustomer(String name);
	

}
