<%@page import="com.prolink.sync.model.Aniversariante"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Aniversariantes</title>
</head>
<body>
<div style="text-align: center;">
			<table align="center" border="1" cellpadding="1" cellspacing="0" style="width: 100%">
				<caption>
					<strong>Rela&ccedil;&atilde;o de Aniversariantes da Semana</strong></caption>
				<thead>
					<tr>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">Data</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">ID</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">Status</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">Empresa</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">Socio</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">Telefone</span></th>
						<th scope="col" style="background-color: rgb(0, 0, 153); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color:#fff0f5;">E-mail</span></th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty(aniversariantes)}">
						<th colspan="7"
							style="background-color: rgb(255, 204, 0); border-color: rgb(102, 102, 102); text-align: center;">
							<span style="color: rgb(0, 0, 153);">Nenhum aniversariante
								essa semana</span>
						</th>
					</c:when>
					<c:otherwise>
						<c:forEach var="a" items="${aniversariantes}">
							<tr>
								<th
									style="border-color: rgb(102, 102, 102); text-align: center;">
									<span style="color: rgb(0, 0, 153);">${a.aniversario}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102); text-align: center;">
									<span style="color: rgb(0, 0, 153);">${a.id}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102); text-align: center;">
									<span style="color: rgb(0, 0, 153);">${a.status}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102);">
									<span style="color: rgb(0, 0, 153);">${a.empresa}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102);">
									<span style="color: rgb(0, 0, 153);">${a.nome}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102); text-align: center;">
									<span style="color: rgb(0, 0, 153);">${a.telefone}</span>
								</th>
								<th
									style="border-color: rgb(102, 102, 102);">
									<p>
										<span style="color: rgb(0, 0, 153);">${a.email}</span>
									</p>
								</th>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
</body>
</html>