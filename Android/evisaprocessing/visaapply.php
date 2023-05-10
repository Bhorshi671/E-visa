
<?php
$connect=mysql_connect("localhost","root","");
mysql_select_db("evisaprocessing",$connect);


$name=$_REQUEST['gname'];
	$gname1=$_REQUEST['gname1'];
				$price=$_REQUEST['gtype'];
				$Age=$_REQUEST['Age'];
				$Birth=$_REQUEST['Birth'];
				$Number=$_REQUEST['Number'];
		   echo $base=$_REQUEST['image'];				
	$destinationFileName = "gimg".date('YmdHis');

	

     $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
    $file = fopen('upload/'. $destinationFileName .'.jpg', 'wb');
    fwrite($file, $binary);
    fclose($file);
	
					
												
	 if($connect)
	 	{
			$sqlCheckUname = mysql_query("Insert into visaapply values('','$name','$gname1','$price','$Age','$Birth','$Number','$destinationFileName')");
				
				if($sqlCheckUname)
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
	
   // echo 'Image upload complete!!, Please check your php file directory';
?>

