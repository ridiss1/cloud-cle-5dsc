<%-- 
    Document   : vmProf
    Created on : Jan 5, 2015, 11:04:28 AM
    Author     : Duy Duc
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Template</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.scrolly.min.js"></script>
        <script src="js/jquery.scrollzer.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
        <link href="css/my_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        </noscript>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->

    </head>
    <body>
        <!-- Header -->
        <div id="header" class="skel-layers-fixed">

            <div class="top">

                <!-- Logo -->
                <div id="logo">
                    <span class="image avatar48"><img src="images/avatar.jpg" alt="" /></span>
                    <h1 id="title">${sessionScope.sessionUser.prenom} ${sessionScope.sessionUser.nom}</h1>
                    <p>Professor</p>
                </div>

                <!-- Nav -->
                <nav id="nav">

                    <ul>
                        <li><a href="accueilProf" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Accueil</span></a></li>

                        <li><a href="vmProf" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtuel Machine</span></a></li>

                        <li><a class="active" href="templateProf" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>



            </div>

        </div>
        <!-- Main -->
        <div id="main">


            
            <section id="contact" class="four">
                <div class="container">
                <div class="navbar navbar-inverse">
                        <ul class="nav navbar-nav"  >
                            <li class="active"> <a href="#">Accueil</a> </li>
                            <li class="active"> <a href="#">Create</a> </li>
                            <li> <a href="#">Show</a> </li>
                            <li> <a href="#">Delete</a> </li>
                        </ul>
                    </div>
                </div>
                <div class="container">

                    <header>
                        <h2>Creation d'une template</h2>
                    </header>

                    <form method="post" action="#">
                        <div class="row 50%">
                            <div class="6u"><input type="text" name="name" placeholder="Name" /></div>
                            
                        </div>
                        <div class="row 50%">
                         
                            <div class="6u"><input type="text" name="name" placeholder="Name"/></div>
                           
                        </div>
                        <div class="row 50%">
                            <div class="12u">
                                <textarea name="message" placeholder="Message"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="12u">
                                <input type="submit" value="Send Message" />
                            </div>
                        </div>
                    </form>

                </div>
            </section>



        </div>




        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <ul class="copyright">
                <li>&copy; Insa Toulouse. All rights reserved.</li>
            </ul>

        </div>

    </body>

</html>
