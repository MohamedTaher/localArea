<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 //set the SECOND_ACCEPT field to 1 in the relation between source_id and destination_id
 
 $source = $_POST['first_user'];
 $destination = $_POST['second_user'];

 $sql = "update FRIENDREQUESTMODEL set SECOND_ACCEPTED=1 where NOTIFICATION_ID='$source' AND REQUEST_ID	='$destination';";

  if (mysqli_query($con,$sql))
 {
     echo "accepted";
 }
 else 
 {
     echo "Error in insertion" . mysqli_error($con);
 }

}

?>

