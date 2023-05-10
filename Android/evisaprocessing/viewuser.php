<?php

$connect=mysql_connect("localhost","root","");
mysql_select_db("evisaprocessing",$connect);





$sql3=mysql_query("SELECT * FROM userregister");

if(sql3){
while($res=mysql_fetch_array($sql3)){
 $tem[] =array("gname"=>$res['name'],"count"=>$res['address'],"totp"=>$res['gender'],"contact"=>$res['contact'],"address"=>$res['date']);
 $json = json_encode($tem);
}
 }


else {
 echo "0 results";
}

 echo $json;

?>
	
						
					
   
