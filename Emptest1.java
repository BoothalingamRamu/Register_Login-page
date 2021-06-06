package com.spring.springtest.test;



import org.junit.Test;

import com.spring.springTest.dao.Employee;
import com.spring.springTest.model.Emp;


public class Emptest1  {

	@Test
	public void test() {
		
		Employee adm=new Employee();
        Emp dto=new Emp();
        dto.setEmpid("2");
        dto.setFirstname("sunthari");
        dto.setLastname("kc");
        dto.setUsername("sunthari");
        dto.setPassword("sunthari!@#");
        dto.setEmail("sunthari123@gmail.com");
        dto.setRoleid("2");
      
              adm.UpdateEmployee(dto);
	}

}

