
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>meals</h2>





<table border="1">
    <caption>Meals</caption>
    <tr>
        <th>dateTime</th>
        <th>description</th>
        <th>calories</th>
        <th></th>
        <th></th>

    </tr>


    <c:forEach items="${mealTos}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${meal.excess ? "red":"green"}">
            <td><c:out value="${meal.dateTimeFormatted}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="#">Update</a></td>
            <td><a href="meals/delete?id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>


</table>
</body>
</html>