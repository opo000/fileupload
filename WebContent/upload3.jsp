<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript">

    var httpRequest = null;
    
    function getXMLHttpRequest(){
    	if(window.ActiveXObject){
    		try{
    			return new ActiveXObject("Msxml2.XMLHTTP");
    		}catch(e){
    			return new ActiveXObject("Microsoft.XMLHTTP")
    		}
    	}else{
    		return new XMLHttpRequest();
    	}
    }

	function fnAddFile(){
	    httpRequest  = getXMLHttpRequest();
	    httpRequest.onreadystatechange = fnMessage;
	    var url = "uploadActionVer3.spr?addCnt=" + document.getElementById("addCnt").value;
	    
	    httpRequest.open("GET", url, true);
	    httpRequest.send(null);
	}
	
	function fnMessage(){
		if(httpRequest.readyState == 4){
			if(httpRequest.status == 200){
				
				document.getElementById("fileArea").innerHTML = "";
				var cnt = httpRequest.responseText;
				
				var input = "<input type='file' name='sel[]' /><br />";
				
				for(i=0;i<cnt;i++){
					document.getElementById("fileArea").innerHTML += input;
				}
				
			}else{
				alert(" Error : " + httpRequest.status);
			}
		}
	}


    function fnAddFile2(){
    	
    	$(function() {
    	    $.get("uploadActionVer2.spr?addCnt="+ $("addCnt").val(), function(data) {
    	         $(data).find("img").appendTo("#result");    
    	     });
    	    });    	
    }
    
    
</script>
</head>
<body>

	Spring 3.X File upload using Annotation & Ajax
	<p />

	
    <spring:hasBindErrors name="uploadCommand" />
    
    <!-- print log1 by using form:errors tag -->
    <!-- form:errors name="uploadCommand" />
    
	<form method="post" action="uploadActionVer1.spr" enctype="multipart/form-data">
		Title : <input type="text" name="title" /> 
		<div style="color:red"><form:errors path="uploadCommand.title" /></div>
		</p>
		Name : <input type="text" name="name" />
		<div style="color:red"><form:errors path="uploadCommand.name" /></div>
		</p>
		File : <input type="file" name="upFile" />
		<div style="color:red"><form:errors path="uploadCommand.upFile" /></div>
		</p>
		<input type="submit" value="Send" />
	</form-->
	
	
    <!-- print log2 by using form:form tag -->
    
    
    <form method="post" action="uploadActionVer3.spr" enctype="multipart/form-data">
    
    <form:form commandName="uploadCommand">
		Title : <input type="text" name="title" id="title" value="${uploadCommand.title}" /> 
		<div style="color:red"><form:errors path="title" /></div>
		</p>
		Name : <input type="text" name="name" id="name" value="${uploadCommand.name}" />
		<div style="color:red"><form:errors path="name" /></div>
		</p>
		
		Number of files : <input type="text" name="addCnt" id="addCnt" size="2" />
		<input type="button" value="confirm" onclick = "fnAddFile();"/>
		<p/>
		
		<div id="fileArea">
		
		</div>
				
		
		<input type="submit" value="Send" />
		
	</form:form>		
	
	</form>	
	
	
	
 

</body>
</html>