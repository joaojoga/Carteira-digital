<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Model.Pessoa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carteira Digital - Pessoal</title>
<link rel="stylesheet" href="CarteiraDigital.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

</head>
<body>
    <c:set var="p" value="${requestScope.pessoa}" />
    <c:set var="result" value="${requestScope.metodo}" />
    
    <c:set var="sexo">
        <c:choose>
            <c:when test="${p.sexo == 77}">Masculino</c:when>
            <c:otherwise>Feminino</c:otherwise>
        </c:choose>
    </c:set>

    <div class="profile-container">
        <div class="profile-header">
            <h2>Perfil - Carteira Digital</h2>
        </div>
        <div class="profile-field field-profile-picture">
            <img src="images/profile-pic.jpg" alt="Profile Picture">
        </div>
        <div class="profile-field field-name">
            <label>Nome: <c:out value="${p.nome}" /></label>
        </div>
        <div class="profile-field">
            <label>Sexo: <c:out value="${sexo}" /></label>
        </div>
        <div class="profile-field">
            <label>Data de Nascimento: <c:out value="${p.dataNasc}" /></label>
        </div>
        <div class="profile-field">
            <label>RG: <c:out value="${p.rg}" /></label>
        </div>
        <div class="profile-field">
            <label>CPF: <c:out value="${p.cpf}" /></label>
        </div>
        <div class="profile-field">
            <label>Salário: <c:out value="${p.salario}" /></label>
        </div>
        <div class="profile-field">
            <label>Tempo de Contribuição: <c:out value="${p.tempoContribuicao}" /></label>
        </div>
        <div class="profile-field">
            <label>Área de Trabalho: <c:out value="${p.areaTrabalho}" /></label>
        </div>
        <div class="profile-field">
            <label><c:out value="${requestScope.resposta}" />: <c:out value="${result}" /></label>
        </div>
    </div>
</body>
</html>