<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
 
    $city_name = 'Goooooa';
    
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("SELECT ID FROM city WHERE CITY_NAME='$city_name'");
    
    //get the values..
    $latitude=13.2;
    $longitude=12.2;
    $children=15;
    $stime=2;
    $etime=3;
    $chead='RAJU Rastogi';
    $contact=9415754306;
    $row=mysql_fetch_row($result); 
   

    // check if row inserted or not
    if (!$result) {
        // successfully FOUND THE CITY in database
    $result1 = mysql_query("INSERT INTO CENTRES(LATITUDE,LONGITUDE,CHILDREN,STIME,ETIME,CHEAD,CONTACT,FLAG,ID1) VALUES($latitude,$longitude,$children,$stime,$etime,'$chead',contact,0,$row[0])");
        
        if($result1){
        $response["success"] = 1;
            $response["message"] = "SUCCESSFULLY INSERTED...";      
            // echoing JSON response
            echo json_encode($response);
        }
 
        
        else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! result1 An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
        }
    }
    else
    {
    //CENTER NOT FOUND...
    $result1=mysql_query("INSERT INTO CITY(CITY_NAME) VALUES('$city_name')");
    $id = mysql_insert_id();
    //echo $id;
    $result2 = mysql_query("INSERT INTO CENTRES(LATITUDE,LONGITUDE,CHILDREN,STIME,ETIME,CHEAD,CONTACT,FLAG,ID1) VALUES($latitude,$longitude,$children,$stime,$etime,'$chead',contact,0,$id)");
    
    if($result2){
        $response["success"] = 1;
            $response["message"] = "SUCCESSFULLY INSERTED...";  
            // echoing JSON response
    echo json_encode($response);    

    }
    
    else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! result2 An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
}
?>