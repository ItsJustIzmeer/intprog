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
		// create default user
		User district = new DistrictOfficer("District", "district@district.com", "12345678", "0193515999",
				"Jalan Pendidikan, Taman Universiti", "Skudai", "Johor");

		User sadmin1 = new SchoolAdmin("Azman", "azman@admin.com", "12345678", "0193515096",
				"Jalan Pendidikan, Taman Universiti", "Skudai", "Johor");
		User sadmin2 = new SchoolAdmin("Osman", "osman@admin.com", "12345678", "0123515096",
				"Jalan Sri Skudai, Taman Sri Skudai", "Skudai", "Johor");
		User sadmin3 = new SchoolAdmin("Zafri", "zafri@admin.com", "12345678", "0193215096",
				"Jalan Hang Jebat, Mutiara Rini", "Skudai", "Johor");
		User sadmin4 = new SchoolAdmin("Fadz", "fadz@admin.com", "12345678", "0193515196", "Tanjung Pengelih", "Skudai",
				"Johor");
		User sadmin5 = new SchoolAdmin("Daus", "daus@admin.com", "12345678", "0193515196",
				"Jalan Taruka, Kampung Muafakat", "Skudai", "Johor");
		User sadmin6 = new SchoolAdmin("Thaqif", "thaqif@admin.com", "12345678", "0193215196",
				"Jalan Rentaka, Taman Sri Tebrau", "Skudai", "Johor");
		User sadmin7 = new SchoolAdmin("Hakim", "hakim@admin.com", "12345678", "0192212345",
				"Jalan Setia, Taman Permai", "Skudai", "Johor");
		User sadmin8 = new SchoolAdmin("Faizal", "faizal@admin.com", "12345678", "0193321456",
				"Jalan Kemajuan, Taman Jaya", "Skudai", "Johor");
		User sadmin9 = new SchoolAdmin("Rafidah", "rafidah@admin.com", "12345678", "0184412345",
				"Jalan Harmoni, Taman Sentosa", "Skudai", "Johor");
		User sadmin10 = new SchoolAdmin("Nadiah", "nadiah@admin.com", "12345678", "0175512345",
				"Jalan Sejahtera, Taman Melati", "Skudai", "Johor");
		User sadmin11 = new SchoolAdmin("Amran", "amran@admin.com", "12345678", "0166612345",
				"Jalan Makmur, Taman Indah", "Skudai", "Johor");
		User sadmin12 = new SchoolAdmin("Salina", "salina@admin.com", "12345678", "0157712345",
				"Jalan Ria, Taman Damai", "Skudai", "Johor");
		User sadmin13 = new SchoolAdmin("Yusof", "yusof@admin.com", "12345678", "0148812345",
				"Jalan Damai, Taman Cerdas", "Skudai", "Johor");
		User sadmin14 = new SchoolAdmin("Hafiza", "hafiza@admin.com", "12345678", "0139912345",
				"Jalan Bahagia, Taman Mesra", "Skudai", "Johor");
		User sadmin15 = new SchoolAdmin("Azhar", "azhar@admin.com", "12345678", "0121012345",
				"Jalan Makmur, Taman Ilmu", "Skudai", "Johor");
		User sadmin16 = new SchoolAdmin("Izzati", "izzati@admin.com", "12345678", "0112212345",
				"Jalan Lestari, Taman Aman", "Skudai", "Johor");

		User student1 = new Student("Aiman", "aiman@student.com", "password123", "0123456789",
				"Jalan Aman, Taman Sejahtera", "Skudai", "Johor");
		User student2 = new Student("Siti", "siti@student.com", "password123", "0131234567", "Jalan Flora, Taman Indah",
				"Skudai", "Johor");
		User student3 = new Student("Ahmad", "ahmad@student.com", "password123", "0149876543",
				"Jalan Kenari, Taman Murni", "Skudai", "Johor");
		User student4 = new Student("Nurul", "nurul@student.com", "password123", "0167654321",
				"Jalan Wawasan, Taman Permai", "Skudai", "Johor");
		User student5 = new Student("Hafiz", "hafiz@student.com", "password123", "0172345678",
				"Jalan Ilmu, Taman Cerdas", "Skudai", "Johor");
		User student6 = new Student("Farah", "farah@student.com", "password123", "0198765432",
				"Jalan Mawar, Taman Harmoni", "Skudai", "Johor");
		User student7 = new Student("Iqbal", "iqbal@student.com", "password123", "0183456789",
				"Jalan Melati, Taman Damai", "Skudai", "Johor");
		User student8 = new Student("Zulaikha", "zulaikha@student.com", "password123", "0192233445",
				"Jalan Cempaka, Taman Sentosa", "Skudai", "Johor");
		User student9 = new Student("Arif", "arif@student.com", "password123", "0171122334",
				"Jalan Dahlia, Taman Bahagia", "Skudai", "Johor");
		User student10 = new Student("Alya", "alya@student.com", "password123", "0169988776",
				"Jalan Teratai, Taman Mesra", "Skudai", "Johor");
		User student11 = new Student("Haziq", "haziq@student.com", "password123", "0187654321",
				"Jalan Sutera, Taman Jaya", "Skudai", "Johor");
		User student12 = new Student("Amira", "amira@student.com", "password123", "0196543210",
				"Jalan Anggerik, Taman Mega", "Skudai", "Johor");
		User student13 = new Student("Nizam", "nizam@student.com", "password123", "0113344556",
				"Jalan Amanah, Taman Sejahtera", "Skudai", "Johor");
		User student14 = new Student("Rina", "rina@student.com", "password123", "0113344667",
				"Jalan Harmoni, Taman Sentosa", "Skudai", "Johor");
		User student15 = new Student("Faris", "faris@student.com", "password123", "0113344778",
				"Jalan Indah, Taman Makmur", "Skudai", "Johor");
		User student16 = new Student("Lina", "lina@student.com", "password123", "0113344889",
				"Jalan Mesra, Taman Damai", "Skudai", "Johor");
		User student17 = new Student("Hakimi", "hakimi@student.com", "password123", "0113344990",
				"Jalan Ria, Taman Permai", "Skudai", "Johor");
		User student18 = new Student("Syafiq", "syafiq@student.com", "password123", "0114455667",
				"Jalan Damai, Taman Jaya", "Skudai", "Johor");
		User student19 = new Student("Sakinah", "sakinah@student.com", "password123", "0114455778",
				"Jalan Sentosa, Taman Cerdas", "Skudai", "Johor");
		User student20 = new Student("Haziqah", "haziqah@student.com", "password123", "0114455889",
				"Jalan Bahagia, Taman Bahagia", "Skudai", "Johor");
		User student21 = new Student("Anas", "anas@student.com", "password123", "0115566778",
				"Jalan Mesra, Taman Melati", "Skudai", "Johor");
		User student22 = new Student("Alia", "alia@student.com", "password123", "0115566889",
				"Jalan Permai, Taman Setia", "Skudai", "Johor");
		User student23 = new Student("Amir", "amir@student.com", "password123", "0116677889", "Jalan Makmur, Taman Ria",
				"Skudai", "Johor");
		User student24 = new Student("Afiq", "afiq@student.com", "password123", "0117788990",
				"Jalan Lestari, Taman Harmoni", "Skudai", "Johor");
		User student25 = new Student("Iman", "iman@student.com", "password123", "0118899001", "Jalan Aman, Taman Indah",
				"Skudai", "Johor");
		User student26 = new Student("Aina", "aina@student.com", "password123", "0119900112",
				"Jalan Sentosa, Taman Melati", "Skudai", "Johor");
		User student27 = new Student("Zahid", "zahid@student.com", "password123", "0120011223",
				"Jalan Mesra, Taman Cerdas", "Skudai", "Johor");
		User student28 = new Student("Sofea", "sofea@student.com", "password123", "0121122334",
				"Jalan Makmur, Taman Amanah", "Skudai", "Johor");
		User student29 = new Student("Fikri", "fikri@student.com", "password123", "0122233445",
				"Jalan Damai, Taman Permai", "Skudai", "Johor");
		User student30 = new Student("Fahmi", "fahmi@student.com", "password123", "0123344556",
				"Jalan Sentosa, Taman Mesra", "Skudai", "Johor");
		User student31 = new Student("Azharina", "azharina@student.com", "password123", "0124455667",
				"Jalan Bahagia, Taman Amanah", "Skudai", "Johor");
		User student32 = new Student("Harith", "harith@student.com", "password123", "0125566778",
				"Jalan Mesra, Taman Setia", "Skudai", "Johor");

		// Assign into school
		School school1 = SchoolDatabase.getSchoolDatabase().get(0);
		School school2 = SchoolDatabase.getSchoolDatabase().get(1);
		School school3 = SchoolDatabase.getSchoolDatabase().get(2);
		School school4 = SchoolDatabase.getSchoolDatabase().get(3);
		School school5 = SchoolDatabase.getSchoolDatabase().get(4);
		School school6 = SchoolDatabase.getSchoolDatabase().get(5);

		student1.setSchool(school1);
		student2.setSchool(school2);
		student3.setSchool(school3);
		student4.setSchool(school4);
		student5.setSchool(school5);
		student6.setSchool(school6);
		student7.setSchool(school1);
		student8.setSchool(school2);
		student9.setSchool(school3);
		student10.setSchool(school4);
		student11.setSchool(school5);
		student12.setSchool(school6);
		student13.setSchool(school1);
		student14.setSchool(school2);
		student15.setSchool(school3);
		student16.setSchool(school4);
		student17.setSchool(school5);
		student18.setSchool(school6);
		student19.setSchool(school1);
		student20.setSchool(school2);
		student21.setSchool(school3);
		student22.setSchool(school4);
		student23.setSchool(school5);
		student24.setSchool(school6);
		student25.setSchool(school1);
		student26.setSchool(school2);
		student27.setSchool(school3);
		student28.setSchool(school4);
		student29.setSchool(school5);
		student30.setSchool(school6);
		student31.setSchool(school1);
		student32.setSchool(school2);

		sadmin1.setSchool(school1);
		sadmin2.setSchool(school2);
		sadmin3.setSchool(school3);
		sadmin4.setSchool(school4);
		sadmin5.setSchool(school5);
		sadmin6.setSchool(school6);
		sadmin7.setSchool(school1);
		sadmin8.setSchool(school2);
		sadmin9.setSchool(school3);
		sadmin10.setSchool(school4);
		sadmin12.setSchool(school5);
		sadmin13.setSchool(school6);
		sadmin14.setSchool(school1);
		sadmin15.setSchool(school2);
		sadmin16.setSchool(school3);

		// Add into database
		userDatabase.put(district.getUername(), district);

		userDatabase.put(sadmin1.getUername(), sadmin1);
		userDatabase.put(sadmin2.getUername(), sadmin2);
		userDatabase.put(sadmin3.getUername(), sadmin3);
		userDatabase.put(sadmin4.getUername(), sadmin4);
		userDatabase.put(sadmin5.getUername(), sadmin5);
		userDatabase.put(sadmin6.getUername(), sadmin6);
		userDatabase.put(sadmin7.getUername(), sadmin7);
		userDatabase.put(sadmin8.getUername(), sadmin8);
		userDatabase.put(sadmin9.getUername(), sadmin9);
		userDatabase.put(sadmin10.getUername(), sadmin10);
		userDatabase.put(sadmin11.getUername(), sadmin11);
		userDatabase.put(sadmin12.getUername(), sadmin12);
		userDatabase.put(sadmin13.getUername(), sadmin13);
		userDatabase.put(sadmin14.getUername(), sadmin14);
		userDatabase.put(sadmin15.getUername(), sadmin15);
		userDatabase.put(sadmin16.getUername(), sadmin16);

		userDatabase.put(student1.getUername(), student1);
		userDatabase.put(student2.getUername(), student2);
		userDatabase.put(student3.getUername(), student3);
		userDatabase.put(student4.getUername(), student4);
		userDatabase.put(student5.getUername(), student5);
		userDatabase.put(student6.getUername(), student6);
		userDatabase.put(student7.getUername(), student7);
		userDatabase.put(student8.getUername(), student8);
		userDatabase.put(student9.getUername(), student9);
		userDatabase.put(student10.getUername(), student10);
		userDatabase.put(student11.getUername(), student11);
		userDatabase.put(student12.getUername(), student12);
		userDatabase.put(student13.getUername(), student13);
		userDatabase.put(student14.getUername(), student14);
		userDatabase.put(student15.getUername(), student15);
		userDatabase.put(student16.getUername(), student16);
		userDatabase.put(student17.getUername(), student17);
		userDatabase.put(student18.getUername(), student18);
		userDatabase.put(student19.getUername(), student19);
		userDatabase.put(student20.getUername(), student20);
		userDatabase.put(student21.getUername(), student21);
		userDatabase.put(student22.getUername(), student22);
		userDatabase.put(student23.getUername(), student23);
		userDatabase.put(student24.getUername(), student24);
		userDatabase.put(student25.getUername(), student25);
		userDatabase.put(student26.getUername(), student26);
		userDatabase.put(student27.getUername(), student27);
		userDatabase.put(student28.getUername(), student28);
		userDatabase.put(student29.getUername(), student29);
		userDatabase.put(student30.getUername(), student30);
		userDatabase.put(student31.getUername(), student31);
		userDatabase.put(student32.getUername(), student32);

	}

	@PostMapping("/sign-up")
	public ModelAndView handleSignUp(@ModelAttribute("user") User user) {
		// Can only create student account in login
		ModelAndView model = new ModelAndView("login");

		if (user.getUsername() != null && user.getPassword() != null && user.getEmail() != null) {
			String email = user.getEmail();
			String username = user.getUername();

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
						&& existingUser.getPassword().equalsIgnoreCase(user.getPassword())
						&& existingUser.getStatus().equals("A")) {
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
		case "school admin":
			School targetSchool = currentUser.getSchool();
			for (User user : userDatabase.values()) {
				if (user.getSchool().getId().equals(targetSchool.getId())) {
					userList.add(user);
				}
			}
			break;

		case "district officer":
			for (User user : userDatabase.values()) {
				userList.add(user);
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
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		String school = request.getParameter("school");

		return userList.stream()
				.filter(user -> (username == null || username.isEmpty() || user.getUsername().contains(username)))
				.filter(user -> (email == null || email.isEmpty() || user.getEmail().contains(email)))
				.filter(user -> (status == null || status.isEmpty() || user.getStatus().equalsIgnoreCase(status)))
				.filter(user -> (role == null || role.isEmpty() || user.getRole().equalsIgnoreCase(role)))
				.filter(user -> (phoneNum == null || phoneNum.isEmpty() || user.getPhoneNum().equalsIgnoreCase(phoneNum)
						|| user.getPhoneNum().startsWith(phoneNum)))
				.filter(user -> (address == null || address.isEmpty() || user.getAddress().equalsIgnoreCase(address)
						|| user.getAddress().toLowerCase().contains(address.toLowerCase())))
				.filter(user -> (district == null || district.isEmpty()
						|| user.getDistrict().equalsIgnoreCase(district)))
				.filter(user -> (state == null || state.isEmpty() || user.getState().equalsIgnoreCase(state)))
				.filter(user -> (school == null || school.isEmpty() 
                || (user.getSchool() != null && user.getSchool().getCode() != null 
                    && user.getSchool().getCode().equals(school))))
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
		return "";
	}

	public Boolean createUser(String role, String username, String email, String password, String phoneNum,
			String address, String district, String state, School school) {
		switch (role) {
		case "student":
			Student newUser = new Student(username, email, password, phoneNum, address, district, state);
			newUser.setSchool(school);
			userDatabase.put(newUser.getUername(), newUser);
			break;
		case "school admin":
			SchoolAdmin newUser2 = new SchoolAdmin(username, email, password, phoneNum, address, district, state);
			newUser2.setSchool(school);
			userDatabase.put(newUser2.getUername(), newUser2);
			break;
		case "district officer":
			DistrictOfficer newUser3 = new DistrictOfficer(username, email, password, phoneNum, address, district,
					state);
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
		for (User existingUser : userDatabase.values()) {
			if (existingUser.getId().equalsIgnoreCase(id)) {
				if (existingUser.getRole().equals("A")) {
					int tot = existingUser.getSchool().getTotalTeacher() - 1;
					existingUser.getSchool().setTotalTeacher(tot);
				} else if (existingUser.getRole().equals("S")) {
					int tot = existingUser.getSchool().getTotalStudent() - 1;
					existingUser.getSchool().setTotalStudent(tot);
				}
				userDatabase.values().remove(existingUser);
				error_msg = "";
				break;
			}
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
