<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
 $user_id = $_POST['uid'];
 $sql = "select * from USERMODEL where USER_ID='$user_id'";
 $result = mysqli_query($con, $sql);



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
     echo $users_found;
   }
 }
 else {
   echo "!";
 }
}

?>

