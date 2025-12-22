package com.inkspac3.course.servlet;

import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Beat;
import com.inkspac3.course.model.Order;
import com.inkspac3.course.service.BeatService;
import com.inkspac3.course.service.impl.BeatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "beats", urlPatterns = "/beats")
public class FindAvailableBeatsServlet extends HttpServlet {
  private final BeatService beatService = new BeatServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("userId") == null) {
      resp.sendRedirect("/login");
      return;
    }

    try {
      List<Beat> beats = beatService.getAvailableBeats();
      req.setAttribute("beats", beats);

    } catch (ServiceException e) {
      throw new ServletException("Cannot load beats", e);
    }
    req.getRequestDispatcher("/pages/dashboard.jsp").forward(req, resp);
  }
}
