
<%@ page import="java.util.List" %>
<%@ page import="com.riadh.siteannonce.dao.AnnonceDao" %>
<%@ page import="com.riadh.siteannonce.model.Annonce" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>Les Bons plans</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<%
    AnnonceDao annonceDao = new AnnonceDao();
    List<Annonce> annonces = annonceDao.getAllAnnonces();
%>
<div class="container mx-auto p-4">
    <h1 class="text-4xl font-bold text-center text-gray-800 mb-8">Les Bons Plans</h1>

    <div class="bg-white p-6 rounded-lg shadow-lg mb-8">
        <form action="search" method="get" class="flex items-center">
            <input type="text" name="keyword" placeholder="Rechercher des mots clés..." class="flex-grow p-2 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            <button type="submit" class="p-2 bg-blue-500 text-white rounded-r-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500">Rechercher</button>
        </form>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-lg ">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Liste des annonces</h2>
        <ul class="space-y-4">

            <c:forEach items="<%=annonces%>" var="annonce">
                <li class="p-4 bg-gray-50 rounded-lg shadow-sm hover:bg-gray-100 pointer">
                    <button type="button" onclick="window.location.href='/site_annonce_war/detail?id=${annonce.id}'">
                        <h3 class="text-xl font-semibold text-gray-700">${annonce.title}</h3>
                        <p class="text-gray-600">${annonce.description}</p>
                    </button>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>
</body>
</html>