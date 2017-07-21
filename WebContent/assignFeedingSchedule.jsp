<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- jquery stuff -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

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
 
		<h1>eZoo <small>Assign Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Schedule ID</th>
					<th class="text-center">Time</th>
					<th class="text-center">Recurrence (Hours)</th>
					<th class="text-center">Food Type</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Animal></th>					
				</tr>
			</thead>
			<tbody>			
				<c:forEach var="schedules" items="${schedules}">
					<tr>					
						<td><c:out value="${schedules.schedule_ID}" /></td>
						<td><c:out value="${schedules.time}" /></td>
						<td><c:out value="${schedules.recurrence}" /></td>
						<td><c:out value="${schedules.food}" /></td>
						<td><c:out value="${schedules.notes}" /></td>	
						<!--  					
						<td><select name="name">
							<c:forEach var="animals" items="${animals}">
								<c:if test="${schedules.schedule_ID == animals.feedingSchedule }">
									<option value="{animals.name}"><c:out value="${animals.name}"/></option>
								</c:if>
							</c:forEach>
						</select></td>	
						-->
						
						<td><form id="form1" action="assignFeedingSchedule" method="post">															
								<select name="name">
									<c:forEach var="animals" items="${animals}">								
											<option value="${animals.name}"/><c:out value="${animals.name}"/>															
									</c:forEach>
								</select>
								<button type="Submit" name="id" value="${schedules.schedule_ID}">Assign</button>
							</form>	
						</td>	
						<!--  
						<td align="center">
							<form id="form1" action="assignFeedingSchedule" method="post">								
								<button type="Submit" name="id" value="${schedules.schedule_ID}">Assign</button>
							</form>	
						</td>	
						-->															
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	  </div>
	</header>
	

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
