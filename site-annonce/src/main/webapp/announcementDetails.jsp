<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.coding.siteannonce.model.Announcement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails de l'announcement</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<% Announcement announcementDetails = (Announcement) request.getAttribute("announcementDetails"); %>

<div class="container mx-auto p-4">
    <h1 class="text-4xl font-bold text-center text-gray-800 mb-8">Détails de l'announcement</h1>
    <div class="bg-white p-6 rounded-lg shadow-lg flex flex-row gap-36">
        <div>
            <h2 class="text-2xl font-bold text-gray-800 mb-4"><%= announcementDetails.getTitle() %></h2>
            <p class="text-gray-600 mb-4"><%= announcementDetails.getDescription() %></p>
        </div>
        <div class="mt-4">
            <img src="<%= announcementDetails.getPath() %>" alt="Image de l'announcement" class="w-100 h-100 rounded-lg shadow-md">
        </div>
    </div>
    <div class="mt-8">
        <button onclick="window.history.back()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
            Retour
        </button>
    </div>
</div>
</body>
</html>