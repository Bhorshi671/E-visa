<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("evisaprocessing",$connect);

 $eusername = $_GET["eusername"];
 $epassword = $_GET["epassword"];
	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("SELECT * FROM userregister WHERE username='$eusername' && password='$epassword'");
				$u_name_query =  mysql_num_rows($sqlCheckUname);
				if($u_name_query == 1)
				{
				echo "Success";
				}
				else
				{
				echo "failed";
				}
 		}
	else
		{
		echo "Connection Error";
		}
	

?>
