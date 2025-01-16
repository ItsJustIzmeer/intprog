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
			<div class="maintitle">Request Equipment</div>
			<div class="main-container">
				<!-- start of main content -->
				<div class="box">
					<div class="program-container">
						<h2>Request Equipment Form</h2>
						<form id="requestEquipmentForm" method="post"
							action="<c:url value='/requestEquipment'/>">
							<input type="hidden" name="id" value="${equipment.id}">
							<div class="form-category">
								<div class="inline-group">
									<div class="form-group">
										<label>Equipment Name</label> <select name="equipmentName"
											id="equipmentName">
											<option value="camera">Camera</option>
											<option value="phone">Phone</option>
											<option value="greenscreen">Green Screen</option>
											<option value="laptop">Laptop</option>
											<option value="lighting">Lighting</option>
											<option value="background">Background</option>
										</select>
									</div>
									<div class="form-group">
										<label>Amount</label> <input type="number"
											id="equipmentAmount" name="equipmentAmount" required>
									</div>
								</div>
								<div class="inline-group">
									<div class="form-group">
										<label>Equipment Brand</label> <input type="text"
											id="equipmentBrand" name="equipmentBrand" required>
									</div>
								</div>
								<div class="form-buttons">
									<button type="submit" class="btn btn-success">Confirm</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="box">
					<div class="program-container">
						<h2>Request Equipment Application</h2>
						<div class="form-group">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Equipment Name</th>
										<th>Brand</th>
										<th>Amount</th>
										<th>Apply Date</th>
										<th>Approved Date</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty reqEquipments}">
									<c:forEach var="reqEquipment" items="${reqEquipments}">
										<tr>
											<td>${reqEquipment.name}</td>
											<td>${reqEquipment.brand}</td>
											<td>${reqEquipment.amount}</td>
											<td>${reqEquipment.applyDate}</td>
											<td>${reqEquipment.approvedDate}</td>
											<td>${reqEquipment.status}</td>
											<td><a href="requestEquipment/${reqEquipment.id}"
												class="details-btn">Cancel</a></td>

										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty reqEquipments}">
									<tr>
										<td colspan="6" style="text-align: center; color: red;">No
											request equipment application found.</td>
									</tr>
								</c:if>
								</tbody>
							</table>


						</div>
					</div>
				</div>
				<!--  end of main content -->
			</div>
		</div>
	</main>
</body>
</html>