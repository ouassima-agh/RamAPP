<%@ page import="model.Flight" %>
<%@ page import="dao.FlightDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setCharacterEncoding("UTF-8"); %>
<%
    String flightIdParam = request.getParameter("flightId");
    Flight flight = new FlightDAO().getFlightById(Long.parseLong(flightIdParam));
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Réservation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="indexstyle.css">
    <style>
        main {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 60px 20px;
        }

        .reservation-container {
            background: rgba(0, 0, 0, 0.75);
            padding: 40px;
            border-radius: 20px;
            width: 90%;
            max-width: 600px;
            color: white;
            box-shadow: 0 10px 30px rgba(0,0,0,0.5);
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #007bff;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        label {
            font-weight: 600;
            margin-bottom: 5px;
        }

        input, select {
            padding: 10px;
            border-radius: 8px;
            border: none;
            font-size: 1rem;
        }

        .btn {
            padding: 12px;
            background-color: #007bff;
            border: 2px solid #007bff;
            border-radius: 10px;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s ease;
        }

        .btn:hover {
            background-color: #007bff;
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
                <li class="nav-item"><a href="indexa.jsp" class="nav-link">ACCUEIL</a></li>
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
    <div class="reservation-container">
        <h2>Réservation du vol <%= flight.getFlightNumber() %></h2>

        <form action="reserve" method="post">
            <input type="hidden" name="flightId" value="<%= flight.getId() %>">

            <div>
                <label>Nom :</label>
                <input type="text" name="nom" required>
            </div>

            <div>
                <label>Prénom :</label>
                <input type="text" name="prenom" required>
            </div>

            <div>
                <label>Email :</label>
                <input type="email" name="email" required>
            </div>

            <div>
                <label>Téléphone :</label>
                <input type="text" name="telephone" required>
            </div>

            <div>
                <label>Sexe :</label>
                <select name="sexe">
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                </select>
            </div>

            <div>
                <label>Date de naissance :</label>
                <input type="date" name="dob" required>
            </div>

            <button type="submit" class="btn">Confirmer la réservation</button>
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