<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 4/18/2020
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dataSource.DataSource" %>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="ru">
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
<form>
<div class="container">
    <div class="content">
        <div class="content__name">
            <div class="content__name--title">Онлайн библиотека WEBLAB</div>
            <div class="content__name--subtitle">Онлайн библиотека WEBLAB</div>
        </div>
        <div class="content__menu">
            <div class="menu__text">Добро пожаловать в онлайн - библиотеку.<br>Пожалуйста, авторизируетесь</div>
            <c:set var="login" scope="session" value="${param.login_field}"/>
            <c:set var="password" scope="session" value="${param.password_field}"/>
            <c:if test="${login.equals('') and password.equals('')}">
                <div class="menu__forms">
                    <div class="forms__login">
                        Логин
                        <input type="text" name="login_field" placeholder="логин">
                        <div class="remark">поле обязательно для заполнения</div>
                    </div>
                    <div class="forms__password">
                        Пароль
                        <input type="password" name="password_field" placeholder="пароль">
                        <div class="remark">поле обязательно для заполнения</div>
                    </div>
                        <button class="forms__btn">Вход</button>
                </div>
            </c:if>
            <c:if test="${login.equals('') and !password.equals('')}">
                <div class="menu__forms">
                    <div class="forms__login">
                        Логин
                        <input type="text" name="login_field" placeholder="логин">
                        <div class="rem
                        ark">поле обязательно для заполнения</div>
                    </div>
                    <div class="forms__password">
                        Пароль
                        <input type="password" name="password_field" placeholder="пароль">
                    </div>
                    <button class="forms__btn">Вход</button>
                </div>
            </c:if>
            <c:if test="${!login.equals('') and password == ('')}">
                <div class="menu__forms">
                    <div class="forms__login">
                        Логин
                        <input type="text" name="login_field" placeholder="логин">
                    </div>
                    <div class="forms__password">
                        Пароль
                        <input type="password" name="password_field" placeholder="пароль">
                        <div class="remark">поле обязательно для заполнения</div>
                    </div>
                    <button class="forms__btn">Вход</button>
                </div>
            </c:if>
            <c:if test="${!login.equals('') and !password.equals('')}">
                <c:choose>
                    <c:when test="${DataSource.existUser(login, password)}">
                        <jsp:useBean id="user" class="entities.User" scope="session"/>
                        <c:set target="${user}" property="login" value="${login}"/>
                        <c:set target="${user}" property="password" value="${password}"/>
                        <c:redirect url="http://localhost:8081/lab_3/result.html"/>
                    </c:when>
                    <c:otherwise>
                        <div class="menu__forms remark__forms">
                            <div class="remark">такого пользователя не существует</div>
                            <div class="forms__login">
                                Логин
                                <input type="text" name="login_field" placeholder="логин">
                            </div>
                            <div class="forms__password">
                                Пароль
                                <input type="password" name="password_field" placeholder="пароль">
                            </div>
                            <button class="forms__btn">Вход</button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
</form>
</body>
</html>
