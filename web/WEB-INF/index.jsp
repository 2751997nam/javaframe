<%@ page import="models.User"%>
<%@page import="models.Product"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>index</title>
        <link rel="stylesheet" href="style/style.css"/>
    </head>
    <body>
        <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users"); %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Created</th>
                    <th>Updated</th>
                </tr>
            </thead>
            <tbody>
                <% for (User user : users) {%>
                <tr>
                    <td><%= user.getId()%></td>
                    <td><%= user.getName()%></td>
                    <td><%= user.getEmail()%></td>
                    <td><%= user.getCreated_at()%></td>
                    <td><%= user.getUpdated_at()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        
        <div class="paginate">
            <%@include  file="paginate.jsp"%>
        </div>           
    </body>
</html>