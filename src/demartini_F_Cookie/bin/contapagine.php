<?php 
session_start();
if(!isset($_SESSION["CNT"])) 
	$cnt=1;
else
	$cnt=$_SESSION["CNT"]+1;

// in ogni caso salva per prossima visita
	$_SESSION["CNT"]=$cnt;

?>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title> MYSQL </title>
	</head>
<body>
	<h1>Pagina di conteggi visite</h1>
	<h2> Buongiorno !! <?php print($oracorr); ?></h2>
	<?php
	if ($cnt==1)
		print("<h2> prima visita </h2>"); 
	else
		print("<h2> visita N. $cnt</h2>"); 
	?>
	<p> <a href="index.php">Indietro </a> </p>
	
</body>
</html>