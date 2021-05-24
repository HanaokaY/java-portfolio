<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="content-style-type" content="text/css" />
<style type="text/css">
th {
	background-color: #FF9900;
}
.message {
	color: #800000;
}
</style>
<title>従業員検索ページ</title>
</head>
<body>
	<h1>従業員検索ページ</h1>
	<form action="controller" method="post">
		<p>検索したい従業員IDを入力して下さい</p>
		<p>
			<input type="text" name="paramId" value="" />
			<input type="hidden" name="btn" value="IdSearch" />
			<input type="submit" value="検索" />
		</p>
	</form>
	<form action="controller" method="post">
		<p>検索したい年齢を入力して下さい</p>
		<p>
			<input type="text" name="paramAge1" value="" />歳から
			<input type="text" name="paramAge2" value="" />歳まで
			<input type="hidden" name="btn" value="AgeSearch" />
			<input type="submit" value="検索" />
		</p>
	</form>
	<c:if test="${not empty requestScope.message}">
		<p class="message">${requestScope.message}</p>
	</c:if>
	<c:if test="${not empty requestScope.empList}">
		<h2>検索結果</h2>
		<table border="1" summary="検索結果をまとめた表">
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>住所</th>
				<th>年齢</th>
				<th>E-MAIL</th>
			</tr>
			<c:forEach var="emp" items="${requestScope.empList}">
				<tr>
					<td><c:out value="${emp.id}" /></td>
					<td><c:out value="${emp.name}" /></td>
					<td><c:out value="${emp.address}" /></td>
					<td><c:out value="${emp.age}" /></td>
					<td><c:out value="${emp.mail}" /></td>
					<td>
					<form action="delete" method="get">
					<input type="hidden" name="empId" value="${emp.id }"/>
					<input type="submit" value="削除"/>
					</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>