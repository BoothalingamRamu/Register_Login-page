<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listed Page</title>
</head>
<body>
<div align = "center">
<input type="text" id="username" name="search" style="float: center; padding:3px;">
<button type="button" class="btn-btn-success" onclick="search()">search</button>
<table  width="1000px" border="1px" cellpadding="5">
<tr>
<th>Firstname</th>
<th>Lastname</th>
<th>Username</th>
<th>Email</th>
<th>Role</th>
<th>Action</th>
</tr>
    <tbody id = "example">
    <c:forEach var="emp" items="${employee}">
    <tr>
        <td>${emp.firstname}</td>
        <td>${emp.lastname}</td>
        <td>${emp.username}</td>
        <td>${emp.email}</td>
        <td>${emp.role}</td>
        <td><a href="register?empid=<c:out value='${emp.empid}'/>"><button class = "btn btn-success">Edit</button></a> 
         <span onclick="deleteEmployee('${emp.empid}')"><button type ="submit" class="btn btn-success">Delete</button></span></td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
 $(document).ready(function() {
    var table = $('#example').DataTable({     
    });
});    
    function deleteEmployee(empid){
    $.ajax({
        url: 'deleteEmployee',
        type: "POST",
        async: false,
        data: {
            empid:empid
        },
        success: function(result){
            var obj= JSON.parse(result);
            console.log(obj);
            if(obj.msg == "Success"){
                alert("Removed");
            }
            else {
                if(obj.msg=="Fail")
            
                alert("Failed");
            }
            }
    
        });
    }
    
    function search(){
        var username=$('#username').val();
        $.ajax({
            url:'search',
            type:"POST",
            async:true,
            data:{ 
                username:username
            },
            success: function(response){
                var obj=JSON.parse(response);
                console.log(obj);
                $('#example').html('');
                var table = '';
                for(var i=0;i<obj.searchlist.length;i++){
                    table+='<tr><td>'+obj.searchlist[i].firstname+'</td><td>'+obj.searchlist[i].lastname+'</td><td>'+obj.searchlist[i].username+'</td><td>'+obj.searchlist[i].email+'</td><td>'+obj.searchlist[i].role+'</td><td><a href="register?empid='+obj.searchlist[i].empid+'"><button class = "btn btn-success">Edit</button></a><a href="" onclick="deleteEmployee('+obj.searchlist[i].empid+')"><button type ="submit" class="btn btn-success">Delete</button></a></td></tr>';      
                   
                }
                $('#example').append(table);
                }
            });
    
    }
</script>
</body>
</html>