<?php 

$connect=mysql_connect("localhost","root","");
mysql_select_db("evisaprocessing",$connect);

$sql = mysql_query("SELECT * FROM  renewalvisa");

if ($sql) {
while($res=mysql_fetch_array($sql))
{




$tem[]=	array("pid"=>$res['name'],"name"=>$res['contact'],"name1"=>$res['country'],"image1"=>$res['vnumber'],"birth"=>$res['birth'],"idnumber"=>$res['idnumber']
,"image"=>$res['image']);
 $json = json_encode($tem);
}
}
else {
 echo "0 results";
}

 echo $json;


?>