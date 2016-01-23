<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de produtos</title>
</head>
<body>
	
	<!-- To uncouple the url of the method, we can use the "mvcUrl" tag -->
	<!-- whit this the method can be access without the explict url be showing -->
	<!-- the url is assembled by the "build" -->
	<!-- "PC#save" ... PC = ProductController || "save" is the method -->
	<!-- <form method = "post" action="/SpringMvc/produtos">  -->
	<!-- Essa função recebe como parâmetro as letras em maiúsculo do seu controller -->
	<!-- e o nome do método, separados pelo #. O método build() serve -->
	<!--	para realizar a construção da URL -->	 
	
		
	<!-- <form method = "post" action="/SpringMvc/products"> -->
		<form:form action= "${spring:mvcUrl('PC#save').build()}" method="post" commandName="product">
			<div>
				<label for = "title">Título</label>
				<!-- <input type = "text" name="title" id = "title"/>  -->	
				<form:input path="title"/>
				<form:errors path="title"/> <!-- How the form is inside the hasBindErrros we can do that -->					
			</div>
			<div>
				<label for = "description">Descrição</label>
				<!-- <textarea rows = "10" cols = "20" name="description" id= "description"/>-->
				<form:textarea path="description" rows="10" cols="20"/>		
			</div>
				<!-- The path atribute is the link with the model -->
				<!-- To the tag "form" work we need pass a object like parameter of form -->
				<!-- The attribute 'path' will search the value in the getter of the class defined in commandName -->
				<!-- Actually the commandName will search the name of the attribute in the controller (Ex: Product product, ABC product) -->
			<div>
				<label for="pages">Número de páginas</label>
				<input type = "text" name = "pages" id = "pages"/>
				<form:errors path="pages"/>			
			</div>
			
			<div>
				<label for="releaseDate">Data de lançamento</label>
				<input type="date" name="releaseDate"/>
				<form:errors path="releaseDate"/>
			</div>
			
			<c:forEach items="${types}" var ="bookType" varStatus = "status">		
			<div>			
				<label for="price_${bookType}">${bookType}</label>
				<input type = "text" name = "prices[${status.index}].value" id="price_${bookType}"/>
				<input type = "hidden" name = "prices[${status.index}].bookType" value= "${bookType}" />
			</div>
			</c:forEach>
		
			
			<!-- <c:forEach 	items="${requestScope['org.springframework.validation.BindingResult.product'].allErrors}" 
						var="error">
				${error.field} - ${error.code}
				<br/>
			</c:forEach> -->			
				
			<div>
				<input type = "submit" value = "Enviar">
			</div>
		</form:form>		
</body>
</html>