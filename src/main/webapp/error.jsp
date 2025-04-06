<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Erreur</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 20px;
      color: #333;
    }
    .error-container {
      max-width: 600px;
      margin: 50px auto;
      padding: 30px;
      background: white;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      text-align: center;
    }
    h1 {
      color: #d9534f;
    }
    .error-message {
      margin: 20px 0;
      padding: 15px;
      background-color: #f8d7da;
      border: 1px solid #f5c6cb;
      border-radius: 4px;
    }
    .home-link {
      display: inline-block;
      margin-top: 20px;
      padding: 10px 15px;
      background-color: #0275d8;
      color: white;
      text-decoration: none;
      border-radius: 4px;
    }
  </style>
</head>
<body>
<div class="error-container">
  <h1>Une erreur est survenue</h1>
  <div class="error-message">
    ${not empty param.message ? param.message : 'Erreur inconnue'}
  </div>
  <a href="indexa.jsp" class="home-link">Retour à l'accueil</a>
</div>
</body>
</html>