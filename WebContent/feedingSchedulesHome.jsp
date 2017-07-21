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
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>			
					<th class="text-center">Update</th>		
					<th class="text-center">Time</th>
					<th class="text-center">Recurrence (Hours)</th>
					<th class="text-center">Food Type</th>
					<th class="text-center">Notes</th>	
					<th class="text-center">Animals</th>			
				</tr>
			</thead>
			<tbody>
				<c:forEach var="schedules" items="${schedules}">
					<tr>
						<td align="center">
							<form action="updateFeedingSchedule" method="get">
								<button type="Submit" name="id" value="${schedules.schedule_ID}">Update</button>
							</form>
						</td>
						<td><c:out value="${schedules.time}" /></td>
						<td><c:out value="${schedules.recurrence}" /></td>
						<td><c:out value="${schedules.food}" /></td>
						<td><c:out value="${schedules.notes}" /></td>	
						<td><select>
							<c:forEach var="animals" items="${animals}">																										
									<c:if test="${schedules.schedule_ID == animals.feedingSchedule}">
										<option>
											<c:out value="${animals.name}"/>
										</option>
									</c:if>								
							</c:forEach>
							</select>
						</td>					
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	  </div>
	</header>
	

	<!-- Footer -->
	<jsp:include page="footer.jsp" />	
	
