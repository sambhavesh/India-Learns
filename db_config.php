<?php
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "indialearns"); // database name
define('DB_SERVER', "localhost"); // db server

try{
	$conn = new PDO("mysql:host = server_name ; dbname = database_name ",DB_USER,DB_PASSWORD);
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);


}catch (PDOException $e) {
	echo 'Connection failed: ' . $e->getMessage();
}


?>