package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.modal.DistrictOfficer;
import com.modal.Page;
import com.modal.School;
import com.modal.SchoolAdmin;
import com.modal.Student;
import com.modal.User;
import com.repository.PageDatabase;
import com.repository.SchoolDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Map<String, User> userDatabase = new HashMap<>();

	// initialize the database when the system first run
	static {
		userDatabase.put("district", new DistrictOfficer("district", "district@district.com", "12345678"));
		userDatabase.put("ali", new SchoolAdmin("ali", "ali@admin.utm.my", "12345678"));
		userDatabase.put("aiman", new Student("aiman", "aiman@student.utm.my", "12345678"));

//		for (School school : SchoolDatabase.getSchoolDatabase().values()) {
//			if ("utm".contains(school.getSchoolShortName().toLowerCase())) {
//				school.addSchoolAdmin(userDatabase.get("ali"));
//				school.addStudent(userDatabase.get("aiman"));
//				break;
//			}
//		}
	}

	@PostMapping("/sign-up")
	public ModelAndView handleSignUp(@ModelAttribute("user") User user) {
		// Can only create student account in login
		ModelAndView model = new ModelAndView("login");

		if (user.getUsername() != null && user.getPassword() != null && user.getEmail() != null) {
			String email = user.getEmail();
			String username = user.getUername();
			School targetSchool = null;

			// Check if the username or email already exists in the userDatabase
			for (User existingUser : userDatabase.values()) {
				if (existingUser.getUsername().equalsIgnoreCase(username)) {
					model.addObject("message", "Username already exists. Please choose another username.");
					return model;
				}
				if (existingUser.getEmail().equalsIgnoreCase(email)) {
					model.addObject("message", "Email is already in use. Please use a different email.");
					return model;
				}
			}

			// Search for the school dynamically from the email domain
//			for (School school : SchoolDatabase.getSchoolDatabase().values()) {
//				if (email.contains(school.getSchoolShortName().toLowerCase())) {
//					targetSchool = school;
//					break;
//				}
//			}
//			if (targetSchool != null) {
//				user = new Student(user.getUsername(), email, user.getPassword());
//				userDatabase.put(user.getUsername(), user);
//				targetSchool.addStudent(user);
//				model.addObject("message", "Student account for " + targetSchool.getSchoolShortName()
//						+ " has created. You can login now.");
//			} else {
//				model.addObject("message", "School not recognized for the email domain.");
//			}

		} else {
			model.addObject("message", "PLease fill in all the required field.");
		}
		return model;
	}

	@PostMapping("/sign-in")
	public ModelAndView handleSignIn(HttpServletRequest request, @ModelAttribute("user") User user) {
		if (user.getUsername() != null && user.getPassword() != null) {
			for (User existingUser : userDatabase.values()) {
				if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())
						&& existingUser.getPassword().equalsIgnoreCase(user.getPassword())) {
					request.getSession().setAttribute("user", existingUser);
					request.getSession().setAttribute("currPage", PageDatabase.getPageDatabase().get("dashboard"));
					return new ModelAndView("redirect:/dashboard");
				}
			}
			ModelAndView model = new ModelAndView("login");
			model.addObject("message", "No user found.");
			return model;
		} else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("message", "Please fill in all the required field.");
			return model;
		}
	}

	public List<User> getUserGridTable(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");
		List<User> userList = new ArrayList<>();

		switch (currentUser.getRole()) {
//		case "school admin":
//			String email = currentUser.getEmail();
//			School targetSchool = null;
//			for (School school : SchoolDatabase.getSchoolDatabase().values()) {
//				if (email.contains(school.getSchoolShortName().toLowerCase())) {
//					targetSchool = school;
//					break;
//				}
//			}
//			userList.addAll(targetSchool.getSchoolAdmin());
//			userList.addAll(targetSchool.getStudents());
//
//			break;

		case "district officer":
			// Add each user to the list
			for (Map.Entry<String, User> entry : userDatabase.entrySet()) {
				User user = entry.getValue(); // Get the value (User) from the map
				userList.add(user); // Add the User to the list
			}
			break;

		default:
			throw new RuntimeException("Invalid user role.");
		}
		return userList;
	}

	public List<User> searchUser(HttpServletRequest request) {
		List<User> userList = getUserGridTable(request);

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String role = request.getParameter("role");

		return userList.stream()
				.filter(user -> (username == null || username.isEmpty() || user.getUsername().contains(username)))
				.filter(user -> (email == null || email.isEmpty() || user.getEmail().contains(email)))
				.filter(user -> (status == null || status.isEmpty() || user.getStatus().equalsIgnoreCase(status)))
				.filter(user -> (role == null || role.isEmpty() || user.getRole().equalsIgnoreCase(role)))
				.collect(Collectors.toList());
	}

	public Boolean checkPermission(HttpServletRequest request, String pageName, String action) {
		// Retrieve the logged-in user from the session
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		Map<Page, String[]> permissions = user.getPermissions();
		for (Map.Entry<Page, String[]> entry : permissions.entrySet()) {
			Page page = entry.getKey();
			String[] actions = entry.getValue();

			// Check if the page matches and if 'Read' permission exists
			if (page.getFilename().equalsIgnoreCase(pageName)) {
				for (String perAction : actions) {
					if (action.equalsIgnoreCase(perAction)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String validateExistUser(String username, String email, String role) {
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getUsername().equalsIgnoreCase(username)) {
				return "Username already exist! Please use another username.";
			}
			if (existingUser.getEmail().equalsIgnoreCase(email)) {
				return "Email already exist! Email please use another email";
			}
		}
//		if (role == "A" || role == "S") {
//			for (School school : SchoolDatabase.getSchoolDatabase().values()) {
//				if (email.contains(school.getSchoolShortName().toLowerCase())) {
//					return "";
//				}
//			}
//		} else {
//			return "";
//		}
		return "School not exist! Check if the school detected first before submit.";
	}

	public Boolean createUser(String role, String username, String email, String password) {
		School targetSchool = null;
//		for (School school : SchoolDatabase.getSchoolDatabase().values()) {
//			if (email.contains(school.getSchoolShortName().toLowerCase())) {
//				targetSchool = school;
//				break;
//			}
//		}

		switch (role) {
		case "S":
//			if (targetSchool != null) {
//				Student newUser = new Student(username, email, password);
//				targetSchool.addStudent(newUser);
//				userDatabase.put(newUser.getUername(), newUser);
//			} else {
//				return false;
//			}
			break;
		case "A":
//			if (targetSchool != null) {
//				SchoolAdmin newUser2 = new SchoolAdmin(username, email, password);
//				targetSchool.addSchoolAdmin(newUser2);
//				userDatabase.put(newUser2.getUername(), newUser2);
//			} else {
//				return false;
//			}
			break;
		case "D":
			DistrictOfficer newUser3 = new DistrictOfficer(username, email, password);
			userDatabase.put(newUser3.getUername(), newUser3);
			break;
		default:
			return false;
		}

		return true;
	}

	public String updateUser(String id, String username, String email, String password, String status) {
		String error_msg = "No User Found.";
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				existingUser.setUsername(username);
				existingUser.setEmail(email);
				if (password != "")
					existingUser.setPassword(password);
				existingUser.setStatus(status);
				error_msg = "";
			}
		}
		return error_msg;
	}

	public String deleteUser(String id) {
		String error_msg = "No User Found";
		User userToDelete = null;
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				userToDelete = existingUser;
				break;
			}
		}
		// Remove the user if found
		if (userToDelete != null) {
			userDatabase.values().remove(userToDelete);
			error_msg = "";
		}
		return error_msg;
	}

	public Map<Page, String[]> getPermission(String id) {
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				return existingUser.getPermissions();
			}
		}
		return null;
	}

	public String setPermissionById(String id, Map<Page, String[]> permissionsMap) {
		String error_msg = "Error on setting permission /n";
		if (permissionsMap == null) {
			return "PermissionMap is null";
		}

		// Debug: Check the passed ID
		error_msg += "Looking for user with ID: " + id + "/n";

		// Loop through users to find the matching ID
		for (User existingUser : userDatabase.values()) {
			error_msg += "Checking user with ID: " + existingUser.getId() + "/n"; // Debug: Check current user ID
			if (existingUser.getId().equals(id)) { // Case-sensitive comparison
				existingUser.setPermissions(permissionsMap); // Set the permissions
				return ""; // Success
			}
		}
		return error_msg;
	}

	public String getUserNameById(String id) {
		String error_msg = "Error on getting Username";
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				return existingUser.getUsername();
			}
		}
		return error_msg;
	}

	public String getEmailById(String id) {
		String error_msg = "Error on getting Email";
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				return existingUser.getEmail();
			}
		}
		return error_msg;
	}
}
