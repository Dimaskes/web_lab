<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 4/25/2020
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Library</title>
	<link href="https://fonts.googleapis.com/css?family=Fira+Sans:100,300,400,500,800&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:set scope="session" var="client" value="${sessionScope.client}" />
<c:if test="${client == null}">
	<meta http-equiv="refresh" content="5; URL=index.jsp">
	<div class="container">
		<div class="content">
			<div class="content__name">
				<div class="content__name--title">Онлайн библиотека WEBLAB</div>
				<div class="content__name--subtitle">Онлайн библиотека WEBLAB</div>
			</div>
			<div class="content__menu return--now">
				<div class="menu__text">
					<div class="return__text">
						Пользователь не найден<br>Вы будете перенаправлены на домашнюю страницу через 5 секунд
					</div>
					<a href="index.jsp">вернуться сейчас</a>
				</div>
			</div>
		</div>
	</div>
</div></c:if>
<c:if test="${client != null}">
<div class="container">
	<div class="content">
		<div class="content__name">
			<div class="content__name--title reset__name--title">Онлайн библиотека WEBLAB</div>
			<div class="content__name--subtitle">Онлайн библиотека WEBLAB</div>
		</div>
		<div class="user-space content__menu">
			<div class="greeting-user">
				Здравствуйте, <c:out value="${client.login}"/>!
			</div>
			<div class="user-table">
				<i>Авторы:</i>
				<table class="author-table">
					<tr>
						<th>ID</th>
						<th>количество<br>книг</th>
					</tr>
					<c:forEach var="i" begin="0" end="${client.countAuthors - 1}">
						<tr/>
						<td><c:out value="${client.getAutFromIndex(i).author}"></c:out></td>
						<td><c:out value="${client.getAutFromIndex(i).sizeBook}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
				<i>Книги:</i>
				<table class="book-table">
					<tr>
						<th>ID</th>
						<th>название</th>
						<th>год</th>
						<th>количество<br>страниц</th>
						<th>цена</th>
						<th>ID автора</th>
					</tr>
					<c:forEach var="i" begin="0" end="${client.countAuthors - 1}">
						<c:forEach var="j" begin="0" end="${client.getAutFromIndex(i).sizeBook - 1}">
							<tr/>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).id}"/></td>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).name}"/></td>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).year}"/></td>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).countPage}"/></td>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).price}"/></td>
							<td><c:out value="${client.getAutFromIndex(i).getBookFromIndex(j).authorId}"/></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
			<div class="xml-download">
				<a href="result.xml" download>скачать xml-файл</a>
			</div>
		</div>
	</div>
</div>
</c:if>
</body>
</html>
