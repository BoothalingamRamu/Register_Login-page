package com.spring.springtest.test;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.spring.springTest.dao.Employee;

class login {

	@Test
	void test() {
		Employee emp = new Employee();
		JSONObject json = new JSONObject();
		json.put("username","mk");
		json.put("password","123456");
		System.out.println("msg"+emp.login(json));
	}

}
