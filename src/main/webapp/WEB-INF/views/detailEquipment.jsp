<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Details</title>
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
			<div class="maintitle">Equipment / Equipment Details</div>
			<div class="main-container">
				<div class="box">
					<div class="form-container">
						<form id="editEventForm" method="post"
							action="<c:url value='/editEvent'/>">
							<input type="hidden" name="id" value="${equipment.id}">

							<!-- Event Details -->
							<div class="form-category">
								<h3>Event Details</h3>
								<div class="form-group">
									<label for="eventName">Equipment ID</label> <input type="text"
										id="eventName" name="eventName" value="${equipment.equipmentId}" required>
								</div>
								<div class="inline-group">
									<div class="form-group">
										<label for="startDate">Start Date</label> <input type="date"
											id="startDate" name="startDate" value="${equipment.name}"
											required>
									</div>
									<div class="form-group">
										<label for="endDate">End Date</label> <input type="date"
											id="endDate" name="endDate" value="${equipment.brand}" required>
									</div>
								</div>
								<div class="form-group">
									<label for="eventTime">Time of Event</label> <input type="text"
										id="eventTime" name="eventTime" value="${equipment.amount}" required>
								</div>
							</div>
							
							<!-- Form Buttons -->
							<div class="form-buttons">
								<button type="submit" class="btn btn-success">Update</button>
								<button type="button" class="btn btn-danger"
									onclick="confirmDelete('${equipment.id}')">Delete</button>
								<a href="<%=request.getContextPath()%>/Equipment" id="cancelbtn"
									class="btn btn-secondary">Cancel</a>
							</div>
						</form>
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
