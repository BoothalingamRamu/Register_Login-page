package com.spring.springtest.test;

import java.util.List;



import com.spring.springTest.dao.Employee;
import com.spring.springTest.model.Emp;

class Emptest3 {

	void test() {

		Employee list= new Employee();
		
		List<Emp> listEmployee =list.listEmployee();
      
        System.out.println("list"+listEmployee.size());
        for(int i =0;i<listEmployee.size();i++) {
        System.out.println(listEmployee.get(i).getEmpid());
		System.out.println(listEmployee.get(i).getFirstname());
		System.out.println(listEmployee.get(i).getLastname());
		System.out.println(listEmployee.get(i).getUsername());
		System.out.println(listEmployee.get(i).getPassword());
		System.out.println(listEmployee.get(i).getEmail());
		System.out.println(listEmployee.get(i).getRoleid());
		
}}
    
    
    
    	 
    }
    	 
     