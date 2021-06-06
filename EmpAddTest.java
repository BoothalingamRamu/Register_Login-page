package com.spring.springtest.test;

import org.junit.Test;

import com.spring.springTest.dao.Employee;
import com.spring.springTest.model.Emp;

public class EmpAddTest {

	@Test
	public void test() {
		  Emp dto = new Emp();
		Employee adm=new Employee();
	   dto.setFirstname("Arun");
	   dto.setLastname("vijay");
	   dto.setUsername("av");
	   dto.setPassword("12345678");
	   dto.setEmail("av@gmail.com");
	   dto.setRoleid("2");
	   System.out.println(adm.insertEmployee(dto));
	}

}
