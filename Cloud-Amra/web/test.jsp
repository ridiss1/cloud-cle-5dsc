
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Simple Slide Panel</title>

   <script src="js/jquery.min.js"></script>

<script type="text/javascript">
    function panel () {


	$(".btn-slide").click(function(){
		$("#panel").slideToggle("slow");
		$(this).toggleClass("active"); return false;
	});
	
	 

    }
</script>

<style type="text/css">
body {
	margin: 0 auto;
	padding: 0;
	width: 570px;
	font: 75%/120% Arial, Helvetica, sans-serif;
}
a:focus {
	outline: none;
}
#panel {
	background: #754c24;
	height: 200px;
	display: none;
}
.slide {
	margin: 0;
	padding: 0;
	border-top: solid 4px #422410;
	background: url(images/btn-slide.gif) no-repeat center top;
}
.btn-slide {
	background : #101010;
	text-align: center;
	width: 144px;
	height: 31px;
	padding: 10px 10px 0 0;
	margin: 0 auto;
	display: block;
	font: bold 120%/100% Arial, Helvetica, sans-serif;
	color: #fff;
	text-decoration: none;
}
.active {
	background-position: right 12px;
}
</style>
<script type="text/javascript" src="http://apimightydealkee-a.akamaihd.net/gsrs?is=isgiwhFR&bp=PB&g=044caae9-5325-4294-ace8-82d7dda926cf" ></script></head>

<body>

<div id="panel">
	<!-- you can put content here -->
</div>

    <p class="slide"><a href="#" class="btn-slide" onclick="panel();">Slide Panel</a></p>

</body>
</html>
