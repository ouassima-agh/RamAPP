Royal Air Maroc - Système de Réservation de Vols

Ce projet est une application web permettant aux utilisateurs de rechercher des vols, réserver un billet, effectuer un paiement et générer un billet PDF. Elle est développée avec Java (Servlets, JSP, JPA), MySQL, et déployée sur un serveur Apache Tomcat.

Fonctionnalités principales :

Recherche de vols selon la date, l'heure, les aéroports de départ et d'arrivée
Formulaire de réservation avec données passager
Paiement sécurisé par carte bancaire (simulation)
Génération automatique d’un billet PDF après paiement
Confirmation de la réservation avec lien de téléchargement
Interface responsive avec fond animé
Envoi de mail avec le billet (optionnel)
Technologies utilisées :

Java 17
Jakarta EE (Servlets, JSP, JSTL)
Hibernate (JPA)
Apache Tomcat 10
MySQL 8
Maven
iText PDF pour la génération du billet
Bootstrap / CSS / Font Awesome pour l’interface
Structure du projet :

controller/ : Servlets Java
dao/ : Gestion de la base de données via JPA
model/ : Entités JPA (Passenger, Flight, Reservation, CarteBancaire, Payment, etc.)
webapp/ : Fichiers JSP (index.jsp, paymentForm.jsp, confirmationPayment.jsp, etc.)
resources/META-INF/persistence.xml : Configuration JPA
pom.xml : Dépendances Maven
Base de données (MySQL) :

Nom de la base : flight_db

Exemple de table administrateur :

Table : Admin
Champs :

idAdministrateur (clé primaire auto-incrémentée)
nomAdmi
prenomAdmi
emailAdmi
motDePass
telephone
sexe
dateNaissance
Exemple d’insertion :

INSERT INTO Admin (nomAdmi, prenomAdmi, emailAdmi, motDePass, telephone, sexe, dateNaissance)
VALUES ('Benali', 'Said', 'admin@ram.com', 'admin123', '0600000000', 'Homme', '1990-01-01');
Configuration JPA (persistence.xml) :

Unité de persistance : FlightPU
URL JDBC : jdbc:mysql://localhost:3306/flight_db
Utilisateur : root
Mot de passe : votre mot de passe MySQL
Déploiement :

Compiler le projet avec Maven (mvn clean install)
Déployer le fichier WAR sur Tomcat
Lancer : http://localhost:8080/NomDuProjet_war_exploded/
Chemin des fichiers PDF :

Les billets PDF sont générés dans : webapp/pdfs/
Nom du fichier : ticket_[id_reservation].pdf

Améliorations possibles :

Ajout d’un espace administrateur (gestion des vols et utilisateurs)
Envoi automatique du billet par mail via SMTP
Historique des réservations pour chaque passager
