<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord - client</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            min-height: 100vh;
        }
        .header {
            background-color: white;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            height: 50px;
        }
        .search-bar {
            position: relative;
            width: 300px;
        }
        .search-bar input {
            padding-left: 35px;
            border-radius: 20px;
            border: 1px solid #ddd;
        }
        .search-bar i {
            position: absolute;
            left: 12px;
            top: 10px;
            color: #777;
        }
        .user-profile {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: #ddd;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .navigation {
            background-color: white;
            padding: 10px 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            margin-bottom: 20px;
        }
        .nav-item {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 5px;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
            transition: all 0.3s;
        }
        .nav-item.active {
            background-color: #e6f7ff;
            color: #0077cc;
            border-bottom: 2px solid #0077cc;
        }
        .nav-item:hover {
            background-color: #f0f0f0;
        }
        .content-area {
            padding: 20px;
        }
        .card {
            margin-bottom: 20px;
            border: none;
            border-radius: 10px;
            overflow: hidden;
        }
        .card-header {
            padding: 12px 20px;
            font-weight: 600;
        }
        .table th {
            font-weight: 600;
            color: #555;
            background-color: #f8f9fa;
        }
        .btn-action {
            border-radius: 20px;
            padding: 5px 15px;
        }
    </style>
</head>
<body>
<div class="header">
    <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain.jpg" alt="Company Logo" class="logo">

    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" class="form-control" placeholder="Search">
    </div>

    <div class="user-profile">
        <div class="notifications">
            <i class="fas fa-bell"></i>
        </div>
        <div class="user-avatar">
            <i class="fas fa-user"></i>
        </div>


        <div class="user-info">
            <div class="profile-pic"></div>
            <span class="username">MY PROFILE</span>
            <i class="fas fa-chevron-down"></i>
        </div>

    </div>
</div>

<div class="navigation">
    <a href="#" class="nav-item active"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
    <a href="booking.jsp" class="nav-item"><i class="fas fa-ticket-alt"></i> Reservation</a>

    <a href="index.jsp" class="nav-item"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>


<div class="content-area">
    <div class="card">
        <div class="card-header">
            <h5>Welcome To Your ROYAL AIR MAROC Space</h5>
        </div>
        <div class="card-body">
            <p>Here you can manage your bookings,and access other services.</p>
        </div>
    </div>
</div>




<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>