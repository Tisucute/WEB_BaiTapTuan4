<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
    <h1>Register</h1>
    <form action="${pageContext.request.contextPath}/register" method="post">
      <c:if test="${alert != null}">
        <div class="alert alert-danger">${alert}</div>
      </c:if>

      <div class="form-group">
        <label for="uname">Username</label>
        <input type="text" class="form-control" id="uname" name="uname" placeholder="Enter Username" required>
      </div>
      <div class="form-group">
        <label for="fullname">Fullname</label>
        <input type="text" class="form-control" name="fullname" placeholder="Enter Fullname" required>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name="email" placeholder="Enter Email" required>
      </div>
      <div class="form-group">
        <label for="psw">Password</label>
        <input type="password" class="form-control" name="psw" placeholder="Enter Password" required>
      </div>
      <div class="form-group">
        <label for="psw-repeat">Repeat Password</label>
        <input type="password" class="form-control" name="psw-repeat" placeholder="Repeat Password" required>
      </div>
      <button type="submit" class="btn btn-primary">Register</button>
    </form>

    <hr>

    <p>Already have an account? 
      <a href="${pageContext.request.contextPath}/login">Login here</a>.
    </p>
  </div>
</body>
</html>