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
        <title>Virtuel Machine</title>
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

                        <li><a class="active" href="vmProf" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtuel Machine</span></a></li>
                        
                        <li><a href="templateProf" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        <!-- Main -->
        <div id="main">

            <section id="list_vm" class="vm">
                <div class="container">
                    <!-- <a href="#" class="list-group-item">24*7 support</a>
                    <a href="#" class="list-group-item">Free Window Space hosting</a>
                    <a href="#" class="list-group-item">Number of Images</a>
                    <a href="#" class="list-group-item">Renewal cost per year</a> -->
                    <h1>Modify VM</h1>
                    <form method="post" action="vmProfModify">
                            <h3>RAM:</h3>
                            <select name="ram">    
                                <option>256</option>
                                <option>512</option>
                                <option>1000</option>
                            </select>
                            <br/>
                            <h3>CPU:</h3>
                            <select name="cpus">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                            </select>
                                                        
                            <br/>
                            <h3>DISK:</h3>
                            <select name="disk">
                                <option>5</option>
                                <option>10</option>
                                <option>15</option>
                            </select>
                                                        
                            <br/>
                            <h3>Template:</h3>
                            <select name="template">
                                <c:forEach items="${sessionScope.ListeTemplate}" var="template">
                                    <option>${template.file} </option> 
                                </c:forEach>
                            </select>
                            
                            <br/>
                            <h3>Groupe:</h3>
                            <select name="groupe">
                                <c:forEach items="${sessionScope.ListeGroupe}" var="groupe">
                                    <option>${groupe.libelle} </option> 
                                </c:forEach>
                            </select>
                            
                            <br/>
                            <h3>Mot de passe:</h3>
                            <input type="password" name="passwordDefault" class="form-control input-lg" placeholder="Password">
                        <center> <input type="submit" name="actionAdd" value="CreerContainer"></center>
                    </form>

                </div>
            </section>


            <section id="content" class="one dark cover">

                <div class="container">
                    <h1>Hello</h1>
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

