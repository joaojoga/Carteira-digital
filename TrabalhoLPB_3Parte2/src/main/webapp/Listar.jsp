<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.Pessoa" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./Listagem.css">
<title>Catálogo de Pessoas</title>
</head>
<body>
	</head>
	<body>

		<div class="container">
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Sexo</th>
				<th>Idade</th>
				<th>RG</th>
				<th>CPF</th>
				<th>Salário</th>
				<th>Contribuição</th>

				<th>Area</th>
				<th class="operacoesTh" colspan="3">Operações</th>
			</tr>
			
			<c:choose>
                <c:when test="${not empty pessoasBanco}">
                    <c:forEach var="pessoa" items="${pessoasBanco}">
                        <tr>
                            <td>${pessoa.nome}</td>
                            <td>${pessoa.sexo}</td>
                            <td>${pessoa.idade}</td>
                            <td>${pessoa.rg}</td>
                            <td>${pessoa.cpf}</td>
                            <td>${pessoa.salario}</td>
                            <td>${pessoa.tempoContribuicao} Anos</td>
                            <td>${pessoa.areaTrabalho}</td>
                            <td><a href="ServletController?operacao=Remover&cpf=${pessoa.cpf}"><img src="./images/trash.png" height="20px"></a></td>
                            <td><a href="ServletController?operacao=Buscar&cpf=${pessoa.cpf}"><img src="./images/edit.png" height="20px"></a></td>
                            <td><a href="ServletController?operacao=Operacoes&cpf=${pessoa.cpf}"><img src="./images/opp.png" height="20px"></a></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="9">Não há registros de pessoas ainda!</td>
                    </tr>
                </c:otherwise>
            </c:choose>
		</table>
		<p><a href="index.jsp">Inicio</a></p>
		</div>
		
		
</body>
</html>