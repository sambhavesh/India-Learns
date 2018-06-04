<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 // include db connect class

 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['CITY_NAME']) )  
{
    require_once __DIR__ . '/db_connect.php';

    $city_name = $_POST['CITY_NAME'];
    
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("SELECT ID FROM CITY WHERE CITY_NAME='$city_name'");
    //added by Ankit

    //get the values..
    $latitude=$_POST['LATITUDE'];
    $longitude=$_POST['LONGITUDE'];
    $children=$_POST['CHILDREN'];
    $stime=$_POST['STIME'];
    $etime=$_POST['ETIME'];
    $chead=$_POST['CHEAD'];
    $contact=$_POST['CONTACT'];
    

    // check if row inserted or not
    if (mysql_num_rows($result)==0) {
        // NOT FOUND THE CITY in database
        $result1=mysql_query("INSERT INTO CITY(CITY_NAME) VALUES('$city_name')");
        $id = mysql_insert_id();
        echo $id;
        $result2 = mysql_query("INSERT INTO CENTRES(LATITUDE,LONGITUDE,CHILDREN,STIME,ETIME,CHEAD,CONTACT,FLAG,ID1) VALUES($latitude,$longitude,$children,$stime,$etime,'$chead',$contact,0,$id)");
        
        if(!$result2){
            $response["success"] = 0;
        $response["message"] = "Oops! result1 An error occurred.";
 
        // echoing JSON response
        //echo json_encode($response);
        echo "failed1";
        }
        else {
        // failed to insert row
        


        $response["success"] = 1;
            $response["message"] = "SUCCESSFULLY INSERTED...";  
            // echoing JSON response
            //echo json_encode($response);    
            echo "new city made";
        }

        


        
    }
    else
    {
        
        
        $row=mysql_fetch_row($result);
        echo $row[0];
       // $row=mysql_fetch_row(mysql_query("SELECT ID FROM CITY WHERE CITY_NAME='$city_name'"));
        $result1 = mysql_query("INSERT INTO CENTRES(LATITUDE,LONGITUDE,CHILDREN,STIME,ETIME,CHEAD,CONTACT,FLAG,ID1) VALUES($latitude,$longitude,$children,$stime,$etime,'$chead',$contact,0,$row[0])");
        
        if(!$result1){
            // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! result1 An error occurred.";
 
        // echoing JSON response
        //echo json_encode($response);
        echo "failed";
        }
 
        
        else {
        
        $response["success"] = 1;
            $response["message"] = "SUCCESSFULLY INSERTED...";      
            // echoing JSON response
            //echo json_encode($response);
            echo "inserted";
        }

       
    }
}    
else {
    // failed to insert row
    $response["success"] = 0;
    $response["message"] = "Oops! result2 An error occurred.";

    // echoing JSON response
    //echo json_encode($response);
    echo "failed2";
}

?>