package com.spring.springtest.test;

import org.junit.jupiter.api.Test;

import com.spring.springTest.dao.Employee;

class Emptest4 {

	@Test
	void test() {
		Employee adm = new Employee();
	  //  System.out.println(adm.fetch("5"));
		//System.out.println(adm.deleteEmployee("3"));	
		//System.out.println(adm.fetchrole());
		//System.out.println(adm.search("jayu"));
		System.out.println(adm.login("5"));
	}
}
