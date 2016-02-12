<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 //Send the user name as key->value where the key is 'uname'
 $user_name = $_POST['uname'];
 $sql = "select * from USERMODEL where USER_NAME='$user_name'";
 $result = mysqli_query($con, $sql);


 //The pattern goes like this ==> 'username1-useremail1~username2-useremail2
 //If found nothing in the database, printing '!'
 $users_found = '';

 if($result) {
 $insert_delimiter = 0;
 while($row = mysqli_fetch_array($result)) {
   if($insert_delimiter) {
     $users_found = $users_found."~";
   }
   $users_found = $users_found.$row['USER_ID']."-".$row['USER_NAME']."-".$row['USER_EMAIL'];
   $insert_delimiter = 1;
 }
 } else {
 $users_found = '!';
 }
 echo $users_found;
}

?>

