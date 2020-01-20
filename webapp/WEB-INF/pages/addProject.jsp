<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
<form class="form ml-3 mt-3" action="/projects/addProject" method="GET">
    <div class="form-group">
        <label id="form" for="name">Name</label><br>
        <input id="form" type="text" name="name" placeholder="name">
    </div>
    <div class="form-group">
        <label id="form" for="description">Description</label><br>
        <input id="form" type="text" name="description" placeholder="description">
    </div>
    <div class="form-group">
        <label id="form" for="dateStart">Start Date</label><br>
        <input id="form" type="date" name="dateStart">
    </div>
    <div class="form-group">
        <label id="form" for="dateFinish">Finish Date</label><br>
        <input id="form" type="date" name="dateFinish">
    </div>
    <div class="form-group">
        <label id="status" for="status">Status</label>
        <select id="status" class="form-control" name="status">
            <option>${scheduled}</option>
            <option>${processing}</option>
            <option>${finished}</option>
        </select>
    </div>

    <input id="button" class="btn btn-outline-success" type="submit" value="Create Project">
</form>
</body>
</html>
