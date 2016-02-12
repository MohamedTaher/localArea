<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 $uname = $_POST['uname']; 
 $upass = $_POST['upassword'];

 $sql = "SELECT USER_ID FROM USERMODEL where USER_NAME = '$uname' and USER_PASSWORD = '$upass'";
 $result = mysqli_query($con,$sql);

 $response = mysqli_fetch_array($result); 

 $data = $response[0];

 if($data) 
 { 
 echo $data;
 $sql = "update USERMODEL set ACTIVE=1 where USER_ID='$data'";
 mysqli_query($con, $sql);
 } 
 else 
 { 
 echo '-1'; 
 } 
}

?>
