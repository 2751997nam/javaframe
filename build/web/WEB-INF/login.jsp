<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>login</title>
    </head>
    <body>
        <form action="LoginController" method="post">
            <div>
                <label for="username">Username:</label>
                <input type="text" name="username" placeholder="Username"/>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" name="password" placeholder="Password"/>
            </div>
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
