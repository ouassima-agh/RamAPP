<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Vol" %>
<%
  List<Vol> flights = (List<Vol>) request.getAttribute("flights");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des Vols </title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: "Segoe UI", sans-serif;
      color: white;
      background: rgba(0, 0, 0, 0.85);
    }

    .background-slider {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      z-index: -1;
    }

    .slide {
      position: absolute;
      width: 100%;
      height: 100%;
      background-size: cover;
      background-position: center;
      opacity: 0;
      transition: opacity 1s ease-in-out;
    }

    .slide.active {
      opacity: 1;
    }

    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 15px 30px;
      background-color: rgba(0, 0, 0, 0.7);
      box-shadow: 0 2px 10px rgba(0,0,0,0.6);
    }

    .logo {
      height: 50px;
    }

    .search-bar input {
      border-radius: 20px;
      border: none;
      padding-left: 30px;
    }

    .search-bar {
      position: relative;
      width: 300px;
    }

    .search-bar i {
      position: absolute;
      left: 10px;
      top: 10px;
      color: #999;
    }

    .user-profile {
      display: flex;
      align-items: center;
      gap: 15px;
      color: white;
    }

    .navigation {
      display: flex;
      justify-content: center;
      background-color: rgba(0, 0, 0, 0.6);
      padding: 15px 0;
      margin-bottom: 30px;
      border-top: 1px solid #444;
      border-bottom: 1px solid #444;
    }

    .nav-item {
      margin: 0 20px;
      color: white;
      text-decoration: none;
      font-weight: bold;
      transition: 0.3s;
    }

    .nav-item:hover,
    .nav-item.active {
      color: #00aaff;
      border-bottom: 2px solid #00aaff;
    }

    .container {
      width: 60%;
      max-width: 900px;
      background: rgba(0, 0, 0, 0.75);
      padding: 30px;
      border-radius: 20px;
      box-shadow: 0px 10px 50px rgba(0, 0, 0, 0.7);
      backdrop-filter: blur(15px);
      transition: all 0.3s ease-in-out;
      margin: 0 auto;
    }

    .container:hover {
      transform: scale(1.02);
    }

    h1 {
      text-align: center;
      font-size: 2.5rem;
      font-weight: 600;
      color: #4A90E2;
      margin-bottom: 30px;
      text-transform: uppercase;
      letter-spacing: 1.5px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      border-radius: 8px;
      background: rgba(0, 0, 0, 0.85);
      box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.3);
      transition: all 0.3s ease;
    }

    table:hover {
      transform: scale(1.01);
    }

    th, td {
      padding: 12px;
      text-align: center;
      border-bottom: 1px solid #ddd;
      font-size: 1.05rem;
      font-weight: 500;
    }

    th {
      background-color: #4A90E2;
      color: white;
      text-transform: uppercase;
      font-weight: 600;
    }

    td {
      background-color: rgba(74, 144, 226, 0.1);
    }

    tr:nth-child(even) td {
      background-color: rgba(74, 144, 226, 0.15);
    }

    tr:hover {
      background: rgba(216, 166, 240, 0.3);
      transition: background-color 0.3s ease;
    }

    .btn {
      padding: 12px 20px;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      font-size: 1rem;
      font-weight: 600;
      text-transform: uppercase;
      transition: all 0.3s ease-in-out;
      border: 2px solid #4A90E2;
    }

    .btn-delete {
      background: #4A90E2;
      color: white;
    }

    .btn-delete:hover {
      background: #357ABD;
      transform: scale(1.1);
      box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
    }

    .btn-add {
      background: #4A90E2;
      color: white;
      padding: 15px 30px;
      display: block;
      width: max-content;
      margin: 30px auto;
      text-align: center;
      font-size: 1.2rem;
      border-radius: 12px;
      text-transform: uppercase;
      transition: all 0.3s ease;
    }

    .btn-add:hover {
      background: #357ABD;
      transform: scale(1.05);
      box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.3);
    }

    .message {
      text-align: center;
      font-size: 1.5rem;
      font-weight: 700;
      color: #f0a6db;
      padding: 20px;
    }
  </style>
</head>
<body>

<!-- Navigation Bar -->
<div class="navigation">
  <a href="dashboard.jsp" class="nav-item">Dashboard</a>
  <a href="manageUsers.jsp" class="nav-item">Manage Users</a>
  <a href="manageFlights.jsp" class="nav-item active">Manage Flights</a>
  <a href="index.jsp" class="nav-item">Logout</a>
</div>

<!-- Background Slider -->
<div class="background-slider">
  <div class="slide active" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0');"></div>
  <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
  <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<div class="container">
  <h1> Gestion des Vols</h1>
  <table>
    <thead>
    <tr>
      <th>Numéro de vol</th>
      <th>Départ</th>
      <th>Arrivée</th>
      <th>Prix</th>
      <th>Tickets disponibles</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% if (flights != null && !flights.isEmpty()) {
      for (Vol flight : flights) { %>
    <tr>
      <td><%= flight.getFlightNumber() %></td>
      <td><%= flight.getFormattedDepartureTime() %></td>
      <td><%= flight.getFormattedArrivalTime() %></td>
      <td><%= flight.getPrice() %> MAD</td>
      <td><%= flight.getAvailableTickets() %></td>
      <td>

        <%-- <form action="FlightServlet" method="POST"> --%>
        <%--<input type="hidden" name="id" value="<%= flight.getId() %>"> --%>
        <%--<button class="btn btn-delete" type="submit" name="action" value="delete">Supprimer</button> --%>
        <%--</form> --%>
        <form action="FlightServlet" method="POST">
          <input type="hidden" name="delete" value="true">
          <input type="hidden" name="id" value="<%= flight.getId() %>">
          <button type="submit">Supprimer</button>
        </form>

      </td>
    </tr>
    <% }
    } else { %>
    <tr>
      <td colspan="6" class="message">Aucun vol disponible pour le moment.</td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <a href="addFlight.jsp" class="btn-add">Ajouter un vol</a>
</div>

<script>
  let currentSlide = 0;
  const slides = document.querySelectorAll('.slide');

  setInterval(() => {
    slides[currentSlide].classList.remove('active');
    currentSlide = (currentSlide + 1) % slides.length;
    slides[currentSlide].classList.add('active');
  }, 5000);
</script>

</body>
</html>


