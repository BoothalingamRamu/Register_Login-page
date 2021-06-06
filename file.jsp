<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<body>
<div align ="center">
<h1>Upload</h1>
<div>
<label for="files">Select files:</label>
<input type ="file" id="file" name ="file"/>
<input type="submit" onclick = "fileupload()">
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script>
function fileupload(){
	var file = $('#file')[0].files[0];
	var formdata = new FormData();
	formdata.append('file',file);
	$.ajax({
		url:'fileupload',
		type:"POST",
		async:false,
	    data:formdata,
	    async: false,
        contentType: false,
        processData: false,
	    success:function(result){
			var obj=JSON.parse(result);
			console.log(obj);
			if(obj.msg=="upload successful"){
				alert("succesfull");
			}
			else if(obj.msg=="fail"){
				alert("Failed");
			}
		}
	});
}
</script>
</body>
</html>
