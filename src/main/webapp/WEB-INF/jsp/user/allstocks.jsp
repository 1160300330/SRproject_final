<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <head>
        <title>所有股票价格</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 引入 Bootstrap -->
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>股票价格 —— 显示所有股票</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增</a>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>股票编号</th>
                    <th>股票价格</th>
                    <th>股票名字</th>
                    <th>股票涨跌</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="stockPrice" items="${requestScope.get('list')}">
                    <tr>
                        <td>${stockPrice.getId()}</td>
                        <td>${stockPrice.getPrice()}</td>
                        <td>${stockPrice.getName()}</td>
                        <td>${stockPrice.getUps_and_downs()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
