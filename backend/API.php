<?php

include_once __DIR__ . '/config.php';

$conn = new mysqli($servername, $username, $password, $db_name);

if($conn -> connect_error)
{
	// echo "Something bad :(";
}

else 
{
	 // echo "Connected Successfully :)";
	 
	 $sql = "SELECT * FROM products"; 
	 $result = $conn->query($sql);
	 
	 if($result -> num_rows>0)
	 {
		 while($row = $result->fetch_assoc())
		 {
			 $item["name"] = $row["Name"];
			 $item["price"] = $row["Price"];
			 $item["img"] = $row["Picture_Link"];
			 
			 $product[] = $item; // to save all records in one array
		 }
		 //as json array (use "json_encode(nameOfArray)"):
		    echo json_encode($product);
		
	 }
	 
	 else // something error in dbase_add_record
	 {
		     $item["name"] = $row["Name"];
			 $item["price"] = $row["Price"];
			 $item["img"] = $row["Picture_Link"];
			 
			 $product[] = $item;
			  echo json_encode($product);
	 }
	 
	 $conn -> close();
}


?>