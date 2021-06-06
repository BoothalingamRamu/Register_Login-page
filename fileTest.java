package com.spring.springtest.test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import com.spring.springTest.dao.Employee;

class fileTest {

	@Test
	void test() { 
		byte[] fileContent;
		Employee em = new Employee();
		try {
			fileContent = FileUtils.readFileToByteArray(new File("C:/Users/Admin/Desktop/learningnotes/notes.txt"));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			System.out.println("upload successful"+em.fileupload("notes", encodedString));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
