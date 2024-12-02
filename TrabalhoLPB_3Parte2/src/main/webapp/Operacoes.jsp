<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Form.css">
<meta charset="ISO-8859-1">
<title>Opera��es</title>
</head>
<body>
	<div class="form-container">
		<form action="ServletController" method="get">
			<h2>Opera��es</h2>
			<input type="hidden" name="cpf2" value="${param.cpf}">
			<input type="radio" id="1" value="1" name="metodo"><label
				class="radio-label" for="1"> Salario Anual</label> <input
				type="radio" id="2" value="2" name="metodo"><label
				class="radio-label" for="2"> Salario p�s Imposto</label> <input
				type="radio" id="3" value="3" name="metodo"><label
				class="radio-label" for="3"> Ver Aposentadoria</label> <input
				type="radio" id="4" value="4" name="metodo"><label
				class="radio-label" for="4"> Ver Salario de Aposentado</label> 
				<input type="Submit" value="Finalizar" name="operacao" class="submit-button">
		</form>
		<a href="./index.jsp">P�gina Inicial</a>
	</div>
</body>
</html>