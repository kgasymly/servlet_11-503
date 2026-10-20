package org.example.servlets;

import org.example.repository.UserRepository;
import org.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() { userRepository = new UserRepository(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type  = req.getParameter("type");
        String value = req.getParameter("value");

        if (type == null || type.isBlank()) {
            req.getRequestDispatcher("/jsp/select.jsp").forward(req, resp);
            return;
        }

        System.out.println("SelectServlet type=" + type + ", value=" + value);
        List<User> list;
        switch (type) {
            case "email": list = userRepository.selectByEmail(value); break;
            case "city":  list = userRepository.selectByCity(value);  break;
            case "phone": list = userRepository.selectByPhone(value); break;
            default: list = new ArrayList<>(); break;
        }
        for (User u : list) System.out.println("SelectServlet " + u);
        System.out.println("SelectServlet total = " + list.size());

        req.setAttribute("rows", list);
        req.getRequestDispatcher("/jsp/select.jsp").forward(req, resp);
    }
}