<?php

mysql_connect('localhost', 'root', 'root');
mysql_select_db('surya_sakti');
$result = mysql_query("SELECT * FROM tbl_barang") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["barang"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $barang = array();
        $barang["kode_barang"] = $row["kode_barang"];
        $barang["nama_barang"] = $row["nama_barang"];
        $barang["harga"] = $row["harga"];


        // push single product into final response array
        array_push($response["barang"], $barang);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";

    // echo no users JSON
    echo json_encode($response);
}
?>