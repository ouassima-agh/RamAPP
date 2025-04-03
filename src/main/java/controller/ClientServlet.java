package controller;


import dao.ClientDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
    private ClientDAO clientDAO;

    public void init() {
        clientDAO = new ClientDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        List<Client> clients;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            clients = clientDAO.searchClients(searchQuery);
        } else {
            clients = clientDAO.getAllClients();
        }

        request.setAttribute("clients", clients);


        if ("true".equals(request.getParameter("ajax"))) { // Si c'est une requête AJAX
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("clients.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("clients.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String nom = request.getParameter("nomClient");
                String prenom = request.getParameter("prenomClient");
                String email = request.getParameter("emailClient");
                String motDePass = request.getParameter("motDePassClient");
                String tel = request.getParameter("telClient");
                String sexe = request.getParameter("sexe");
                Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));

                Client client = new Client();
                client.setNomClient(nom);
                client.setPrenomClient(prenom);
                client.setEmailClient(email);
                client.setMotDePassClient(motDePass);
                client.setTelClient(tel);
                client.setSexe(sexe);
                client.setDateNaissance(dateNaissance);

                clientDAO.addClient(client);
            } else if ("delete".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                clientDAO.deleteClient(id);
            } else if ("update".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                Client client = clientDAO.getClientById(id);

                if (client != null) {
                    client.setNomClient(request.getParameter("nomClient"));
                    client.setPrenomClient(request.getParameter("prenomClient"));
                    client.setEmailClient(request.getParameter("emailClient"));
                    client.setMotDePassClient(request.getParameter("motDePassClient"));
                    client.setTelClient(request.getParameter("telClient"));
                    client.setDateNaissance(Date.valueOf(request.getParameter("dateNaissance")));
                    client.setSexe(request.getParameter("sexe"));

                    clientDAO.updateClient(client);
                }
            }

            // utilise request.getContextPath() pour rediriger correctement
            response.sendRedirect(request.getContextPath() + "/ClientServlet");

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 Cela affichera les erreurs dans la console de Tomcat
            response.sendRedirect("error.jsp"); // 🔥 Rediriger vers une page d'erreur si une exception survient
        }
    }

}
