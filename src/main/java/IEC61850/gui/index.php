<?php $ini = parse_ini_file('config.ini');
$db_host = $ini['host'];
$db_name = $ini['name'];
$db_table = $ini['table'];
$db_user = $ini['user'];
$db_password = $ini['password'];
$connection = new mysqli($db_host, $db_user, $db_password, $db_name);
?>

<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
    <title> emergency </title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            const filteredOptions = {
                curveType: 'function',
                legend: {position: 'top', textStyle: {color: 'black', fontSize: 16}},
                backgroundColor: 'white',
                vAxis: {
                    viewWindow: {min: 0, max: 2, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'red'
                },
                hAxis: {
                    viewWindow: {min: 0, max: 7000, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'black'
                }
            };


            const IaData = google.visualization.arrayToDataTable([['time', 'phsA', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Ia, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Ia'] . ", 'color: blue'],";
                }
                ?>
            ]);

            const Ia = new google.visualization.LineChart(document.getElementById('Ia'));
            Ia.draw(IaData, filteredOptions);

            const IbData = google.visualization.arrayToDataTable([['time', 'phsB', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Ib, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Ib'] . ", 'color: blue'],";
                }
                ?>
            ]);

            const Ib = new google.visualization.LineChart(document.getElementById('Ib'));
            Ib.draw(IbData, filteredOptions);

            const IcData = google.visualization.arrayToDataTable([['time', 'phsC', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Ic, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Ic'] . ", 'color: blue'],";
                }
                ?>
            ]);

            const Ic = new google.visualization.LineChart(document.getElementById('Ic'));
            Ic.draw(IcData, filteredOptions);

            const instantOptions = {
                curveType: 'function',
                legend: {position: 'top', textStyle: {color: 'black', fontSize: 16}},
                backgroundColor: 'white',
                vAxis: {
                    viewWindow: {min: -2, max: 2, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'red'
                },
                hAxis: {
                    viewWindow: {min: 0, max: 7000, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'black'
                }
            };


            const fIaData = google.visualization.arrayToDataTable([['time', 'phsFa', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.fIa, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['fIa'] . ", 'color: green'],";
                }
                ?>
            ]);

            const fIa = new google.visualization.LineChart(document.getElementById('fIa'));
            fIa.draw(fIaData, instantOptions);

            const fIbData = google.visualization.arrayToDataTable([['time', 'phsFb', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.fIb, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['fIb'] . ", 'color: green'],";
                }
                ?>
            ]);

            const fIb = new google.visualization.LineChart(document.getElementById('fIb'));
            fIb.draw(fIbData, instantOptions);

            const fIcData = google.visualization.arrayToDataTable([['time', 'phsFc', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.fIc, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['fIc'] . ", 'color: green'],";
                }
                ?>
            ]);

            const fIc = new google.visualization.LineChart(document.getElementById('fIc'));
            fIc.draw(fIcData, instantOptions);

            const voltageOptions = {
                curveType: 'function',
                legend: {position: 'top', textStyle: {color: 'black', fontSize: 16}},
                backgroundColor: 'white',
                vAxis: {
                    viewWindow: {min: -140, max: 140, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'red'
                },
                hAxis: {
                    viewWindow: {min: 0, max: 7000, titleTextStyle: {color: 'black'}},
                    gridlines: {color: 'black'},
                    baselineColor: 'black'
                }
            };


            const UaData = google.visualization.arrayToDataTable([['time', 'phsUa', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Ua, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Ua'] . ", 'color: purple'],";
                }
                ?>
            ]);

            const Ua = new google.visualization.LineChart(document.getElementById('Ua'));
            Ua.draw(UaData, voltageOptions);

            const UbData = google.visualization.arrayToDataTable([['time', 'phsUb', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Ub, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Ub'] . ", 'color: purple'],";
                }
                ?>
            ]);

            const Ub = new google.visualization.LineChart(document.getElementById('Ub'));
            Ub.draw(UbData, voltageOptions);

            const UcData = google.visualization.arrayToDataTable([['time', 'phsUc', {role: "style"}],
                <?php $sql = "SELECT comtrade.emergency.Uc, comtrade.emergency.id FROM $db_table WHERE comtrade.emergency.id < 14000";
                $result = $connection->query($sql);
                while ($row = $result->fetch_assoc()) {
                    $timestamp = $row['id'];
                    echo "['" . $timestamp . "'," . $row['Uc'] . ", 'color: purple'],";
                }
                ?>
            ]);

            const Uc = new google.visualization.LineChart(document.getElementById('Uc'));
            Uc.draw(UcData, voltageOptions);

        }
    </script>

    <h1 class="headers">Graphs of instantaneous currents</h1>

    <div id = "fIa"> </div>
    <div id = "fIb"> </div>
    <div id = "fIc"> </div>

    <h1 class="headers">Graphs of filtered currents</h1>

    <div id = "Ia"> </div>
    <div id = "Ib"> </div>
    <div id = "Ic"> </div>

    <h1 class="headers">Graphs of instantaneous voltages</h1>

    <div id = "Ua"> </div>
    <div id = "Ub"> </div>
    <div id = "Uc"> </div>

    <h2 class="headers">Начало аварийного режима: на 150 мс </h2>
    <h2 class="headers">Время короткого замыкания: 100 мс </h2>


</head>
<body>

</body>

</html>






