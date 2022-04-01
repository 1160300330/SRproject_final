<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <style type="text/css">

        body {
            margin: 0;
            padding:0;
        }
        /* 头部样式 */
        .header {
            background:url(tittle.png) no-repeat left;
            background-size: 90px;
            height: 100px;
            text-align: center;
        }

        /* 导航条 */
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        /* 导航链接 */
        .topnav a {
            float: right;
            display: block;
            color: #f2f2f2;
            text-align: center;
            font-size: 20px;
            padding:20px 22px;
            text-decoration: none;
        }

        /* 链接 - 修改颜色 */
        .topnav a:hover {
            background-color: #EF4B4C;
            color: white;
        }

        .center{
            background-color: #43506C;
            height: 701px;
            margin:0;
            padding-top:1px;
        }
        /*.blur {-webkit-filter: blur(4px);filter: blur(4px);}*/
        /* 底部样式 */
        .footer {
            padding: 20px;
            text-align: right;
        }
        h1 {text-decoration:none;font-size: 50px;color:#333;text-align:left;font-family:"Times New Roman",Times,serif;padding-left:80px;padding-top:15px;}
        p {color:#43506C;font-family:黑体,Times,serif}
        img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>股票模拟交易市场 </h1>
</div>
<div class="topnav">
    <a href="/user/login">用户登录</a>
    <a href="/stock/queryPage">开始查询</a>
</div>
<div class="center">
    <img class="blur" src="1.jpg" height="1576" width="3832" >
</div>

<div class="footer">
    <p style=“color:#43506C”>
        使啥浏览器都行，分辨率没什么要求。
    </p>
    <p>
        消费者投资热线： 400-8888-400
    </p>
</div>

</body>
</html>