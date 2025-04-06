<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Flight" %>

<%
    List<Flight> allerFlights = (List<Flight>) request.getAttribute("allerFlights");
    List<Flight> retourFlights = (List<Flight>) request.getAttribute("retourFlights");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Résultats des Vols ✈️</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="indexstyle.css">
    <style>
        main {
            padding: 60px 20px;
            display: flex;
            justify-content: center;
        }

        .results-container {
            background: rgba(0, 0, 0, 0.75);
            padding: 30px;
            border-radius: 20px;
            width: 90%;
            max-width: 1200px;
            color: white;
            box-shadow: 0 10px 30px rgba(0,0,0,0.5);
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #0095ff;
            margin-bottom: 20px;
            border-bottom: 2px solid #0095ff;
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
            background-color: rgba(255, 255, 255, 0.05);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #444;
            color: white;
        }

        th {
            background-color: #0095ff;
            color: rgb(52, 54, 57);
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: rgba(255,255,255,0.06);
        }

        tr:hover {
            background-color: rgba(255,255,255,0.15);
        }

        .btn {
            padding: 8px 14px;
            background-color: #008cff;
            border: 2px solid #008cff;
            border-radius: 10px;
            color: white;
            font-weight: 600;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn:hover {
            background-color: #0095ff;
            transform: scale(1.05);
        }

        .message {
            text-align: center;
            font-size: 1.2rem;
            color: #008cff;
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
                <li class="nav-item"><a href="booking.jsp" class="nav-link">RÉSERVATIONS</a></li>
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
    <div class="results-container">
        <h2>Vols Aller</h2>
        <% if (allerFlights != null && !allerFlights.isEmpty()) { %>
        <table>
            <thead>
            <tr>
                <th>Vol</th>
                <th>Départ</th>
                <th>Arrivée</th>
                <th>Date</th>
                <th>Heure Départ</th>
                <th>Heure Arrivée</th>
                <th>Prix (€)</th>
                <th>Places</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% for (Flight f : allerFlights) { %>
            <tr>
                <td><%= f.getFlightNumber() %></td>
                <td><%= f.getDepartureAirport().getCity() %></td>
                <td><%= f.getArrivalAirport().getCity() %></td>
                <td><%= f.getDepartureDate() %></td>
                <td><%= f.getDepartureTime() %></td>
                <td><%= f.getArrivalTime() %></td>
                <td><%= f.getPrice() %></td>
                <td><%= f.getAvailableTickets() %></td>
                <td>
                    <form method="get" action="booking.jsp">
                        <input type="hidden" name="flightId" value="<%= f.getId() %>">
                        <button class="btn" type="submit">Réserver</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <div class="message">Aucun vol aller trouvé.</div>
        <% } %>

        <% if (retourFlights != null) { %>
        <h2>Vols Retour</h2>
        <% if (!retourFlights.isEmpty()) { %>
        <table>
            <thead>
            <tr>
                <th>Vol</th>
                <th>Départ</th>
                <th>Arrivée</th>
                <th>Date</th>
                <th>Heure Départ</th>
                <th>Heure Arrivée</th>
                <th>Prix (€)</th>
                <th>Places</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% for (Flight f : retourFlights) { %>
            <tr>
                <td><%= f.getFlightNumber() %></td>
                <td><%= f.getDepartureAirport().getCity() %></td>
                <td><%= f.getArrivalAirport().getCity() %></td>
                <td><%= f.getDepartureDate() %></td>
                <td><%= f.getDepartureTime() %></td>
                <td><%= f.getArrivalTime() %></td>
                <td><%= f.getPrice() %></td>
                <td><%= f.getAvailableTickets() %></td>
                <td>
                    <form method="get" action="booking.jsp">
                        <input type="hidden" name="flightId" value="<%= f.getId() %>">
                        <button class="btn" type="submit">Réserver</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <div class="message">Aucun vol retour trouvé.</div>
        <% } %>
        <% } %>
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
