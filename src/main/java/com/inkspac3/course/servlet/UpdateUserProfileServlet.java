package com.inkspac3.course.servlet;

import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.User;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
import com.inkspac3.course.util.PasswordEncoder;
import com.inkspac3.course.util.validation.RegexPattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "updateProfile", urlPatterns = "/updateProfile")
public class UpdateUserProfileServlet extends HttpServlet {
  private static final Logger logger = LogManager.getLogger();
  private final UserService userService = new UserServiceImpl();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
      resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
      return;
    }

    User currentUser = (User) session.getAttribute("user");
    String newEmail = req.getParameter("email");
    String newPassword = req.getParameter("password");
    String confirmPassword = req.getParameter("confirmPassword");

    try {
      if (newEmail == null || !newEmail.matches(RegexPattern.EMAIL)) {
        req.setAttribute("error", "Invalid format of email");
        req.getRequestDispatcher("/pages/userProfile.jsp").forward(req, resp);
        return;
      }

      if (newPassword != null && !newPassword.isEmpty()) {
        if (!newPassword.matches(RegexPattern.PASSWORD)) {
          req.setAttribute("error", "Password must be at least 8 characters and include at least one digit, letter and special symbol");
          req.getRequestDispatcher("/pages/userProfile.jsp").forward(req, resp);
          return;
        }
        if (!newPassword.equals(confirmPassword)) {
          req.setAttribute("error", "Passwords do not match");
          req.getRequestDispatcher("/pages/userProfile.jsp").forward(req, resp);
          return;
        }
        currentUser.setPasswordHash(PasswordEncoder.encode(newPassword));
      }

      currentUser.setEmail(newEmail);
      boolean updated = userService.updateUser(currentUser);

      if (updated) {
        session.setAttribute("user", currentUser);
        req.setAttribute("message", "Data updated successfully");
      } else {
        req.setAttribute("error", "Unable to update data");
      }

    } catch (ServiceException e) {
      logger.error("Error while updating user profile", e);
      req.setAttribute("error", "Error while updating user profile");
    }

    req.getRequestDispatcher("/pages/userProfile.jsp").forward(req, resp);
  }
}
