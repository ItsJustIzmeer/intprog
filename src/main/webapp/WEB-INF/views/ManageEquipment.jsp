<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.currPage.title}</title>
<link rel="stylesheet"
	href="<c:url value='/resources/font-awesome-4.7.0/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/systemStyle.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
	<!-- nav bar page-->
	<jsp:include page="navbar.jsp" />
	<main>
		<!-- side menu page -->
		<jsp:include page="sidenav.jsp" />

		<!-- main body -->
		<div class="main-body" style="width: -webkit-fill-available;">
			<div class="maintitle">Equipment List</div>
			<div class="main-container">
				<!-- start of main content -->
				<div class="box">
					<div class="program-container">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>No</th>
									<th>Equipment ID</th>
									<th>Equipment Name</th>
									<th>Brand</th>
									<th>Amount</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty equipments}">
									<c:forEach var="equipment" items="${equipments}">
										<tr>
											<td></td>
											<td>${equipment.equipmentId}</td>
											<td>${equipment.name}</td>
											<td>${equipment.brand}</td>
											<td>${equipment.amount}</td>
											<td><a href="detailEquipment/${equipment.id}"
												class="details-btn">Details</a><a
												href="editEquipment/${equipment.id}" class="details-btn">Edit</a></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty equipments}">
									<tr>
										<td colspan="6" style="text-align: center; color: red;">No
											equipments found.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!--  end of main content -->
				</div>
			</div>
		</div>
	</main>
	<!-- 	<script> -->
	<script src="<c:url value='/resources/js/equipment.js'/>"></script>
	<!--   </script> -->
</body>
</html>
