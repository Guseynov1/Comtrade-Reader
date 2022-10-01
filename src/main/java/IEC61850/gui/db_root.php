<!DOCTYPE html>
<html lang="en">
<head>
    <meta> charset="UTF-8">
    <title>Operation</title>
</head>
<body>
<?php

$addressServer = "localhost";
$userName = "root";
$password = "root";
$dbName = "comtrade";

$connection = mysqli_connect("$addressServer", "$userName", "$password", "$dbName");
if ($connection -> connect_error) {
    die ("Connection failed: " . $connection -> connect_error);
}
echo "Connection has been successfully established";

$dbSQL = "CREATE DATABASE comtrade";
if ($connection -> query($dbSQL)) {
    echo "DataBase successfully created <br/>";
} else {
    echo "DataBase creation error: " . $connection -> error;
}

$tableSQL = "CREATE TABLE emergency (
        id    INT AUTO_INCREMENT PRIMARY KEY,
        times INT NULL,
        Ia    DOUBLE NULL,
        Ib    DOUBLE NULL,
        Ic    DOUBLE NULL,
        fIa   DOUBLE NULL,
        fIb   DOUBLE NULL,
        fIc   DOUBLE NULL,
        Ua    DOUBLE NULL,
        Ub    DOUBLE NULL,
        Uc    DOUBLE NULL)";

if ($connection -> query($tableSQL)) {
    echo "Table successfully created <br/>";
} else {
    echo "Table creation error: " . $connection -> error;
}
?>

</body>
</html>
