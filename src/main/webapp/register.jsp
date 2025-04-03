<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inscription</title>

  <style>
    /* Style général */
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      height: 100vh;

      display: flex;
      align-items: center;
      justify-content: center;
    }

    /* Conteneur principal */
    .form-container {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
      width: 80%;
      max-width: 900px;
      padding: 30px;
    }

    h2 {
      text-align: center;
      margin-bottom: 25px;
      color: #333;
    }

    /* Style pour la division en 2 colonnes */
    .form-row {
      display: flex;
      flex-wrap: wrap;
      margin: 0 -10px;
    }

    .form-column {
      flex: 1;
      padding: 0 10px;
      min-width: 250px;
    }

    .input-group {
      margin-bottom: 15px;
    }

    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="date"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    .radio-group {
      display: flex;
      align-items: center;
      gap: 15px;
    }

    .radio-group input {
      margin-right: 5px;
    }

    /* Mise en forme du bouton - MODIFIÉ POUR BLEU CIEL */
    button {
      width: 100%;
      padding: 12px;
      background-color: #87CEEB; /* Bleu ciel */
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
      transition: 0.3s;
      margin-top: 10px;
    }

    button:hover {
      background-color: #5CACEE; /* Bleu ciel légèrement plus foncé au survol */
      transform: scale(1.02);
    }

    /* Style des liens */
    p {
      text-align: center;
      margin-top: 15px;
    }

    a {
      color: #87CEEB; /* Lien en bleu ciel aussi pour cohérence */
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }

    /* Animation des champs */
    input:focus {
      border-color: #87CEEB; /* Bordure en bleu ciel lors du focus */
      outline: none;
      box-shadow: 0 0 5px rgba(135, 206, 235, 0.5);
    }
  </style>
</head>

<body>

<div class="form-container">
  <form action="RegisterServlet" method="POST" class="register-form">
    <h2>Inscription</h2>

    <div class="form-row">
      <div class="form-column">
        <div class="input-group">
          <label for="nom">Nom</label>
          <input type="text" id="nom" name="nom" placeholder="Saisir votre nom" required>
        </div>

        <div class="input-group">
          <label for="prenom">Prénom</label>
          <input type="text" id="prenom" name="prenom" placeholder="Saisir votre prénom" required>
        </div>

        <div class="input-group">
          <label for="date_naissance">Date de naissance</label>
          <input type="date" id="date_naissance" name="date_naissance" required>
        </div>

        <div class="input-group">
          <label>Sexe</label>
          <div class="radio-group">
            <div>
              <input type="radio" id="homme" name="sexe" value="Homme" required>
              <label for="homme">Homme</label>
            </div>
            <div>
              <input type="radio" id="femme" name="sexe" value="Femme" required>
              <label for="femme">Femme</label>
            </div>
          </div>
        </div>
      </div>

      <div class="form-column">
        <div class="input-group">
          <label for="telephone">Téléphone</label>
          <input type="text" id="telephone" name="telephone" placeholder="Saisir votre numéro de téléphone" required>
        </div>

        <div class="input-group">
          <label for="email">E-mail</label>
          <input type="email" id="email" name="email" placeholder="Saisir votre adresse e-mail" required>
        </div>

        <div class="input-group">
          <label for="password">Mot de passe</label>
          <input type="password" id="password" name="password" placeholder="8 caractères minimum" required minlength="8">
        </div>

        <div class="input-group">
          <label for="confirm_password">Confirmation du mot de passe</label>
          <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirmer votre mot de passe" required minlength="8">
        </div>
      </div>
    </div>

    <button type="submit" class="btn">S'inscrire</button>
    <p>Vous avez déjà un compte ? <a href="login.jsp">Se connecter</a></p>
  </form>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirm_password");

    confirmPassword.addEventListener("input", function() {
      if (password.value !== confirmPassword.value) {
        confirmPassword.style.borderColor = "red";
      } else {
        confirmPassword.style.borderColor = "green";
      }
    });
  });
</script>

</body>
</html>