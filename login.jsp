<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<body>
<div align ="center">
<h1>Login Page</h1>
<form id="loginValidation">
<div>
<label for="username">username:</label>
<input type ="text" id="username" name ="username"/>
</div>
<div>
<label for="password">password:</label>
<input type ="text" id="password" name ="password"/>
</div>
<div class="col-sm-12">
<button type="submit" class="btn btn-success">Login</button>
</div>
</form>
<a href="register"><button type="submit" class="btn btn-success">Register</button></a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
<script>
function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	$.ajax({
		url:'login',
		type:"POST",
		async:false,
		data:{
			username:username,
			password:password
		},
		success:function(response){
			var obj=JSON.parse(response);
			console.log(obj);
			if(obj.msg=="login successful"){
				alert("succesfull");
				window.location.href=("employeedetails");
			}
			else if(obj.msg=="fail"){
				alert("Failed");
			}
		}
	});
}
$("#loginValidation").validate({
	rules:{
		username:"required",
		password:{
			required:true,
			minlength:6
		},
	},
		messages:{
			username:"please enter username",
			password:{
				required:"please enter password",
				minlength:"password must be atleast 6 characters"},
		},
		submitHandler:function(){
			login();
			
	}
});
</script>
</body>
</html>