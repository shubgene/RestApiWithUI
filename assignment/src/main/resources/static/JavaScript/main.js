/**
 * 
 */
function createproduct() {
    var xhttp = new XMLHttpRequest();
    var product_name=document.getElementsByClassName("createname")[0].value;
	var price=document.getElementsByClassName("createprice")[0].value
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 201) {
        	 document.getElementById("created").innerHTML = "The product has been created";
         }
    };
    xhttp.open("POST", "http://localhost:8080/api/products", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({ "name": product_name, "currentPrice": price }));
}

function getProductById(){
	var xhttp = new XMLHttpRequest();
	var id = document.getElementsByClassName("fetchById");
	if (id.length > 0) {
	    id=id[0].value;
	}
	var url="http://localhost:8080/api/products/"+id;
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
        	  prod=JSON.parse(this.responseText);
        		  document.getElementsByClassName("fetchedProductname")[0].value=prod.name;
        		  document.getElementsByClassName("fetchedprice")[0].value=prod.currentPrice;
        		  document.getElementsByClassName("updatePhase")[0].style.visibility='visible';
        		  document.getElementsByClassName("fetchById")[0].readOnly = true;
        		  document.getElementById("datafound").style.visibility='hidden';
         }else if(this.status == 404){
        	 document.getElementById("datafound").style.visibility='visible';
        	 document.getElementById("datafound").innerHTML = "The given id doesnt exist";
         }
    };
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("Your JSON Data Here");
}

function getAllProduct(){
	var xhttp = new XMLHttpRequest();
	var txt="";
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             myObj = JSON.parse(this.responseText);
             txt += "<table border='1' class='allproduct'>"
             txt += "<tr><th>Id</th><th>Product Name</th> <th>Current Price</th> <th>Last Update date</th></tr>";
             for (x in myObj) {
               txt += "<tr><td>" + myObj[x].id + "</td>";
               txt += "<td>" + myObj[x].name + "</td>";
               txt += "<td>" + myObj[x].currentPrice + "</td>";
               txt += "<td>" + myObj[x].lastUpdate + "</td></tr>";
             }
             txt += "</table>"
             document.getElementById("AllProduct").innerHTML = txt;
         }
    };
    xhttp.open("GET", "http://localhost:8080/api/products", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("Your JSON Data Here");
}

function update(){
	var xhttp = new XMLHttpRequest();
	var id = document.getElementsByClassName("fetchById");
	if (id.length > 0) {
	    id=id[0].value;
	}
	var product_name=document.getElementsByClassName("fetchedProductname")[0].value;
	var price=document.getElementsByClassName("fetchedprice")[0].value;
	var url="http://localhost:8080/api/products/"+id;
	var data = {};
	data.name =product_name;
	data.currentPrice  = price;
	data.id=id;
	var json = JSON.stringify(data);
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
        	 document.getElementById("datafound").style.visibility='visible';
        	 document.getElementById("datafound").innerHTML = "The given product is updated";
         }
    };
    xhttp.open("PUT", url, true);
    xhttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    xhttp.send(json);
}