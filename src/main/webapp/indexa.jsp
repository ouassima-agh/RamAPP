
<%@ page import="java.util.List" %>
<%@ page import="model.Airport" %>
<%
  List<Airport> airports = (List<Airport>) request.getAttribute("airports");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Réservation de Vol</title>
  <link rel="stylesheet" href="indexstyle.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="/" class="logo">
      <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain.jpg" alt="Royal Air Maroc" class="logo-img">
    </a>
    <nav class="header-nav">
      <ul class="nav-list">
        <li class="nav-item"><a href="booking.jsp" class="nav-link">R&Eacute;SERVATIONS</a></li>
      </ul>
    </nav>
  </div>
</header>
<div class="background-slider">
  <div class="slide" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0.jpg.jpg');"></div>
  <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
  <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const slides = document.querySelectorAll(".slide");
    let currentIndex = 0;

    function showNextSlide() {
      const currentSlide = slides[currentIndex];
      const nextIndex = (currentIndex + 1) % slides.length;
      const nextSlide = slides[nextIndex];
      currentSlide.classList.remove("active");
      nextSlide.classList.add("active");
      currentIndex = nextIndex;
    }
    slides[currentIndex].classList.add("active");
    setInterval(showNextSlide, 3000);
  });
</script>
<main>
  <div class="hero-section">
    <div class="search-container">
      <h1>R&Eacute;SERVEZ VOTRE VOL</h1>
      <form action="searchFlights" method="POST" class="search-form">
        <div class="form-row">
          <div class="form-group">
            <label for="depart">A&eacute;roport de d&eacute;part</label>
            <select id="depart" name="departureAirport" required>
              <option value="">Choisissez un a&eacute;roport</option>
              <% if (airports != null) {
                for (Airport a : airports) { %>
              <option value="<%= a.getId() %>"><%= a.getCity() %> (<%= a.getCode() %>)</option>
              <% } } %>
            </select>
          </div>
          <div class="form-group">
            <label for="depart-date">D&eacute;part le</label>
            <input type="date" id="depart-date" name="departDate" required>
          </div>
          <div class="form-group">
            <label for="depart-time">Heure de d&eacute;part</label>
            <input type="time" id="depart-time" name="departTime">
          </div>
        </div>
        <div class="form-row">
          <div class="form-group">
            <label for="arrivee">A&eacute;roport d'arriv&eacute;e</label>
            <select id="arrivee" name="arrivalAirport" required>
              <option value="">Choisissez un a&eacute;roport</option>
              <% if (airports != null) {
                for (Airport a : airports) { %>
              <option value="<%= a.getId() %>"><%= a.getCity() %> (<%= a.getCode() %>)</option>
              <% } } %>
            </select>
          </div>
          <div class="form-group" id="return-date-group">
            <label for="return-date">Retour le</label>
            <input type="date" id="return-date" name="returnDate">
          </div>
          <div class="form-group">
            <label for="return-time">Heure de retour</label>
            <input type="time" id="return-time" name="returnTime">
          </div>
        </div>
        <div class="radio-group">
          <label>
            <input type="radio" name="tripType" value="roundTrip" checked>
            Aller-Retour
          </label>
          <label>
            <input type="radio" name="tripType" value="oneWay">
            Aller simple
          </label>
        </div>
        <button type="submit" class="search-button">RECHERCHER</button>
      </form>
    </div>
  </div>
</main>
</body>
</html>
