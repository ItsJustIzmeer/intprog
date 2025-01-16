<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>School Details</title>
<link rel="stylesheet"
	href="<c:url value='/resources/font-awesome-4.7.0/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/systemStyle.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<!-- Include navbar -->
	<jsp:include page="navbar.jsp" />
	<main>
		<!-- Include sidebar -->
		<jsp:include page="sidenav.jsp" />

		<!-- Main content -->
		<div class="main-body" style="width: -webkit-fill-available;">
			<div class="maintitle">
				<c:if test="${sessionScope.user.role == 'student'}">School / School Details</c:if>
				<c:if test="${sessionScope.user.role == 'district officer'}">Manage School / School Details</c:if>
			</div>
			<div class="main-container">
				<div class="box">
					<div class="program-container">
						<div class="form-container">
							<div class="image-container">
								<img src="<c:url value='/resources/img/smktu.png'/>"
									alt="School Logo" class="image-logo"> <img
									src="<c:url value=''/>" alt="TVPSS Logo" class="image-logo">
							</div>
							<form id="editScoolForm" method="post"
								action="<c:url value='/editSchool'/>">
								<input type="hidden" name="id" value="${event.id}">

								<!-- School Details -->
								<div class="form-category">
									<div class="inline-group">
										<div class="form-group">
											<label for="schoolName">School Name</label> <input
												type="text" id="schoolName" name="schoolName"
												value="${school.name}" required disabled>
										</div>
										<div class="form-group">
											<label for="schoolCode">School Code</label> <input
												type="text" id="schoolCode" name="schoolCode"
												value="${school.code}" required disabled>
										</div>
									</div>
									<div class="inline-group">
										<div class="form-group">
											<label for="schoolType">School type</label> <input
												type="text" id="schoolType" name="schoolType"
												value="${school.type}" required disabled>
										</div>
										<div class="form-group">
											<label for="schoolVersion">School Version</label> <input
												type="text" id="schoolVersion" name="schoolVersion"
												value="${school.version}" required disabled>
										</div>
									</div>
									<div class="inline-group">
										<div class="form-group">
											<label for="schoolTotalStudent">Total Students</label> <input
												type="text" id="schoolTotalStudent"
												name="schoolTotalStudent" value="${school.totalStudent}"
												required>
										</div>
										<div class="form-group">
											<label for="schoolTotalTeacher">Total Teachers</label> <input
												type="text" id="schoolTotalTeacher"
												name="schoolTotalTeacher" value="${school.totalTeacher}"
												required>
										</div>
									</div>
									<div class="form-group">
										<label for="schoolHead">Headmaster Name</label> <input
											type="text" id="schoolHead" name="schoolHead"
											value="${school.head}" required>
									</div>

									<div class="form-group">
										<label for="schoolPhoneNum">Phone Number</label> <input
											type="number" id="schoolPhoneNum" name="schoolPhoneNum"
											value="${school.name}" required>
									</div>
									<div class="form-group">

										<label for="schoolAddress">Address</label>
										<textarea id="schoolAddress" name="schoolAddress" required>${school.address}</textarea>
									</div>
									<div class="inline-group">
										<div class="form-group">
											<label for="schoolDistrict">District</label> <input
												type="text" id="schoolDistrict" name="schoolDistrict"
												value="${school.district}" required>
										</div>
										<div class="form-group">
											<label for="schoolState">State</label> <input type="text"
												id="schoolState" name="schoolState" value="${school.state}"
												required>
										</div>
									</div>
									<div class="form-buttons">
										<c:if test="${sessionScope.user.role == 'student'}">
											<button type="submit" class="btn btn-success">Update</button>
											<a href="<%=request.getContextPath()%>/School" id="cancelbtn"
												class="btn btn-secondary">Cancel</a>
										</c:if>
										<c:if test="${sessionScope.user.role == 'district officer'}">
											<button type="submit" class="btn btn-success">Update</button>
											<a href="<%=request.getContextPath()%>/ManageSchool"
												id="cancelbtn" class="btn btn-secondary">Cancel</a>
										</c:if>
									</div>
								</div>
							</form>
						</div>
						<div class="program-container"></div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script>
		document
				.querySelector('#cancelbtn')
				.addEventListener(
						'click',
						function(event) {
							if (!confirm("Are you sure you want to cancel? Unsaved changes will be lost.")) {
								event.preventDefault(); // Prevents navigation if user cancels
							}
						});

		$(document).ready(
				function() {
					$('input[name="participants"]').change(
							function() {
								$('#participantLimit').prop('disabled',
										$(this).val() !== 'limit');
							});

					$('input[name="eventType"]').change(function() {
						if ($(this).val() === 'physical') {
							$('#eventLocation').prop('disabled', false);
							$('#eventPlatform').prop('disabled', true);
						} else {
							$('#eventLocation').prop('disabled', true);
							$('#eventPlatform').prop('disabled', false);
						}
					});
				});

		function confirmDelete(eventId) {
			const confirmDelete = window
					.confirm("Are you sure you want to delete this event?");
			if (!confirmDelete) {
				return;
			}

			const hiddenForm = document.createElement('form');
			hiddenForm.setAttribute('action', '<c:url value="/deleteEvent"/>');
			hiddenForm.setAttribute('method', 'post');

			const inputField = document.createElement('input');
			inputField.name = "id";
			inputField.value = eventId;
			hiddenForm.appendChild(inputField);

			document.body.appendChild(hiddenForm);
			hiddenForm.submit();
		}
	</script>
</body>
</html>
