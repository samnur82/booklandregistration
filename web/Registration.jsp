<%-- 
    Document   : Login
    Created on : Mar 29, 2021, 3:35:04 PM
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book's Land Registration</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Registration.css" type="text/css" />              
    </head>
    <body>  
      <form action="InsertUser" method="POST">
        <div class="backtologin">
            <a href="http://jenkins.fitraalim.com:9010/BookLandLogin/Login.jsp">Login</a>
            <!-- <a href="http://appslogin:9000/BookLandLogin/Login.jsp">Login</a> -->
            <!-- <a href="http://localhost:8080/BookLandLogin/Login.jsp">Login</a> -->
            
        </div>
        <div class="tbody">
          <table class="table">
            <tbody>
                <tr>
                    <td colspan="2" class="reg-title"><h1>Create User Account</h1></td>
                </tr>
                <tr>
                   <td td colspan="2" class="align-middle"><input type="text" class="form-control" name="display_name" placeholder="Display Name*"></td>
                </tr>
                  <tr>
                    <td class="align-middle"><input type="text" class="form-control" name="first_name" placeholder="First Name*"></td>
                    <td class="align-middle"><input type="text" class="form-control" name="last_name" placeholder="Last Name*"></td>
                </tr>
                  <tr>
                    <td colspan="2"><input type="text" class="form-control" name="email" placeholder="Email*"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="password" class="form-control" name="password" placeholder="Password*"></td>
                </tr>
                 <tr>
                    <td colspan="2"><input type="password" class="form-control" name="confirm_password" placeholder="Confirm Password*"></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="btn btn-info btn-block" type="submit" value="Register Now"></td>
                </tr>
                <tr>
                    <td colspan="2"><span class="success">${error_message}</span><span class="success">${success_message}</span></td>
                    <td colspan="2"><span hidden>booklandregistration</span></td>
                </tr>
            </tbody>
          </table>
        </div>
      </form>
    </body>
</html>

