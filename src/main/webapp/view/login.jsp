<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
    <h2>Login to Your Account</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
      <c:if test="${alert != null}">
        <div class="alert alert-danger">${alert}</div>
      </c:if>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
      </div>
      <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="remember" name="remember">
        <label class="form-check-label" for="remember">Remember me</label>
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>

    <hr>
	 <p>Forgot your password? 
	      <button class="btn btn-link" onclick="window.location.href='${pageContext.request.contextPath}/forgotpassword'">Reset Password</button>
	    </p>
    <!-- Redirect to Register Page -->
    <p>Don't have an account? 
      <button class="btn btn-link" onclick="window.location.href='${pageContext.request.contextPath}/register'">Register</button>
    </p>
  </div>
</body>
</html>