package com.inkspac3.course.servlet;

import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Order;
import com.inkspac3.course.service.OrderService;
import com.inkspac3.course.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "orders", urlPatterns = "/orders")
public class FindUserOrdersServlet extends HttpServlet {
  private static final Logger logger = LogManager.getLogger();
  private final OrderService orderService = new OrderServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("userId") == null) {
      resp.sendRedirect("/login");
      return;
    }

    Long userId = (Long) session.getAttribute("userId");
    try {
      List<Order> orders = orderService.getUserOrders(userId);
      req.setAttribute("orders", orders);

    } catch (ServiceException e) {
      throw new ServletException("Cannot load orders", e);
    }
    req.getRequestDispatcher("/pages/userOrders.jsp").forward(req, resp);
  }
}

