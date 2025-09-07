<?php

include_once __DIR__ . '/config.php';

	
/* values"data" that entered by user, 
   the data will received through ( $_POST[] array ) and then storing it in $Email and $Pass variables*/
	$Email = $_POST["email"];
	$Pass = $_POST["password"];
	
	
 
   // echo "Email >>> ".$Email."<br>";
   // echo "pass >>> ".$Pass."<br>";
	
	
	$conn = new mysqli($servername,$username,$password,$db_name);
	
	if($conn->connect_error){
		
		
		//die("not connected successfully :(");
		
	}else{
		
		
		//echo "connected successfully :)";
		$sql = "SELECT * FROM user WHERE email ='$Email' AND password = '$Pass'";
		$result = $conn->query($sql); // storing  the respone in $result
		if($result ->num_rows>0){ 
			
			//echo "some data selected";
			while($row = $result -> fetch_assoc()){
				
				$user = array(
				
				"id" => $row["id"],
				"name" => $row["name"],
				"email" => $row["email"],
				"password" => $row["password"],
				"phone" => $row["phone"]
				);
				
				$product["error"] = false;
				$product["message"]= "Loged In Successfully";
				$product["user"] = $user;
				
				
				
				
			}
			
			 echo json_encode($product);
			
			
			
		}else{
			
			
				$user = array(
				
				"id" => 0,
				"name" => "No Data",
				"email" => "No Data",
				"password" => "No Data",
				"phone" => "No Data"
				);
				
				$product["error"] = true;
				$product["message"]= " Faield to Logging";
				$product["user"] = $user;
			
			echo json_encode($product);
		}
		
	}

?>