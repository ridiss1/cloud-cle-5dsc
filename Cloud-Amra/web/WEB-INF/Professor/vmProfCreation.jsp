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
                        
                        <li><a href="accueil_template" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-tasks">Template</span></a></li>

                        <li><a href="connexion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-power-off">Deconnexion</span></a></li>
                    </ul>
                </nav>

            </div>

        </div>
        <!-- Main -->
        <div id="main">
                        
            <section id="list_vm" class="vm">
                <div class="container">
                    <div class="navbar-header ">
                        <a class="navbar-brand active" href="#" ><h1>Gestion des VMs</h1></a>
                    </div>
                    <br/>
                    <br/>
                    <ul class="nav nav-tabs">
                        <li ><a href="vmProf">List VMs</a></li>
                        <li class="active"><a href="vmProfCreation">Creation</a></li>                  
                    </ul>
                </div>
            </section>
            <section id="creation_vm" >
                <div class="container">
                    <!-- <a href="#" class="list-group-item">24*7 support</a>
                    <a href="#" class="list-group-item">Free Window Space hosting</a>
                    <a href="#" class="list-group-item">Number of Images</a>
                    <a href="#" class="list-group-item">Renewal cost per year</a> -->
                    <h1>Creation d'un VM</h1>
                    <form method="post" action="vmProf" class="form-horizontal" role="form" >
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Ram</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="vm" id="vm">
                                    <option >256</option>
                                    <option >512</option>
                                    <option >1000</option>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Cpu</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="vm" id="vm">
                                    <option >1</option>
                                    <option >2</option>
                                    <option >3</option>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Disk</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="vm" id="vm">
                                    <option >5</option>
                                    <option >10</option>
                                    <option >15</option>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Template</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="vm" id="vm">
                                    <c:forEach items="${ListeTemplate}" var="template">
                                    <option >${template.libelle} </option> 
                                </c:forEach>
                                </select>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="vm" class="col-sm-2 control-label">Groupe</label>
                            <div class="col-sm-5">
                                <c:forEach items="${ListeGroupe}" var="groupe">
                                    <option >${groupe.libelle} </option> 
                                </c:forEach>
                            </div>  

                        </div>
                        
                        <div class="form-group">
                            <label for="libelle" class="col-sm-2 control-label">Hostname</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="libelle" name="hostname"
                                       placeholder="Entrez le nom de la machine">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="libelle" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="libelle" name="passwordDefault"
                                       placeholder="Entrez le mot de passe">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default" name="actionAdd" value="CreerContainer">Valider</button>
                            </div>
                        </div>

                    </form>

                </div>
                                    
                <div class="container four dark" id="status">
                        <c:if test="${InfoCreation && RequestCreation}">
                            <div class="container alert alert-success">La création est réussie. </div>
                        </c:if>                                  

                        <c:if test="${ (not InfoCreation) && RequestCreation}">
                            <div class="container alert alert-danger">Problème avec la création .</div>
                        </c:if>     
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

