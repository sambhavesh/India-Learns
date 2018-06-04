<?php
 
require_once __DIR__ . '/db_connect.php';

if (isset($_POST['CENTRE_ID']) ){
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row 
    $namet = $_POST['NAME_T'];
    $time1 = $_POST['STIME'];
    $time2 = $_POST['ETIME'];
    $contact = $_POST['CONTACT'];
    $centreid = $_POST['CENTRE_ID'];

    $result = mysql_query("INSERT INTO INFO(NAME_T, CONTACT, TIME1, TIME2, CENTRE_ID) VALUES ('$namet',$contact,$time1,$time2,$centreid)");


    
    
    // check if row inserted or not
    if (mysql_num_rows($result)==0) {
        echo "failed";

    }
    else {
        echo "success";
    }


}
else
{
    echo "not_set";
}

?>