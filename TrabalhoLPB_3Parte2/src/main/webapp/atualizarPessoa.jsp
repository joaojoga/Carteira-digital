<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Pessoa" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="Form.css">
		<meta charset="ISO-8859-1">
		<title>Atualização de Perfil</title>
	</head>
	<body>
		<div class="form-container">
			<h3>Atualização da Tarefa</h3>
			<form action="ServletController" method="POST">
				<label>Nome:</label> 
				<input required class="input-field" value="${pessoa.nome}" type="text" name="nome">

				<label>Sexo:</label>
				<select required class="input-field" name="sexo" id="sexo">
			    <c:choose>
			        <c:when test="${pessoa.sexo == 77}">
			            <option value="M" selected>Masculino</option>
			            <option value="F">Feminino</option>
			        </c:when>
			        <c:otherwise>
			            <option value="M">Masculino</option>
			            <option value="F" selected>Feminino</option>
			        </c:otherwise>
			    </c:choose>
			</select>

				<label>Data de Nascimento:</label> 
				<input required class="input-field" value="${pessoa.dataNasc}" type="date" name="dataNasc">

				<input required type="hidden" value="${pessoa.rg}" maxlength="10" minlength="7" name="rg">
				<input required type="hidden" value="${pessoa.cpf}" maxlength="11" minlength="11" name="cpf">

				<label>Salário:</label> 
				<input required class="input-field" value="${pessoa.salario}" type="number" step="0.01" name="salario">

				<label>Tempo de Contribuição:</label> 
				<input required class="input-field" value="${pessoa.tempoContribuicao}" type="number" step="1" name="tempoContribuicao">

				<label>Área de Trabalho:</label>
				<select required class="input-field" name="areaTrabalho" id="areaTrabalho">
					<option value="Rural" ${pessoa.areaTrabalho == 'Rural' ? 'selected' : ''}>Rural</option>
					<option value="Urbano" ${pessoa.areaTrabalho == 'Urbano' ? 'selected' : ''}>Urbano</option>
				</select>
				
				<input type="submit" value="Atualizar" name="operacao" class="submit-button">
				<p><a href="index.jsp">Inicio</a></p>
			</form>
		</div>
	</body>
</html>