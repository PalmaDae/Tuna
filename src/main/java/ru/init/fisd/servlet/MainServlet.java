package ru.init.fisd;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/main.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Кнопка нажата");

        String name = req.getParameter("name");
        String age = req.getParameter("age");

        HttpSession session = req.getSession();



        session.setAttribute("name", name);

        User user = new User(name, age);

        session.setAttribute("user", user);

        Cookie cok = new Cookie("cooka", "15");

        resp.sendRedirect("/profile");
    }
}
