
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
        <script src="js/Chart.js"></script>
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
                    <p>Etudiant</p>
                </div>

                <!-- Nav -->
                <nav id="nav">

                    <ul>
                        <li><a href="accueil" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Accueil</span></a></li>

                        <li><a class="active" href="vm" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtuel Machine</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        <!-- Main -->
        <div id="main">

            <section id="listvm" class="my_list_vm">
                <div class="container">
                    <a href="#" class="list-group-item active">
                        Liste des machines virtuelles
                    </a>
                    <c:forEach items="${sessionScope.ListContainer}" var="container">
                        <a href="#" class="list-group-item">${container.hostname}</a>
                    </c:forEach>


                </div>
            </section>
            <c:forEach items="${sessionScope.ListContainer}" var="container">

                <section id="content${container.vmid}" class="backvm">
                    <div  class="container" >
                        <p id="alignright">
                            <a class="btn btn-default btn-lg active" href="#" role="button">Start</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="btn btn-default btn-lg active" href="#" role="button">Console</a>
                        </p>

                    </div>


                    <div  class="container">

                        <table class="table table-hover">
                            <caption><h1>Vm : </h1></caption>
                            <thead>
                                <tr>
                                    <th>Hostname</th>
                                    <th>${container.hostname}</th>
                                    <th>&nbsp;&nbsp;</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Vmid</td>
                                    <td>${container.vmid}</td>
                                    <td>&nbsp;&nbsp;</td>

                                </tr>
                                <tr>
                                    <td>Address</td>
                                    <td></td>
                                    <td>&nbsp;&nbsp;</td>

                                </tr>
                                
                                
                                
                                
                            </tbody>
                        </table>

                    </div>

                </section>

                <section id="graphe_cpu${container.vmid}" class="one dark cover">

                    <div id="tabstat" class="container">
                        <table class="table table-hover">
                            <caption class="titrestat"><h1>MEMORY</h1></caption>
                            <thead>
                                <tr>
                                    <th>RAM</th>
                                    <th>CPU</th>
                                    <th>DISK</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><canvas id="ramdata${container.vmid}" width="300" height="300"/></td>
                                    <td><canvas id="cpudata${container.vmid}" width="300" height="300"/></td>
                                    <td><canvas id="memdata${container.vmid}" width="300" height="300"/></td>

                                </tr>


                            </tbody>
                        </table>

                    </div>

                </section>
            </c:forEach>



        </div>




        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <ul class="copyright">
                <li>&copy; Insa Toulouse. All rights reserved.</li>
            </ul>

        </div>
        <script type="text/javascript" src="js/graphe.js"></script>

    </body>

</html>
