<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<html>
<head>
    <title>股票历史记录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <!--link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>日期</th>
                    <th>代码</th>
                    <th>名称</th>
                    <th>收盘价</th>
                    <th>最高价</th>
                    <th>最低价</th>
                    <th>开盘价</th>
                    <th>前收盘</th>
                    <th>涨跌额</th>
                    <th>涨跌幅</th>
                    <th>换手率</th>
                    <th>成交量</th>
                    <th>成交金额</th>
                    <th>总市值</th>
                    <th>流通市值</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="StockInfo" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${StockInfo.datetime}</td>
                        <td>${requestScope.get('id')}</td>
                        <td>${requestScope.get('Name')}</td>
                        <td>${StockInfo.closing_price}</td>
                        <td>${StockInfo.highese_price}</td>
                        <td>${StockInfo.lowest_price}</td>
                        <td>${StockInfo.opening_price}</td>
                        <td>${StockInfo.previous_close}</td>
                        <td>${StockInfo.ups_and_downs}</td>
                        <td>${StockInfo.quote_change}</td>
                        <td>${StockInfo.turnover_rate}</td>
                        <td>${StockInfo.volume}</td>
                        <td>${StockInfo.turnover}</td>
                        <td>${StockInfo.ttmc}</td>
                        <td>${StockInfo.cmc}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>
