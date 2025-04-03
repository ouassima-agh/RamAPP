<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Connexion</title>
</head>
<body>

<div class="form-container">
  <form action="LoginServlet" method="POST" class="login-form">
    <h2>Connexion</h2>
    <div class="input-group">
      <label for="email">E-mail</label>
      <input type="email" id="email" name="email" placeholder="Saisir votre adresse e-mail" required>
    </div>
    <div class="input-group">
      <label for="password">Mot de passe</label>
      <input type="password" id="password" name="password" placeholder="Saisir votre mot de passe" required>
    </div>
    <div class="remember-me">
      <input type="checkbox" id="remember" name="remember">
      <label for="remember">Se souvenir de moi</label>
    </div>
    <button type="submit" class="btn">Se connecter</button>
    <div class="links">
      <p>Vous n'avez pas de compte ? <a href="register.jsp">S'inscrire</a></p>
      <p><a href="#" class="forgot-password">Mot de passe oublié ?</a></p>
    </div>
  </form>
</div>

<style>
  /* Style général */
  body {
    margin: 0;
    font-family: Arial, sans-serif;
    height: 100vh;

    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
  }

  /* Conteneur du formulaire */
  .form-container {
    width: 90%;
    max-width: 400px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
    padding: 30px;
    animation: fadeIn 0.5s ease-in-out;
  }

  @keyframes fadeIn {
    from { opacity: 0; transform: translateY(-20px); }
    to { opacity: 1; transform: translateY(0); }
  }

  /* Titre */
  h2 {
    text-align: center;
    margin-bottom: 25px;
    color: #333;
    font-size: 28px;
  }

  /* Groupes d'entrées */
  .input-group {
    margin-bottom: 20px;
  }

  label {
    display: block;
    font-weight: bold;
    margin-bottom: 8px;
    color: #333;
  }

  input[type="email"],
  input[type="password"] {
    width: 100%;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
    transition: all 0.3s;
    box-sizing: border-box;
  }

  input:focus {
    border-color: #87CEEB;
    box-shadow: 0 0 8px rgba(135, 206, 235, 0.5);
    outline: none;
  }

  /* Case à cocher */
  .remember-me {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .remember-me input {
    margin-right: 10px;
  }

  /* Bouton bleu ciel */
  .btn {
    width: 100%;
    padding: 12px;
    background-color: #87CEEB;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 18px;
    cursor: pointer;
    transition: all 0.3s;
    margin-bottom: 15px;
  }

  .btn:hover {
    background-color: #5CACEE;
    transform: scale(1.03);
  }

  /* Liens */
  .links {
    text-align: center;
  }

  p {
    margin: 10px 0;
  }

  a {
    color: #87CEEB;
    text-decoration: none;
    transition: color 0.3s;
  }

  a:hover {
    color: #5CACEE;
    text-decoration: underline;
  }

  .forgot-password {
    font-size: 14px;
  }
</style>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector(".login-form");
    const button = document.querySelector(".btn");

    // Animation subtile lorsque le curseur survole le formulaire
    form.addEventListener("mouseenter", function() {
      this.style.transform = "translateY(-5px)";
      this.style.boxShadow = "0 6px 22px rgba(0, 0, 0, 0.15)";
    });

    form.addEventListener("mouseleave", function() {
      this.style.transform = "translateY(0)";
      this.style.boxShadow = "none";
    });

    // Animation pour le bouton
    button.addEventListener("mouseenter", function() {
      this.style.transform = "scale(1.05)";
    });

    button.addEventListener("mouseleave", function() {
      this.style.transform = "scale(1)";
    });
  });
</script>

</body>
</html>