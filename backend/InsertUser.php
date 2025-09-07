<?php
    include_once __DIR__ . '/config.php';

	
	$Name = $_POST["name"];
	$Pass = $_POST["password"];
	$Email = $_POST["email"];
	$phone = $_POST["phone"];
	
	
 
   // echo "Name >>> ".$Name."<br>";
   // echo "Email >>> ".$Email."<br>";
    //echo "pass >>> ".$Pass."<br>";
	
	
	$conn = new mysqli($servername,$username,$password,$db_name);
	
	if($conn->connect_error){
		
		
		//die("not connected successfully :(");
		
	}else{
		
		//echo "connected successfully :)"."<br>";
		$sql = "SELECT * FROM user WHERE name = $Name AND password = $Pass";
		$result = $conn->query($sql);
		if($result -> num_rows>0){
			
				$user = array(
				
				"name" => "no data",
				"email" => "no data",
				"password" => "no data",
				"phone" => "no data"
				);
				$product["error"] = true;
				$product["message"]= "username or email already exist";
				$product["user"] = $user;
				
				echo json_encode($product);
				
				
		}else{
			
			
			
			
			$sql2 = "INSERT INTO user (name,password,email,phone) VALUES('$Name','$Pass','$Email','$phone')";
			if($conn->query($sql2) === TRUE){
				
			
				$user = array(
				
				"name" => $Name,
				"email" => $Email,
				"password" => $Pass,
				"phone" =>$phone
				);
				$product["error"] = false;
				$product["message"]= "User Registed successfully";
				$product["user"] = $user;
				
				echo json_encode($product);
				
				
			}else{
				
				$user = array(
				"name" => "no data",
				"email" => "no data",
				"password" => "no data",
				"phone"=> "no data"
				);
				$product["error"] = true;
				$product["message"]= "something goes wrong!";
				$product["user"] = $user;
				
				echo json_encode($product);
				
			}
			
			
		}
	
	}

?>