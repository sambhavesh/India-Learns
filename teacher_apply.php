<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
  // include db connect class
    require_once __DIR__ . '/db_connect.php';
// array for JSON response
$response = array();
 
// check for required field


// check for required fields
if (isset($_POST['CITY_NAME']) ){
 
    $city_name = $_POST['CITY_NAME'];
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("SELECT ID FROM CITY WHERE CITY_NAME='$city_name'");
    $row=mysql_fetch_row($result); 
    $stime = $_POST['STIME'];
    $etime = $_POST['ETIME'];
    
    
    // check if row inserted or not
    if (mysql_num_rows($result)==0) {
        // successfully FOUND THE CITY in database
         // failed to insert row
        $response["success"] = 0;
        $response["message"] = "1 NO MATCHING CITY FOUND";
 
        // echoing JSON response
        //echo json_encode($response);
        echo "city_not_found";

    }
    else 
    {
        $response["data"] = array();
        $result1 = mysql_query("SELECT * FROM CENTRES WHERE ID1=$row[0] AND FLAG=0 AND STIME<=$stime AND ETIME>=$etime");
        if(mysql_num_rows($result1)==0)
        { 
            
            $response["success"] = 0;
            $response["message"] = "2 NO MATCHING CENTRE FOUND"; 
            echo "no_matched_centre";
                
        }
        else
        {   
            while($row1 = mysql_fetch_assoc($result1))
            {
                $product = array();
                $result2 = mysql_query("SELECT * FROM INFO WHERE CENTRE_ID = $row1[ID]");
                if(mysql_num_rows($result2)==0)
                {
                     $product['latitude'] = $row1['LATITUDE'];
                     $product['longitude'] = $row1['LONGITUDE'];
                     $product['id'] = $row1['ID'];
                     array_push($response["data"],$product);
                }
                else
                {
                while($row2 = mysql_fetch_assoc($result2))
                {
                    if($row2['TIME1'] == $stime)
                    {
                        $flag=1;
                        break;
                    }
                }
                if(!$flag)
                {
                    $product['latitude'] = $row1['LATITUDE'];
                    $product['longitude'] = $row1['LONGITUDE'];
                    $product['id'] = $row1['ID'];
                    array_push($response["data"],$product);
                }
                /*else
                {
                    echo "not_available";
                }*/
                }
            }
            echo json_encode($response);   
        }
     
        // echoing JSON response
        
    }
    }
    else{
         // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo "city not set";
    }


?>