<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
	<h2>Login:</h2>	
	<form method="POST" action="j_security_check">
		Username: <input type="text" name="j_username" value="" ><br/>
		Password: <input type="password" name="j_password"><br />
		<input type="submit" value="Login">
	</form>	
	<h3><a href="/eZoo/register.jsp">Create a new account</a></h3>
	</div>
	</header>	
	
	

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	
