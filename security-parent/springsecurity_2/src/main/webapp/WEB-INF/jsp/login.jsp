<html>
<body>
<h2>login</h2>
<%--<form action="${pageContext.request.contextPath}/login" method="post">--%>
<form action="${pageContext.request.contextPath}/login" method="post">
    user:<input type="text" name="username"> <br>
    pass:<input type="password" name="password"> <br>
    code: <input type="text" name="imageCode">
    <img src="${pageContext.request.contextPath}/imageCode"><br>
    <input type="submit" value="LOGIN">
</form>
</body>
</html>
