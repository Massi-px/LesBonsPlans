<%--
  Created by IntelliJ IDEA.
  User: riadh
  Date: 26/11/2024
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<h1>Les annonces</h1>

<div class="container">
    <div class="row">
            <c:forEach items="${annonces}" var="annonce">
                <div class="card" style="width: 18rem;">
                    <img src="${annonce.path}" class="card-img-top" alt="image">
                    <div class="card-body">
                        <h5 class="card-title">${annonce.title}</h5>
                        <p class="card-text">${annonce.description}</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </c:forEach>
    </div>
</div>

</body>
</html>
