<!-- Header -->
	<jsp:include page="/header.jsp" />
	
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
 
		<h1>eZoo <small>Change User Roles</small></h1>
		<hr class="paw-primary">
		<h2>Change to customer role</h2>
		<form action="changeUserRole" method="post">
		<table class="table table-striped table-hover table-responsive ezoo-datatable" id="changeRoleToCustomer">
			<thead>
				<tr>					
					<th class="text-center">User</th>
					<th class="text-center">Current Role</th>	
					<th class="text-center">Change</th>								
				</tr>
			</thead>			
			<tbody>
				<c:forEach var="userRoles" items="${users}">				
					<tr>						
						<td class="username"><c:out value="${userRoles.username}" /></td>
						<td class="role"><c:out value="${userRoles.role}" /></td>
						<!--  <td><input type="submit" value="Change Checked User Roles"/></td>-->						
						<td align="center"><button type="Submit" name="newRoll" value="${userRoles.username},Customer">Change To Customer Role</button></td>				
					</tr>					
				</c:forEach>
			</tbody>
		</table>						
		</form>		
		<br />
		<br />
		<h2>Change to employee role</h2>
		<form action="changeUserRole" method="post">
		<table class="table table-striped table-hover table-responsive ezoo-datatable" id="changeRoleToCustomer">
			<thead>
				<tr>					
					<th class="text-center">User</th>
					<th class="text-center">Current Role</th>	
					<th class="text-center">Change</th>								
				</tr>
			</thead>			
			<tbody>
				<c:forEach var="userRoles" items="${users}">				
					<tr>						
						<td class="username"><c:out value="${userRoles.username}" /></td>
						<td class="role"><c:out value="${userRoles.role}" /></td>
						<!--  <td><input type="submit" value="Change Checked User Roles"/></td>-->						
						<td align="center"><button type="Submit" name="newRoll" value="${userRoles.username},Employee">Change To Employee Role</button></td>				
					</tr>					
				</c:forEach>
			</tbody>
		</table>						
		</form>		
	  </div>
	</header>
	
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
    $(document).ready(function () { //launch this code after the whole DOM is loaded
        $("form").submit(function (event) { // function to process submitted table
                    var tableData = []; // we will store rows' data into this array
                    $("#changeRole") // select table by id
                            .find(".tableRow") // select rows by class
                            .has(":checked") // select only rows with checked checkboxes
                            .each(function () { // for each selected row extract data               
                                var tableRow = {};
                                var jRow = $(this);
                                tableRow.alter = jRow.find('td.alter').text();
                                tableRow.username = jRow.find('td.username').text();
                                tableRow.role = jRow.find('td.role').text();
                                tableRow.newRole = jRow.find('td.newRole')text();
                                tableData.push(tableRow);
                            });

                    $.post(
                            "changeUserRole", /*url of consuming servlet*/
                            {tableData: tableData}, /*data*/
                            function () {
                                alert("Success!");
                            }, /*function to execute in case of success*/
                            "json" /* data type */
                    );
                    event.preventDefault(); //Prevent sending form by browser
                }
        );


    });
</script>
	

	<!-- Footer -->
	<jsp:include page="/footer.jsp" />
