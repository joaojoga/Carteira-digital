<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="FormAdm.css">
<meta charset="ISO-8859-1">
<title>Faça seu cadastro</title>
</head>
<body>

	 <div class="container">
		<h2>Cadastre-se agora</h2>
		<form action="AdministradorController" method="post" >
			Login: <input type="text" name="login"><br>
			Senha: <input type="password" name="senha"><br>
			Nome: <input type="text" name="nome"><br>
			Cpf: <input type="text" name="cpf" minlength="11" maxlength="11"><br>
			Email: <input type="email" name="email"><br>
			Telefone: <input type="text" maxlength="11" name="telefone"><br>
			<input type="submit" value="Cadastrar" name="operacao"></input>
			<p>Já possui uma conta?</p><a href="./LoginAdm.jsp">Faça seu login</a>
		</form>
	</div>
</body>
</html>