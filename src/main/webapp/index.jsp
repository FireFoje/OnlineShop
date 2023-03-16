<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
    <head>
        <title>Main page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css"/>
    </head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
    <h4><a href="/login">Sign in</a></h4>
        <h4><a href="/registration">Sign up</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Exit</a></h4>
    </sec:authorize>
    <h4><a href="/news">News(Only for users)</a></h4>
    <h4><a href="/admin">Users(Only for admin)</a> </h4>
</div>
</body>
</html>
