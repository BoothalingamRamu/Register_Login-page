package com.spring.springTest.dao;

import java.util.List;

import com.spring.springTest.model.Emp;

public interface EmployeeDao {
	
	 public int insertEmployee(Emp emp);
	   public int UpdateEmployee(Emp emp);
		public int deleteEmployee(String empid);
		public List<Emp> listEmployee();
		

}
