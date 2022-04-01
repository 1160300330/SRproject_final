<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/srproject_war/user/trade/sell" method="post">
    <p>股票id: <input type="text" name="stockid" /></p>
    <p>数量: <input type="text" name="amount" /></p>
    <input type="submit" value="出售股票" />
</form>
</body>
</html>