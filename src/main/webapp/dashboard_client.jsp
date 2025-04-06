<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord - Client</title>
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

        .content-area {
            padding: 50px 20px;
            display: flex;
            justify-content: center;
        }

        .card {
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 20px;
            width: 100%;
            max-width: 800px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.4);
        }

        .card-header h5 {
            font-size: 1.8rem;
            color: #00aaff;
        }

        .card-body p {
            font-size: 1.1rem;
        }

        .btn-action {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 30px;
            transition: transform 0.3s;
        }

        .btn-action:hover {
            transform: scale(1.05);
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<!-- Background Slider -->
<div class="background-slider">
    <div class="slide active" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0');"></div>
    <div class="slide" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/f/f1/Royal_Air_Maroc_Boeing_787-9_Dreamliner_CN-RGZ_departing_JFK_Airport.jpg');"></div>
    <div class="slide" style="background-image: url('https://www.lesjardinsdelamedina.com/blog/wp-content/uploads/2017/09/aeroport-2.jpg');"></div>
</div>

<div class="header">
    <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain.jpg" alt="Company Logo" class="logo">

    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" class="form-control" placeholder="Search">
    </div>

    <div class="user-profile">
        <i class="fas fa-bell"></i>
        <div class="user-avatar"><i class="fas fa-user"></i></div>
        <div class="user-info">
            <span class="username">MY PROFILE</span>
            <i class="fas fa-chevron-down"></i>
        </div>
    </div>
</div>

<div class="navigation">
    <a href="#" class="nav-item active"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
    <a href="indexa.jsp" class="nav-item"><i class="fas fa-ticket-alt"></i> Reservation</a>
    <a href="index.jsp" class="nav-item"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>

<div class="content-area">
    <div class="card">
        <div class="card-header">
            <h5>Welcome To Your ROYAL AIR MAROC Space</h5>
        </div>
        <div class="card-body">
            <p>Here you can manage your bookings, and access other services.</p>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const slides = document.querySelectorAll(".slide");
        let currentIndex = 0;

        setInterval(() => {
            slides[currentIndex].classList.remove("active");
            currentIndex = (currentIndex + 1) % slides.length;
            slides[currentIndex].classList.add("active");
        }, 5000);
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>