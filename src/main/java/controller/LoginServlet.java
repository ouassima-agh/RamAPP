package controller;


import java.io.IOException;

import dao.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Client;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Object user = LoginDAO.authenticateUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user instanceof Client) {
                response.sendRedirect("dashboard_client.jsp"); // Redirection pour les clients
            } else if (user instanceof Admin) {
                response.sendRedirect("dashboard_admin.jsp"); // Redirection pour les administrateurs
            }
        } else {
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
