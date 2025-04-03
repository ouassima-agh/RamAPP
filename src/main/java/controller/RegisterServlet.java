package controller;

import dao.RegisterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Client;

import java.io.IOException;
import java.sql.Date;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissance = request.getParameter("date_naissance");
        String sexe = request.getParameter("sexe");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Les mots de passe ne correspondent pas !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        boolean isAdmin = email.endsWith("@admin.com"); // Détection simple des administrateurs

        if (isAdmin) {
            // Créer un administrateur
            Admin admin = new Admin();
            admin.setNomAdmi(nom);
            admin.setPrenomAdmi(prenom);
            admin.setDateNaissance(Date.valueOf(dateNaissance));
            admin.setSexe(sexe);
            admin.setTelephone(telephone);
            admin.setEmailAdmi(email);
            admin.setMotDePass(password);

            if (RegisterDAO.registerAdmin(admin)) {
                response.sendRedirect("dashboard_admin.jsp"); // Rediriger après inscription réussie
            } else {
                request.setAttribute("errorMessage", "L'email est déjà utilisé !");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            // Créer un client
            Client client = new Client();
            client.setNomClient(nom);
            client.setPrenomClient(prenom);
            client.setEmailClient(email);
            client.setMotDePassClient(password);
            client.setTelClient(telephone);
            client.setSexe(sexe);
            client.setDateNaissance(Date.valueOf(dateNaissance));

            if (RegisterDAO.registerClient(client)) {
                response.sendRedirect("dashboard_client.jsp"); // Rediriger après inscription réussie
            } else {
                request.setAttribute("errorMessage", "L'email est déjà utilisé !");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }
}
