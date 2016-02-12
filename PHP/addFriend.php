<?php 
$con = mysqli_connect("localhost","root","20130204","localArea");
if (!$con)
{
 echo('Could not connect: ' . mysqli_error());
}
else
{
mysqli_query($con,'SET foreign_key_checks = 0');

   //Input is 'source_id' and 'destination_id' and the output is nothing, just makes a request in the database where FIRST_ACCEPTED = 1 and  SECOND_ACCEPTED = 0
 $source_id = $_POST['source_id'];
 $des_id = $_POST['des_id'];

echo "'$des_id'" ;

 $sql = "INSERT INTO FRIENDREQUESTMODEL(FIRST_ACCEPTED, SECOND_ACCEPTED, REQUEST_ID, NOTIFICATION_ID)
		VALUES(1, 0, '$source_id', '$des_id' );";

  if (mysqli_query($con,$sql))
 {
     echo "Row inserted";
 }
 else 
 {
     echo "Error in insertion".$des_id  . mysqli_error($con);
 }
  mysqli_query($con,'SET foreign_key_checks = 1'); 
}

?>

