package com.springTest.Controller;

import java.util.ArrayList;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.springTest.dao.Employee;
import com.spring.springTest.model.Emp;

@Controller
public class UserController {
	

	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String loginvendor() {
		return "register";
	}
	
	@RequestMapping(value = "/addEmployee",method=RequestMethod.POST)
	@ResponseBody
    public String employeedetails( @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname, @RequestParam("username") String username,
            @RequestParam("password") String password, @RequestParam("email") String email,@RequestParam("roleid") String roleid,
             HttpServletRequest request,HttpServletResponse response) {
	//	ModelAndView mv= new ModelAndView();
      Employee em=  new Employee();
      Emp emp = new Emp();
    emp.setFirstname(firstname);
  	emp.setLastname(lastname);
  	emp.setUsername(username);
  	emp.setPassword(password);
  	emp.setEmail(email);
  	emp.setRoleid(roleid);
    int counter=  em.insertEmployee(emp);
    JSONObject json = new JSONObject();
 	if(counter>0)
 	{
 		json.put("msg", "success");
 		
 	}
 	else
 	{
 		json.put("msg", "fail");
 	}
 	return json.toString();
 	
 }

@RequestMapping(value="/employeedetails", method=RequestMethod.GET)
 
	
    public ModelAndView employeedetails() {
        Employee model=new Employee();
        List<Emp> listEmployee =model.listEmployee();
        List<Emp> list=new ArrayList<Emp>();
        for(int i=0;i<listEmployee.size();i++) {
         Emp emp=new Emp();
        emp.setEmpid(listEmployee.get(i).getEmpid());
        emp.setFirstname(listEmployee.get(i).getFirstname());
        emp.setLastname(listEmployee.get(i).getLastname());
        emp.setUsername(listEmployee.get(i).getUsername());
        emp.setPassword(listEmployee.get(i).getPassword());
        emp.setEmail(listEmployee.get(i).getEmail());
        emp.setRole(listEmployee.get(i).getRole());
        list.add(emp);
        }
        return new ModelAndView("employeedetails","employee",list);
}
@RequestMapping(value = "/fetch",method=RequestMethod.POST)  //fetching datas from employeedetails page to update datas in exsisting data
@ResponseBody
public String fetch(@RequestParam("empid") String empid ,HttpServletRequest request,HttpServletResponse response)
 {
	Employee emm=  new Employee();
	Emp emp = emm.userById(empid);
    JSONObject json =new JSONObject();
    try {
    	json.put("firstname",emp.getFirstname());
    	json.put("lastname",emp.getLastname());
    	json.put("username",emp.getUsername());
    	json.put("password",emp.getPassword());
    	json.put("email",emp.getEmail());
    	json.put("role",emp.getRole());
    	//json.put( "empid", emp.getEmpid());   	
    	System.out.println("json"+json);
    	
    }catch(Exception e) {
            e.printStackTrace();
    }
           return json.toString();
}

@RequestMapping(value = "/deleteEmployee",method=RequestMethod.POST)
@ResponseBody
public String delete(@RequestParam("empid") String empid ,HttpServletRequest request,HttpServletResponse response)
 {
	Employee em=  new Employee();
    JSONObject json =new JSONObject();
   int counter= em.deleteEmployee(empid);
   
	if(counter>0) {
    	  json.put("msg", "Success");
    	  
      }else {
    	 json.put("msg","failed");
      }
	return json.toString();
      
}
@RequestMapping(value = "/updateEmployee",method=RequestMethod.POST)
@ResponseBody
public String updateEmployee(  @RequestParam("firstname") String firstname,
        @RequestParam("lastname") String lastname, @RequestParam("username") String username,
        @RequestParam("password") String password, @RequestParam("email") String email,@RequestParam("roleid") String roleid, @RequestParam("empid") String empid,
         HttpServletRequest request) {
	Employee em = new Employee();
	Emp emp = new Emp();
	
	emp.setFirstname(firstname);
	emp.setLastname(lastname);
	emp.setUsername(username);
	emp.setPassword(password);
	emp.setEmail(email);
	emp.setRoleid(roleid);
	emp.setEmpid(empid);
	System.out.println("role"+roleid);
	JSONObject json = new JSONObject();
	int counter = em.UpdateEmployee(emp);
	if(counter>0)
	{
		json.put("msg", "success");
	}
	else
	{
		json.put("msg", "fail");
	}
	return json.toString();
}


@RequestMapping(value = "/fetchRole",method=RequestMethod.POST)
@ResponseBody
public String fetchEmployee( HttpServletRequest request,HttpServletResponse response) {
	
	Employee em = new Employee();
	JSONObject json = em.fetchrole();
	return json.toString();
	
}

@RequestMapping(value = "/search",method=RequestMethod.POST)
@ResponseBody
public String search(@RequestParam("username") String username, HttpServletRequest  request,HttpServletResponse response) {
	Employee em = new Employee();
	JSONObject json = em.search(username);
	return json.toString();
	
}

	
@RequestMapping(value="/login", method=RequestMethod.GET)
public String loginpage() {
	return "login";
}	

@RequestMapping(value = "/login",method=RequestMethod.POST)
@ResponseBody
public String login(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest  request,HttpServletResponse response) {
	
	 Employee em=  new Employee();
     JSONObject json = new JSONObject();
     json.put("username", username);
     json.put("password", password);
    JSONObject jsonobj = em.login(json);
    return jsonobj.toString();
    
}

@RequestMapping(value="/file", method=RequestMethod.GET)
public String filepage() {
	return "file";
}
	
@RequestMapping(value = "/fileupload",method=RequestMethod.POST)
@ResponseBody
public String file(HttpServletRequest request,   HttpSession session) throws Exception{  
    Employee em = new Employee();
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    MultipartFile file = multipartRequest.getFile("file");
	String filename = file.getOriginalFilename();  
	String encodedString = Base64.getEncoder().encodeToString(file.getBytes());
	JSONObject jsonobj = em.fileupload(filename,encodedString);
	return jsonobj.toString();

}}
	








	

