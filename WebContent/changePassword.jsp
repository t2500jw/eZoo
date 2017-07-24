<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
<style>
	.CommentBox > div {
					    margin:0 auto;
					    width:250px;
					}
</style>
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
 
	<h2>Change Password:</h2>
	<h3></h3>
	<form method="POST" action="changePassword">		
				Username: <input type="text" name="username" value="${username}"><br/>
				Password: <input type="password" name="password1"><br />
				Re-Enter Password: <input type="password" name="password2"><br />
				<input type="submit" value="Change PW">		
	</form>
	<br/>
	<br/>
	<a href="/eZoo/register.jsp">Create a new account</a>
	</div>
	</header>	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	
