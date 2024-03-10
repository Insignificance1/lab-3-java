<<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String directory = request.getParameter("path");
    File file = new File(directory);
    String parentDirectoryPath = "/";

    parentDirectoryPath = file.getParent();  // Получаем путь к папке-родителю

    if (parentDirectoryPath == null) {
        parentDirectoryPath = "/";
    }

%>
<html>
<head>
    <title>File Explorer</title>
</head>
<body>
<h1>Текущая директория: "<%=directory%> "</h1>
<p><a href=<%="?path="+parentDirectoryPath.replace("\\","/")%>/>Назад</p>
<table>
    <tr>
        <th></th>
        <th>Папка</th>
        <th>Перейти</th>
        <th>Размер(байты)</th>
        <th>Последнее изменение</th>
    </tr>
    <%
        File[] itemList = (File[]) request.getAttribute("folders"); // Получаем список из объекта запроса
        for (File item : itemList) {
    %>
    <tr>
        <th><img src="folderDir.png" width="16" height="16"></th>
        <th><%= item.getName()%></th>
        <th><a href=<%="?path="+item.getAbsolutePath().replace("\\", "/")%>/>Перейти</th>
        <th><%= item.length()%></th>
        <th><%= new Date(item.lastModified())%></th>
    </tr>
    <% } %>
    <tr>
        <th></th>
        <th>Файл</th>
        <th>Ссылка на скачивание</th>
        <th>Размер(байты)</th>
        <th>Последнее изменение</th>
    </tr>
    <%
        File[] list = (File[]) request.getAttribute("files"); // Получаем список из объекта запроса
        for (File item : list) {
    %>
    <tr>
        <th><img src="fileFile.png" width="16" height="16"></th>
        <th><%= item.getName()%></th>

        <th><a href=<%="http://localhost:8080/lab_3_war_exploded/Download?path="+ item.getAbsolutePath().replace("\\","/")%>> Скачать </a> </th>
        <th><%= item.length()%></th>
        <th><%= new Date(item.lastModified())%></th>
    </tr>
    <% } %>
</table>

<p></p>
</body>
</html>
