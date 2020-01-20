<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/projects">Projects</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tasks">Tasks</a>
            </li>
        </ul>
    </div>
</nav>

<a href="/tasks/addPage/${projectId}" class="btn btn-secondary btn-lg active mt-3 ml-3" role="button" aria-pressed="true">Add task</a>

<table class="table my-3">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Project id</th>
        <th scope="col">Description</th>
        <th scope="col">Date start</th>
        <th scope="col">Date finish</th>
        <th scope="col">Status</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <th scope="row">${task.id}</th>
            <td>${task.name}</td>
            <td>${task.projectId}</td>
            <td>${task.description}</td>
            <td>${task.dateStart}</td>
            <td>${task.dateFinish}</td>
            <td>${task.status}</td>
            <td><a type="button" class="btn btn-secondary" href="/tasks/editPage/${task.projectId}/${task.id}">Edit</a></td>
            <td><a type="button" class="btn btn-secondary" href="/tasks/delete/${task.projectId}/${task.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
