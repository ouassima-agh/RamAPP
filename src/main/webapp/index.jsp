
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R&eacute;servation de Vol</title>
    <link rel="stylesheet" href="indexstyle.css">
</head>
<body>
<header>
    <div class="header-container">
        <a href="/" class="logo">
            <img src="https://th.bing.com/th/id/OIP.iMZoiJl4hWfbyjjPXcIGAwHaEn?rs=1&pid=ImgDetMain.jpg" alt="Royal Air Maroc" class="logo-img">
        </a>
        <nav class="header-nav">
            <ul class="nav-list">
                <li class="nav-item"><a href="index.jsp" class="nav-link active">HOME</a></li>
                <li class="nav-item"><a href="about.jsp" class="nav-link">ABOUT US</a></li>
                <li class="nav-item"><a href="login.jsp" class="nav-link">LOG IN </a></li>
                <li class="nav-item"><a href="register.jsp" class="nav-link">SIGN IN</a></li>


            </ul>
        </nav>
    </div>
</header>
<div class="background-slider">
    <div class="slide" style="background-image: url('https://th.bing.com/th/id/R.5e82021178037c0ae407d038bde91d05?rik=g6YKLZKvv%2ftcDg&pid=ImgRaw&r=0.jpg.jpg');"></div>
    <div class="slide" style="background-image: url('https://static.srpcdigital.com/styles/1037xauto/public/2024-01/484521_0.jpeg.jpg');"></div>
    <div class="slide" style="background-image: url('https://images.ctfassets.net/m9ph4qvas97u/2UKXBeDGt9bNOYeYe1s59u/84d96b7b7b18b304556c0685725d1b4d/oneworld-alliance-royal-air-maroc-RAM_787-9-plane.png?fm=webp&q=80.jpg');"></div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const slides = document.querySelectorAll(".slide");
        let currentIndex = 0;

        function showNextSlide() {
            const currentSlide = slides[currentIndex];
            const nextIndex = (currentIndex + 1) % slides.length;
            const nextSlide = slides[nextIndex];
            currentSlide.classList.remove("active");
            nextSlide.classList.add("active");
            currentIndex = nextIndex;
        }
        slides[currentIndex].classList.add("active");
        setInterval(showNextSlide, 3000);
    });
</script>

</body>
</html>
