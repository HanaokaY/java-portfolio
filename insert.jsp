<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>従業員新規登録ページ</title>
</head>
<body>
	<h1>従業員新規登録ページ</h1>
	<p class="message"><c:out value="${message }" /></p>
	<form action="insert" method="post">
		<table border="1" summary="従業員情報登録フォーム">
			<tr>
				<td>名前</td>
				<td><input type="text" name="name" value="" /></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><input type="text" name="address" value="" /></td>
			</tr>
			<tr>
				<td>年齢</td>
				<td><input type="text" name="age" value="" /></td>
			</tr>
			<tr>
				<td>E-MAIL</td>
				<td><input type="text" name="email" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="btn" value="InsertEmp" />
					<input type="submit" value="登録" />
				</td>
			</tr>
		</table>
	</form>
	<p><a href="controller">従業員検索ページへ</a></p>
</body>
</html>