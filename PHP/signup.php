<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 
 $name = $_POST['uname'];
 $password = $_POST['upassword'];
 $email = $_POST['uEmail'];

 $sql = "INSERT INTO USERMODEL (USER_NAME, USER_PASSWORD, USER_EMAIL)
	VALUES ('$name','$password','$email');";

 if (mysqli_query($con,$sql))
 {
     echo "Row inserted";
 }
 else 
 {
     echo "Error in insertion" . mysqli_error($con);
 }
}

?>
