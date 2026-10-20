package org.example.servlets;

import org.example.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/save")
public class SaveFormServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() { userRepository = new UserRepository(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int count = userRepository.getUsersToAdd();
        System.out.println("SaveFormServlet count = " + count);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/jsp/save.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {}
}