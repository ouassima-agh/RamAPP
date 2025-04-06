<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Vol" %>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Ajouter un Vol</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <style>
    /* --- styles identiques à ceux que tu as fournis --- */
    body {
      margin: 0;
      font-family: 'Segoe UI', sans-serif;
      background: rgba(0, 0, 0, 0.85);
      color: white;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      flex-direction: column;
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

    .navigation {
      display: flex;
      justify-content: center;
      background-color: rgba(0, 0, 0, 0.8);
      padding: 15px 0;
      margin-bottom: 30px;
      border-top: 1px solid #444;
      border-bottom: 1px solid #444;
      width: 100%;
      position: fixed;
      top: 0;
      left: 0;
      z-index: 1;
    }

    .nav-item {
      margin: 0 30px;
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

    .form-box {
      background: rgba(0, 0, 0, 0.8);
      padding: 30px 40px;
      border-radius: 25px;
      width: 90%;
      max-width: 500px;
      box-shadow: 0 0 30px rgba(0, 0, 0, 0.8);
      animation: formEnter 0.7s ease-in-out;
      margin-top: 150px;
    }

    @keyframes formEnter {
      from {
        opacity: 0;
        transform: translateY(30px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    .form-box:hover {
      box-shadow: 0 0 50px rgba(0, 0, 0, 1);
      transform: scale(1.05);
    }

    h1 {
      text-align: center;
      font-size: 36px;
      margin-bottom: 20px;
      color: #1E90FF;
      font-family: 'Poppins', sans-serif;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 4px;
      text-shadow: 3px 3px 12px rgba(0, 0, 0, 0.6);
    }

    form {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px 25px;
      margin-bottom: 30px;
    }

    label {
      font-size: 16px;
      display: block;
      margin-bottom: 5px;
      color: #fff;
    }

    input {
      width: 100%;
      padding: 15px;
      font-size: 16px;
      border: 2px solid transparent;
      border-radius: 25px;
      background: #f5f5f5;
      color: #333;
      transition: border-color 0.3s ease, background-color 0.3s ease, transform 0.3s ease;
    }

    input:focus {
      border-color: #1E90FF;
      background: #fff;
      outline: none;
      transform: scale(1.02);
    }

    .full-width {
      grid-column: 1 / -1;
    }

    button {
      padding: 15px;
      background-color: #1E90FF;
      border: none;
      border-radius: 25px;
      color: white;
      font-weight: bold;
      cursor: pointer;
      font-size: 18px;
      transition: background-color 0.3s, transform 0.2s ease, box-shadow 0.3s ease;
      grid-column: 1 / -1;
    }

    button:hover {
      background-color: #0056b3;
      transform: scale(1.05);
    }
  </style>
</head>
<body>

<!-- Navigation Bar -->
<div class="navigation">
  <a href="dashboard.jsp" class="nav-item">Dashboard</a>
  <a href="manageUsers.jsp" class="nav-item">Manage Users</a>
  <a href="manageFlights.jsp" class="nav-item">Manage Flights</a>
  <a href="index.jsp" class="nav-item">Logout</a>
</div>

<!-- Background Slider -->
<div class="background-slider">
  <div class="slide active" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0');"></div>
  <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
  <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<!-- Formulaire -->
<div class="form-box">
  <h1>Ajouter un Vol</h1>
  <form action="FlightServlet" method="POST">
    <input type="hidden" name="action" value="add">

    <label for="flightNumber">Numéro de vol</label>
    <input type="text" id="flightNumber" name="flightNumber" required>

    <label for="departureTime">Départ</label>
    <input type="datetime-local" id="departureTime" name="departureTime" required>

    <label for="arrivalTime">Arrivée</label>
    <input type="datetime-local" id="arrivalTime" name="arrivalTime" required>

    <label for="price">Prix</label>
    <input type="number" id="price" name="price" required step="0.01" min="0">

    <label for="availableTickets">Tickets disponibles</label>
    <input type="number" id="availableTickets" name="availableTickets" required min="1">

    <button type="submit">Ajouter</button>
  </form>
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



