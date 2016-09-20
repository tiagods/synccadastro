<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
 
<link rel="stylesheet" type="text/css" href="./front/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="./front/resources/bootstrap/css/bootstrap-theme.min.css" />
<script src="./front/resources/bootstrap/js/bootstrap.min.js"></script>
 
</head>
<body>
	<div>
		<form method="GET" action="Iniciar">
			<table border="0" align="center">
				<tr>
					<td colspan="2">
					<img src="./front/imagens/LOGO_PROLINK_CONTABIL_POSITIVO.jpg" />
					</td>
				</tr>
				<tr>
					<td align="right">
						Usuario:
					</td>
					<td>
						<input type="text" class="form-control" name="user" size="10" style="width: 100px; ">
					</td>	
				</tr>
				<tr>
					<td align="right">
						Senha:
					</td>
					<td>
						<input type="password" class="form-control" name="password" size="10" style="width: 100px; "/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" class="btn btn-success.active" value="Entrar">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>