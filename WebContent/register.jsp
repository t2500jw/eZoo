<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>User Registration Page</h1>
<form method="POST" action="CreateNewUser">
		<h2>Create User Account</h2>
		Username: <input type="text" name="username"><br/>
		Password: <input type="password" name="password1"><br />
		Re-Enter Password: <input type="password" name="password2"><br />
		<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add</button>
		    </div>
		  </div>
	</form>
</body>
</html>