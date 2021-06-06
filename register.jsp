<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<body>
<div align ="center">
<h1>Emplyoee register form</h1>
<form id="Employeeregistration">
<div>
<label for="firstname">FirstName:</label>
<input type ="text" id=firstname name ="firstname"/>
</div>
<div>
<label for="lastname">LastName:</label>
<input type ="text" id =lastname name ="lastname"/>
</div>
<div>
<label for="username">UserName:</label>
<input type ="text" id =username name ="username"/>
</div>
<div>
<label for="password">password:</label>
<input type ="password" id =password  name ="password"/>
</div>
<div>
<label for="email">Email:</label>
<input type ="text" id =email name ="email"/>
</div>
<div>
<label for="role">Role:</label>
<select name="role" id="role"></select>
</div>

<div class="col-sm-12">
<button type="submit" id="addbutton" class="btn btn-success">Add</button>
<button type="submit" id="updatebutton" class="btn btn-success" >update</button>

</div>
</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
 
<script >

$(document).ready(function(){
	fetchrole();
	$('#addbutton').show();
	$('#updatebutton').hide();
	var empid=<%=request.getParameter("empid")%>
	if(empid!= null){
		$('#updatebutton').show();
		$('#addbutton').hide();
		fetch(empid);
	
		}
	alert(empid);
		
	});
	

 function fetch(empid){
		$.ajax({
			url:'fetch',
			type:"POST",
			async:false,
			data:{
				empid:empid
			},
			success:function(result){
				var obj=JSON.parse(result);
				console.log(obj);
				//var img='<img src='+obj.image+'" width="500" height="600">'
				//$('#image').html(img);
				$('#firstname').val(obj.firstname);
				$('#lastname').val(obj.lastname);
				$('#username').val(obj.username);
				$('#password').val(obj.password);
				$('#email').val(obj.email);
				$('#role').val(obj.role);
			}
		});
	}
		
			function updateEmployee(){
				var empid=<%=request.getParameter("empid")%>
				var firstname = $('#firstname').val();
				var lastname = $('#lastname').val();
				var username= $('#username').val();
				var password= $('#password').val();
				var email= $('#email').val();
				var roleid = $('#role').val();
				$.ajax({
					url:'updateEmployee',
					type:"POST",
					async:false,
					data:{
					firstname:firstname,
					lastname:lastname,
					username:username,
					password:password,
					email:email,
					roleid:roleid,
					empid:empid
				},
				success: function(result){
					var obj=JSON.parse(result);
					console.log(obj);
					if(obj.msg == "success"){
						alert("Updated Successfully");
					location.href="employeedetails";
					}
					if(obj.msg == "update failed"){
						alert("Updated failed");													
					}
					
				}
				
				});
			}
function addEmployee(){
	
	var firstname = $('#firstname').val();
	var lastname = $('#lastname').val();
	var username= $('#username').val();
	var password= $('#password').val();
	var email= $('#email').val();
	var roleid=$('#role').val();
	$.ajax({
		url:'addEmployee',
		type:"POST",
		async:false,
		data:{
		firstname:firstname,
		lastname:lastname,
		username:username,
		password:password,
		email:email,
		roleid:roleid
	},
	 success:function(result){
			var obj=JSON.parse(result);
			console.log(obj);
			if(obj.msg=="success"){
				alert("succesfull");
				location.href="employeedetails";
			}
			else if(obj.msg=="fail"){
				alert("Failed");
			}
		}
	});
}
function fetchrole(){
	$.ajax({
		url:'fetchRole',
		type:"POST",
		async:false,
		data:{ },
		success:function(result){
			var obj=JSON.parse(result);
			console.log(obj.rolelist);
			var roleDD = '<option></option>';
			for(var i = 0;i<obj.rolelist.length;i++){
				roleDD +='<option value='+ obj.rolelist[i].id+'>'+obj.rolelist[i].role+'</option>';
			}
			$('#role').html(roleDD);
			}
	});
}
$('#Employeeregistration').validate({
	rules:{
		firstname:"required",
		lastname:"required",
		username:"required",
		password:{
			required:true,
			minlength:6
		},
		email:{
			required:true,
			email:true
		},
		role:"required"
	},
	messages:{
	firstname:"please enter firstname",
	lastname:"please enter lastname",
	username:"please enter username",
	password:{required:"please enter password",
		      minlength:"password must be atleast 6 characters"},
	email:"please enter email",    
	role:"please select role"
	
	},
	submitHandler: function(form){  
		var empid = <%= request.getParameter("empid") %>;
		if(empid != null){
			updateEmployee();
		}
		else{
			addEmployee();
		}
		
	}
});
</script>
</body>
</html>



