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
	
		<h1>eZoo <small>Add Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="addFeedingSchedule" method="post" class="form-horizontal">
		
		<!--  
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="id" placeholder="ID" required="required"/>
		    </div>
		  </div>
		  -->
		  <div class="form-group">
		    <label for="time" class="col-sm-4 control-label">Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="time" name="time" placeholder="Time (hh:mm)" required="required"/>
		    </div>
		  </div>			  
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence (Hours)</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="recurrence" name="recurrence" placeholder="Recurrence (Hours)" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add</button>
		    </div>
		  </div>		 
		</form>
	  </div>
	</header>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />