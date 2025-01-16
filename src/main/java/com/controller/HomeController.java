package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.modal.Event;
import com.modal.Equipment;
import com.modal.Page;
import com.modal.School;
import com.modal.Student;
import com.modal.User;
import com.repository.EquipmentDatabase;
import com.repository.EventDatabase;
import com.repository.PageDatabase;
import com.repository.SchoolDatabase;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private UserController userController; // Autowire UserController

	@GetMapping("/userMng")
	public ModelAndView userManagement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Authorization
		String view = AuthUtil.checkAndRedirect(request, response);
		if (view.equals("")) {
			view = "userMng";
		}

		ModelAndView modelAndView = new ModelAndView(view);
		// search for permission
		modelAndView.addObject("read", userController.checkPermission(request, view, "Read"));
		modelAndView.addObject("create", userController.checkPermission(request, view, "Create"));
		modelAndView.addObject("update", userController.checkPermission(request, view, "Update"));
		modelAndView.addObject("delete", userController.checkPermission(request, view, "Delete"));

		// get data list
		modelAndView.addObject("userList", userController.getUserGridTable(request));

		return modelAndView;
	}

	@PostMapping(value = "/userMng/{action}")
	public ModelAndView userManagementAction(@PathVariable String action, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("model") ModelMap model) throws IOException {

		String view = "userMng";

		// Update permissions in the model
		model.addAttribute("read", userController.checkPermission(request, view, "Read"));
		model.addAttribute("create", userController.checkPermission(request, view, "Create"));
		model.addAttribute("update", userController.checkPermission(request, view, "Update"));
		model.addAttribute("delete", userController.checkPermission(request, view, "Delete"));

		// Extract parameters and update the model with them
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		String role = request.getParameter("role");
		int input = 0;
		String error_msg = "";
		String success_msg = "";

		// Process action and update the user list in the model
		switch (action) {
		case "create":
			error_msg = userController.validateExistUser(username, email, role);
			if (error_msg == "") {
				if (!userController.createUser(role, username, email, password)) {
					model.addAttribute("error_msg", "Error on creating user");
				} else {
					success_msg = "User created into database successfully.";
					model.addAttribute("success_msg", success_msg);
				}
			} else {
				model.addAttribute("error_msg", error_msg);
			}
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		case "search":
			model.addAttribute("success_msg", "");
			model.addAttribute("error_msg", "");
			if (username != null) {
				model.addAttribute("username", username);
				input++;
			}
			if (email != null) {
				model.addAttribute("email", email);
				input++;
			}
			if (status != null) {
				model.addAttribute("status", status);
				input++;
			}
			if (role != null) {
				model.addAttribute("role", role);
				input++;
			}

			if (input > 0) {
				model.addAttribute("userList", userController.searchUser(request));
			} else {
				model.addAttribute("userList", userController.getUserGridTable(request));
			}
			break;
		case "update":
			String id = request.getParameter("id");
			error_msg = userController.updateUser(id, username, email, password, status);
			if (error_msg != "") {
				model.addAttribute("error_msg", error_msg);
			} else {
				success_msg = "Changes updated into database successfully.";
				model.addAttribute("success_msg", success_msg);
			}
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		case "delete":
			String dltid = request.getParameter("id");
			error_msg = userController.deleteUser(dltid);
			if (error_msg != "") {
				model.addAttribute("error_msg", error_msg);
			} else {
				success_msg = "User remove from  data successfully.";
				model.addAttribute("success_msg", success_msg);
			}
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		default:
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		}

		return new ModelAndView(view, model);
	}

	@GetMapping("/userPrm")
	public ModelAndView userPermission(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Authorization
		String view = AuthUtil.checkAndRedirect(request, response);
		if (view.equals("")) {
			view = "userPrm";
		}

		ModelAndView modelAndView = new ModelAndView(view);
		// search for permission
		modelAndView.addObject("read", userController.checkPermission(request, view, "Read"));
		modelAndView.addObject("create", userController.checkPermission(request, view, "Create"));
		modelAndView.addObject("update", userController.checkPermission(request, view, "Update"));
		modelAndView.addObject("delete", userController.checkPermission(request, view, "Delete"));

		// get data list
		modelAndView.addObject("userList", userController.getUserGridTable(request));

		return modelAndView;
	}

	@PostMapping("/userPrm/{action}")
	public ModelAndView userPermissionAction(@PathVariable String action, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("model") ModelMap model) throws IOException {

		String view = "userPrm";

		// Update permissions in the model
		model.addAttribute("read", userController.checkPermission(request, view, "Read"));
		model.addAttribute("create", userController.checkPermission(request, view, "Create"));
		model.addAttribute("update", userController.checkPermission(request, view, "Update"));
		model.addAttribute("delete", userController.checkPermission(request, view, "Delete"));

		// Extract parameters and update the model with them
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int input = 0;
		String error_msg = "";
		String success_msg = "";
		Map<String, Page> pageDatabase = PageDatabase.getPageDatabase();

		// Process action and update the user list in the model
		switch (action) {
		case "search":
			model.addAttribute("success_msg", "");
			model.addAttribute("error_msg", "");
			if (username != null) {
				model.addAttribute("username", username);
				input++;
			}
			if (email != null) {
				model.addAttribute("email", email);
				input++;
			}
			if (input > 0) {
				model.addAttribute("userList", userController.searchUser(request));
			} else {
				model.addAttribute("userList", userController.getUserGridTable(request));
			}
			break;
		case "get":
			String getId = request.getParameter("id");
			Map<Page, String[]> permissions = userController.getPermission(getId);

			model.addAttribute("username", userController.getUserNameById(getId));
			model.addAttribute("email", userController.getEmailById(getId));
			model.addAttribute("userId", getId);
			StringBuilder htmlTable = new StringBuilder();
			for (Map.Entry<String, Page> entry : pageDatabase.entrySet()) {
				Page page = entry.getValue();
				String[] perms = permissions.get(page);

				htmlTable.append("<tr>");
				htmlTable.append("<td>").append(page.getTitle()).append("</td>");
				htmlTable.append("<td><input type='checkbox' name='read'");
				if (perms != null && Arrays.asList(perms).contains("Read")) {
					htmlTable.append(" checked");
				}
				htmlTable.append("></td>");
				htmlTable.append("<td><input type='checkbox' name='create'");
				if (perms != null && Arrays.asList(perms).contains("Create")) {
					htmlTable.append(" checked");
				}
				htmlTable.append("></td>");
				htmlTable.append("<td><input type='checkbox' name='update'");
				if (perms != null && Arrays.asList(perms).contains("Update")) {
					htmlTable.append(" checked");
				}
				htmlTable.append("></td>");
				htmlTable.append("<td><input type='checkbox' name='delete'");
				if (perms != null && Arrays.asList(perms).contains("Delete")) {
					htmlTable.append(" checked");
				}
				htmlTable.append("></td>");
				htmlTable.append("</tr>");
			}
			model.addAttribute("tableRows", htmlTable.toString());
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		case "update":
			String uid = request.getParameter("id");
			Map<Page, String[]> permissionsMap = new HashMap<>();

			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();

				// Check if the parameter name contains '_read', '_create', '_update', or
				// '_delete'
				if (paramName.endsWith("_read")) {
					String pageName = paramName.replace("_read", "");
					Boolean readValue = Boolean.parseBoolean(request.getParameter(paramName));
					Boolean createValue = Boolean.parseBoolean(request.getParameter(pageName + "_create"));
					Boolean updateValue = Boolean.parseBoolean(request.getParameter(pageName + "_update"));
					Boolean deleteValue = Boolean.parseBoolean(request.getParameter(pageName + "_delete"));

					List<String> permissionList = new ArrayList<>();
					if (readValue)
						permissionList.add("Read");
					if (createValue)
						permissionList.add("Create");
					if (updateValue)
						permissionList.add("Update");
					if (deleteValue)
						permissionList.add("Delete");

					String[] crud = permissionList.toArray(new String[0]);

					for (Map.Entry<String, Page> entry : pageDatabase.entrySet()) {
						Page page = entry.getValue();
						if (page.getTitle().equals(pageName)) {
							permissionsMap.put(page, crud);
						}
					}

				}
			}
			error_msg = userController.setPermissionById(uid, permissionsMap);
			if (error_msg != "") {
				model.addAttribute("error_msg", error_msg);
			} else {
				success_msg = "Changes updated into database successfully.";
				model.addAttribute("success_msg", success_msg);
			}
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		default:
			model.addAttribute("userList", userController.getUserGridTable(request));
			break;
		}

		return new ModelAndView(view, model);
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		return modelAndView;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		return new ModelAndView("redirect:/"); // Redirect to login page
	}

	@GetMapping("/EventApplication")
	public ModelAndView eventApplication(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("EventApplication");
		return modelAndView;
	}

	@GetMapping("/ManageEventApp")
	public ModelAndView manageEventApp(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("ManageEventApp");
		return modelAndView;
	}

	@GetMapping("/Event")
	public ModelAndView event(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Event");
		List<Event> events = EventDatabase.getEventDatabase();
		modelAndView.addObject("events", events);
		return modelAndView;
	}

	@GetMapping("/ManageEquipment")
	public ModelAndView manageEquipment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("ManageEquipment");
		List<Equipment> equipments = EquipmentDatabase.getEquipmentDatabase();
		modelAndView.addObject("equipments", equipments);
		return modelAndView;
	}

	@GetMapping("/ManageRequestEquipment")
	public ModelAndView manageRequestEquipment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("ManageRequestEquipment");
		return modelAndView;
	}

	@GetMapping("/RequestEquipment")
	public ModelAndView requestEquipment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("RequestEquipment");
		return modelAndView;
	}

	@GetMapping("/LevelUpgrade")
	public ModelAndView levelUpgrade(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("LevelUpgrade");
		return modelAndView;
	}

	@GetMapping("/School")
	public ModelAndView school(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("School");
		List<School> schools = SchoolDatabase.getSchoolDatabase();
		modelAndView.addObject("schools" , schools);
		return modelAndView;
	}
	
	@GetMapping("/ManageSchool")
	public ModelAndView manageSchool(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("ManageSchool");
		List<School> schools = SchoolDatabase.getSchoolDatabase();
		modelAndView.addObject("schools" , schools);
		return modelAndView;
	}

	@GetMapping("/SchoolLevel")
	public ModelAndView schoolLevel(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("SchoolLevel");
		return modelAndView;
	}

	/*
	 * @GetMapping("/home") public ModelAndView home() { ModelAndView modelAndView =
	 * new ModelAndView("home"); modelAndView.addObject("message",
	 * "Welcome to the Spring Web App!"); return modelAndView; }
	 */
}