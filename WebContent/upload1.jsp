<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	Spring 2.5 SimpleFormController
	<p />

	<a href="springTest.spr">Spring Test</a>
    <hr />
    
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
    
    
    <form method="post" action="uploadActionVer1.spr" enctype="multipart/form-data">
    
    <form:form commandName="uploadCommand">
		Title : <input type="text" name="title" value="${uploadCommand.title}" /> 
		<div style="color:red"><form:errors path="title" /></div>
		</p>
		Name : <input type="text" name="name" value="${uploadCommand.name}" />
		<div style="color:red"><form:errors path="name" /></div>
		</p>
		File : <input type="file" name="upFile" /> ${uploadCommand.upFile.originalFilename}
		<div style="color:red"><form:errors path="upFile" /></div>
		</p>
		<input type="submit" value="Send" />
		
	</form:form>		
	
	</form>	
	
	
	
 

</body>
</html>