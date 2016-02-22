<?php
$con = mysqli_connect("localhost", "root", "", "localArea");

if(!$con) {
	echo("-1");
} else {
	$uid = $_POST['uid'];
	$mode = $_POST['mode'];
	$sql = "SELECT * FROM MESSAGETHREAD WHERE PARTICIPANTS like %{$uid}%;";
	$result = mysqli_query($con, $sql);
	if(!$result) {
		echo "-1";
	} else {
		//Each message is of this format: [numberOfThreads]/[NumberOfNames]/[Name/Name/...]/[NumberOfMessages]/[MessageLength]/[SenderName:Message][MessageLength][SenderName:Message]/...
		//threadid, participants
		//1,2-3-4-5/2,1-2-3-4
		$ids = "";
		$delimiter = 0;
		while($row = mysqli_fetch_assoc($result)) {
			if($delimiter == 1){
				$ids = $ids.'/';
			}
			$ids = $ids.$row['THREADID'].','.$row['PARTICIPATNS'];
			$delimiter = 1;
		}
		if($mode == "id") {
			echo $ids;
		} else {
			//return names
			;
		}
	}
}








?>