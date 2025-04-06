<%@ page import="model.Reservation" %>
<%@ page import="model.Passenger" %>
<%@ page import="model.Flight" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setCharacterEncoding("UTF-8"); %>

<%
  Reservation reservation = (Reservation) request.getAttribute("reservation");
  Passenger p = reservation.getPassenger();
  Flight f = reservation.getFlight();
%>

<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Confirmation de Réservation</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="indexstyle.css">
  <style>
    main {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 60px 20px;
    }

    .confirmation-container {
      background: rgba(0, 0, 0, 0.75);
      padding: 40px;
      border-radius: 20px;
      width: 90%;
      max-width: 700px;
      color: white;
      box-shadow: 0 10px 30px rgba(0,0,0,0.5);
      text-align: center;
    }

    h2 {
      font-size: 2rem;
      color: #008cff;
      margin-bottom: 20px;
    }

    p {
      font-size: 1.1rem;
      margin: 10px 0;
    }

    .btn {
      padding: 12px 20px;
      margin-top: 30px;
      background-color: #008cff;
      border: 2px solid #008cff;
      border-radius: 10px;
      color: white;
      font-weight: bold;
      font-size: 1rem;
      cursor: pointer;
      transition: 0.3s ease;
      text-decoration: none;
      display: inline-block;
    }

    .btn:hover {
      background-color: #008cff;
      transform: scale(1.05);
    }
  </style>
</head>
<body>

<header>
  <div class="header-container">
    <a href="/" class="logo">
      <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain" alt="Royal Air Maroc" class="logo-img">
    </a>
    <nav class="header-nav">
      <ul class="nav-list">
        <li class="nav-item"><a href="index.jsp" class="nav-link">ACCUEIL</a></li>
      </ul>
    </nav>
  </div>
</header>

<div class="background-slider">
  <div class="slide active" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0');"></div>
  <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
  <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<main>
  <div class="confirmation-container">
    <h2>✅ Réservation confirmée</h2>
    <p><strong>Nom :</strong> <%= p.getPrenom() %> <%= p.getNom() %></p>
    <p><strong>Email :</strong> <%= p.getEmail() %></p>
    <p><strong>Vol :</strong> <%= f.getFlightNumber() %> - <%= f.getDepartureAirport().getCity() %> → <%= f.getArrivalAirport().getCity() %></p>
    <p><strong>Date :</strong> <%= f.getDepartureDate() %> à <%= f.getDepartureTime() %></p>
    <p><strong>Prix :</strong> <%= reservation.getPrice() %> €</p>

    <!-- Bouton vers la page de paiement -->
    <form method="get" action="payment">
      <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
      <button type="submit" class="btn">Payer maintenant 💳</button>
    </form>
  </div>
</main>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const slides = document.querySelectorAll(".slide");
    let currentIndex = 0;
    setInterval(() => {
      slides[currentIndex].classList.remove("active");
      currentIndex = (currentIndex + 1) % slides.length;
      slides[currentIndex].classList.add("active");
    }, 4000);
  });
</script>

</body>
</html>
