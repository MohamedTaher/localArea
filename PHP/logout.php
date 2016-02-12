<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 $user_id = $_POST['user_id'];

 $sql = "update USERMODEL set ACTIVE=0 where USER_ID=$user_id";
 mysqli_query($con, $sql);
}

?>

