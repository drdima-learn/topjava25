package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");

        response.sendRedirect("users.jsp");
        //request.getRequestDispatcher("/users.jsp").forward(request,response);

    }
}
