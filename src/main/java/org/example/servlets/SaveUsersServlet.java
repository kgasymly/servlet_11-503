package org.example.servlets;

import org.example.repository.UserRepository;
import org.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/saveUsers")
public class SaveUsersServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() { userRepository = new UserRepository(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String countStr = req.getParameter("count");
        System.out.println("SaveUsersServlet count param = " + countStr);
        if (countStr == null || countStr.isBlank()) {
            resp.sendError(400, "count is missing");
            return;
        }
        int count = Integer.parseInt(countStr);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name    = req.getParameter("name_"    + i);
            String surname = req.getParameter("surname_" + i);
            String ageStr  = req.getParameter("age_"     + i);
            String email   = req.getParameter("email_"   + i);
            String city    = req.getParameter("city_"    + i);
            String phone   = req.getParameter("phone_"   + i);

            if (name == null || name.isBlank()) continue;
            Integer age = (ageStr == null || ageStr.isBlank()) ? null : Integer.valueOf(ageStr);

            User u = User.builder()
                    .name(name).surname(surname).age(age)
                    .email(email).city(city).phone(phone)
                    .build();
            users.add(u);
            System.out.println("SaveUsersServlet user[" + i + "] = " + u);
        }

        userRepository.saveUsers(users);
        System.out.println("SaveUsersServlet saved " + users.size() + " users");

        // редирект на заглушку
        resp.sendRedirect(req.getContextPath() + "/jsp/success.jsp");
    }
}