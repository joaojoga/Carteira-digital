<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="jakarta.servlet.http.Cookie, jakarta.servlet.RequestDispatcher, Model.Administrador,java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página Inicial</title>
<link rel="stylesheet" href="./index.css">
</head>
<body>

	<c:set var="adm" value="${sessionScope.administrador}" />

	<div class="wrapper">
		<h1>${sessionScope.administrador.nome}, bem-vindo(a) à sua Carteira Digital!</h1>
		
		<c:set var="nomeString" value="nome${adm.login}" />
		<c:set var="rgString" value="rg${adm.login}" />
		<c:set var="cpfString" value="cpf${adm.login}" />
		
		<c:forEach var="c" items="${cookie}">
		    <c:if test="${c.key == nomeString}">	    	
		        <c:set var="nome" value="${c.value.value}" />
		    </c:if>
		    <c:if test="${c.key == rgString}">	    	
		        <c:set var="rg" value="${c.value.value}" />
		    </c:if>
		    <c:if test="${c.key == cpfString}">	    	
		        <c:set var="cpf" value="${c.value.value}" />
		    </c:if>
		</c:forEach>	
		
	    <p class="recent-activity">Última pessoa cadastrada: ${fn:replace(nome, '+', ' ')} | RG: (${fn:replace(rg, '+', ' ')}) | CPF: (${fn:replace(cpf, '+', ' ')})</p>
	    
		<a href="Form.html"><input class="button-6" type="button"
			value="Cadastrar"></a> <a
			href="ServletController?operacao=Listar"><input class="button-6"
			type="button" value="Listar"></a> <a
			href="AdministradorController?operacao=sair"><input type="button"
			class="button-6" value="Sair"></a> <a
			href="AdministradorController?operacao=excluir"><input
			type="button" class="button-6" value="Excluir"></a>
	</div>
</body>
</html>