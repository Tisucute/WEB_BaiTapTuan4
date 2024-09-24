<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Reset Password Form</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card mt-5">
          <div class="card-body">
            <h2 class="card-title text-center text-success">Reset Password Form</h2>

            <!-- Form forgot password -->
            <form action="${pageContext.request.contextPath}/forgotpassword" method="post">
              <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
              </div>
              <div class="form-group">
                <label for="password">New Password</label>
                <input type="password" class="form-control" id="password" name="password"
                       pattern="^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])\\S{8,}$"
                       title="Password must contain at least one number, one alphabet, one symbol, and be at least 8 characters long"
                       placeholder="Enter new password" required>
              </div>
              <div class="form-group">
                <label for="repassword">Re-type Password</label>
                <input type="password" class="form-control" id="repassword" name="repassword" placeholder="Re-type new password" required>
              </div>

              <button type="submit" class="btn btn-primary btn-block">Submit</button>
            </form>

            <!-- Link trở lại trang login -->
            <div class="text-center mt-3">
              <a href="${pageContext.request.contextPath}/login" class="btn btn-link">Back to Login</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS and jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>