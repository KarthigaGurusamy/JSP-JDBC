<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<h3 class="fs-1 text-center mt-2">Welcome to Todo Application!</h3>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header">Login</div>
        <div class="card-body">
          <form action = "login" method="POST">
            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" class="form-control" name="username" placeholder="Enter your username">
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" name="password" placeholder="Enter your password">
            </div>
             <%
                    if(request.getAttribute("error")!=null)
                    {
                        out.print("<p style='color:red;'> Invalid Credentials!!! </p>");
                    }
                %>
            <input type="submit" value="Login" name="login" style="background-color: #007bff;color: #fff;  border: none;  border-radius: 5px; padding: 10px 20px; cursor: pointer; margin-top:5px"/>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header">Register</div>
        <div class="card-body">
          <form action = "login" method="POST">
            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" class="form-control" name="username" placeholder="Enter your username">
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" name="password" placeholder="Enter your password">
            </div>
            <div class="form-group">
                <label for="confirmpassword">Confirm Password</label>
                <input type="password" class="form-control" name="confirmpassword" placeholder="Enter confirm password">
            </div>
             <%
                         if(request.getAttribute("registererror")!=null)
                         {
                             out.print("<p style='color:red;'> Password Mismatch!!! </p>");
                         }
                     %>
                     <%
                                              if(request.getAttribute("existingerror")!=null)
                                              {
                                                  out.print("<p style='color:red;'> User already exists! </p>");
                                              }
                                          %>
            <input type="submit" value="Register" name="register" style="background-color: red;color: #fff;  border: none;  border-radius: 5px; padding: 10px 20px; cursor: pointer; margin-top:5px"/>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>