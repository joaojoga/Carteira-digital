<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="FormAdm.css">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
    <c:if test="${not empty loginError}">
        <div class='wrapperMessage'>
            <p>${loginError}</p>
        </div>
    </c:if>
     <div class="container">
    	<h2>Faça seu login</h2>
	    <form action="AdministradorController" method="post">
	        Login: <input type="text" name="login"><br>
	        Senha: <input type="password" name="senha"><br>
	        <input type="submit" value="Logar" name="operacao">
	        <p>Ainda não possui uma conta?</p><a href="./FormAdm.jsp">Cadastre-se agora</a>
	    </form>
	 </div>
</body>
</html>