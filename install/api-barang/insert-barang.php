<?php

/*
 * Insert barang
 */
mysql_connect('localhost', 'root', 'root');
mysql_select_db('surya_sakti');
// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['nama']) && isset($_POST['alamat']) && isset($_POST['hp']) && isset($_POST['kode_barang']) && isset($_POST['harga_barang']) && isset($_POST['jml_barang']) && isset($_POST['total'])) {

    $nama = $_POST['nama'];
    $alamat = $_POST['alamat'];
    $hp = $_POST['hp'];
    $kode_barang = $_POST['kode_barang'];
    $harga_barang = $_POST['harga_barang'];
    $jml_barang = $_POST['jml_barang'];
    $total = $_POST['total'];

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO pesanan(nama, alamat, hp,kode_barang,harga_barang,jml_barang,total)
        VALUES('$nama', '$alamat', '$hp', '$kode_barang', '$harga_barang', '$jml_barang', '$total')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>