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
                        <li><a  href="accueilProf" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Accueil</span></a></li>

                        <li><a href="vmProf" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Virtuel Machine</span></a></li>

                        <li><a class="active" href="accueil_template" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>



            </div>

        </div>

        <!-- Main -->
        <div id="main">


            <section class="one dark cover">
                <div class="container">
                    <nav class="navbar navbar-inverse " role="navigation">
                        <div class="navbar-header ">
                            <a class="navbar-brand active" href="#" >&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gestion des templates &nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 &nbsp;&nbsp;&nbsp;</a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="bordure active"><a href="create_template">Creation</a></li>

                            <li class="bordure"><a href="show_template">&nbsp;&nbsp;&nbsp;Affichage</a></li>

                           
                        </ul>    


                    </nav>
                </div>
            </section>
            <section id="list_vm" class="vm">
                
                <div class="container">
                    <header>  <h2>Création des templates</h2></header>
                    
                    <form class="form-horizontal" role="form" action="create_template" method="post">
                        
                        
                     
                        <div class="form-group">
                            <label for="libelle" class="col-sm-2 control-label">Libelle</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="libelle" name="libelle"
                                       placeholder="Entrez le libelle du template">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Vm</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="vm" id="vm">
                                    <c:forEach items="${ListVmRun}" var="vm">
                                    <option>${vm.id}</option>
                                    
                                     </c:forEach>
                                </select>
                            </div>  

                        </div>


                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Valider</button>
                            </div>
                        </div>
                    </form>

                </div>
                
               <c:if test="${template && not get}">
                     <div class="container alert alert-success"> Le template ${Libelle} a été avec succès .</div>
               </c:if>
               
               <c:if test="${ not template && not get }">
                     <div class="container alert alert-danger"> Le template ${Libelle} n'a pas été créé .</div>
               </c:if>
               
                     
                   
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

