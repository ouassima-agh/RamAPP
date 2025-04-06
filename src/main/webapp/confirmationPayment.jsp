<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Reservation" %>

<%
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    String pdfFile = (String) request.getAttribute("pdfFile");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Confirmation du Paiement</title>
    <link rel="stylesheet" href="indexstyle.css">
    <style>
        .confirmation-container {
            background: rgba(0, 0, 0, 0.8);
            padding: 40px;
            border-radius: 20px;
            width: 90%;
            max-width: 600px;
            margin: 100px auto;
            color: white;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.5);
        }

        h2 {
            font-size: 2rem;
            color: #00cc66;
            margin-bottom: 20px;
        }

        .info {
            margin: 10px 0;
            font-size: 1.1rem;
        }

        .btn {
            padding: 12px 20px;
            margin-top: 20px;
            background-color: #008cff;
            border-radius: 10px;
            color: white;
            text-decoration: none;
            font-weight: bold;
            display: inline-block;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<header>
    <div class="header-container">
        <a href="/" class="logo">
            <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain.jpg" alt="Royal Air Maroc" class="logo-img">
        </a>
        <nav class="header-nav">
            <ul class="nav-list">
                <li class="nav-item"><a href="indexa.jsp" class="nav-link">ACCUEIL</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="background-slider">
    <div class="slide" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0.jpg');"></div>
    <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
    <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const slides = document.querySelectorAll(".slide");
        let currentIndex = 0;

        function showNextSlide() {
            slides[currentIndex].classList.remove("active");
            currentIndex = (currentIndex + 1) % slides.length;
            slides[currentIndex].classList.add("active");
        }

        slides[currentIndex].classList.add("active");
        setInterval(showNextSlide, 4000);
    });
</script>

<main>
    <div class="confirmation-container">
        <h2>✅ Paiement confirmé !</h2>

        <div class="info"><strong>Vol :</strong> <%= reservation.getFlight().getFlightNumber() %></div>
        <div class="info"><strong>De :</strong> <%= reservation.getFlight().getDepartureAirport().getCity() %></div>
        <div class="info"><strong>À :</strong> <%= reservation.getFlight().getArrivalAirport().getCity() %></div>
        <div class="info"><strong>Passager :</strong> <%= reservation.getPassenger().getPrenom() %> <%= reservation.getPassenger().getNom() %></div>
        <div class="info"><strong>Montant payé :</strong> <%= reservation.getPrice() %> €</div>

        <% if (pdfFile != null) { %>
        <a href="<%= request.getContextPath() + "/" + pdfFile %>" target="_blank" class="btn">📄 Télécharger votre billet PDF</a>
        <% } else { %>
        <p style="color: red;">Erreur : le billet PDF n’a pas pu être généré.</p>
        <% } %>

        <br><br>
        <a href="indexa.jsp" class="btn" style="background-color: gray;">🏠 Retour à l'accueil</a>
    </div>
</main>

</body>
</html>
