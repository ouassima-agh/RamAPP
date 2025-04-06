<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Reservation" %>
<%
    response.setCharacterEncoding("UTF-8");
    Reservation reservation = (Reservation) request.getAttribute("reservation");

    if (reservation == null) {
        response.sendRedirect("error.jsp?message=Réservation introuvable");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Paiement - Royal Air Maroc</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="indexstyle.css">
    <style>
        main {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 100px 20px 40px;
        }

        .payment-container {
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
            color: #00ccff;
            margin-bottom: 25px;
        }

        .flight-info {
            background: rgba(255, 255, 255, 0.1);
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .info-item {
            margin: 6px 0;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        input {
            padding: 10px;
            border-radius: 6px;
            border: none;
            font-size: 1rem;
            background: rgba(255, 255, 255, 0.95);
        }

        .form-row {
            display: flex;
            gap: 15px;
        }

        .form-row .form-group {
            flex: 1;
        }

        .btn {
            padding: 14px;
            background-color: #00aaff;
            border: none;
            border-radius: 10px;
            color: white;
            font-weight: bold;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 10px;
        }

        .btn:hover {
            background-color: #0088cc;
            transform: scale(1.03);
        }

        .amount-display {
            font-size: 1.5rem;
            text-align: center;
            margin: 20px 0;
            color: #00ffcc;
            font-weight: bold;
        }
    </style>
</head>
<body>

<!-- HEADER -->
<header>
    <div class="header-container">
        <a href="indexa.jsp" class="logo">
            <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain" alt="Royal Air Maroc" class="logo-img">
        </a>
        <nav class="header-nav">
            <ul class="nav-list">
                <li class="nav-item"><a href="indexa.jsp" class="nav-link">Accueil</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- SLIDER (fond animé) -->
<div class="background-slider">
    <div class="slide active" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
    <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
    <div class="slide" style="background-image: url('https://cdn.futura-sciences.com/buildsv6/images/wide1920/d/f/5/df5a0bece4_50169646_avion.jpg');"></div>
</div>

<!-- SCRIPT slider -->
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

<main>
    <div class="payment-container">
        <h2>💳 Paiement de la réservation</h2>

        <div class="flight-info">
            <div class="info-item"><strong>Vol :</strong> <%= reservation.getFlight().getFlightNumber() %></div>
            <div class="info-item"><strong>De :</strong> <%= reservation.getFlight().getDepartureAirport().getCity() %></div>
            <div class="info-item"><strong>À :</strong> <%= reservation.getFlight().getArrivalAirport().getCity() %></div>
            <div class="info-item"><strong>Passager :</strong> <%= reservation.getPassenger().getPrenom() %> <%= reservation.getPassenger().getNom() %></div>
        </div>

        <div class="amount-display">
            💰 <%= reservation.getPrice() %> €
        </div>

        <form action="<%= request.getContextPath() %>/payment" method="post">
            <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">

            <div class="form-group">
                <label for="cardNumber">Numéro de carte</label>
                <input type="text" id="cardNumber" name="cardNumber" pattern="[0-9]{16}" required>
            </div>

            <div class="form-group">
                <label for="cardHolderName">Nom sur la carte</label>
                <input type="text" id="cardHolderName" name="cardHolderName" pattern="[A-Za-z ]+" required>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="expiryMonth">Mois d'expiration</label>
                    <input type="text" id="expiryMonth" name="expiryMonth" pattern="[0-9]{1,2}" required>
                </div>

                <div class="form-group">
                    <label for="expiryYear">Année d'expiration</label>
                    <input type="text" id="expiryYear" name="expiryYear" pattern="[0-9]{4}" required>
                </div>
            </div>

            <div class="form-group">
                <label for="cvv">CVV</label>
                <input type="text" id="cvv" name="cvv" pattern="[0-9]{3,4}" required>
            </div>

            <button type="submit" class="btn">Valider le paiement ✅</button>
        </form>
    </div>
</main>

</body>
</html>
