<?php

   include_once __DIR__ . '/config.php';

	$ID = $_POST["id"];
	
	
	$conn = new mysqli($servername,$username,$password,$db_name);
	
	if($conn->connect_error){
		
		
		//die("not connected successfully :(");
	
	}else{
		
		//echo "connected successfully ";
		
		$sql = "DELETE FROM user WHERE Id = '$ID'";
	
	if($conn -> query($sql) === TRUE){
		
		
		
		 //echo "the record deleted successfully :)";
		 
				$product["error"] = false;
				$product["message"]= "Account Deleted successfully";
				
				echo json_encode($product);
	}else{
		
		
		//echo "Error: ".$sql."<br>".$conn ->error;
		
		        $product["error"] = true;
				$product["message"]= "something goes wrong!";
				
				echo json_encode($product);
	}
		
	}
	
	

?>