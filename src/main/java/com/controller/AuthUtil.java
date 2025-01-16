package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modal.User;

public class AuthUtil {

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        return currentUser != null;
    }

    public static String checkAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!isUserLoggedIn(request)) {
            request.getSession(false).setAttribute("message", "You must log in to access this page.");
            return "redirect:/";
        }
        return "";
    }
}