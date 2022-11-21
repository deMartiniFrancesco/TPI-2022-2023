<?php
// gestione cookie
//Il server salva sul browser data e ora dell' ultima visita
// se c'è 

if (!isset($_COOKIE["DUV"]))
	$duv = "";
else
	$duv = $_COOKIE["DUV"];

$ipaddress = getenv("REMOTE_ADDR");
setcookie("DUV", $ipaddress, time() + (100), "/"); // 100 sec
?>
<!DOCTYPE HTML>
<html>

<head>
	<meta charset="utf-8" />
	<title> MYSQL </title>
</head>

<body>
	<h1>Home Page</h1>
	<h2> Buongiorno !!</h2>
	<?php
	if ($duv != "")
		print("<h2> ultima visita $duv </h2>");
	?>
	<p> <a href="visalunni.php">Visualizza alunni </a> </p>
	<p> <a href="ricercaalunno.php">Ricerca alunno </a> </p>
	<p> <a href="contapagine.php">Contapagine </a> </p>

</body>

</html>