<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
   
 $first = $_POST['second_user'];
 $second = $_POST['first_user'];
 
 $sql = "select * from FRIENDREQUESTMODEL where ((REQUEST_ID=$first and NOTIFICATION_ID=$second) and   FIRST_ACCEPTED=1 and SECOND_ACCEPTED=0);";
 $result = mysqli_query($con, $sql);
 $result = $result->num_rows;
 if($result == 0) {
 echo "NO";
 } else {
 echo "YES";
 }
}

?>

