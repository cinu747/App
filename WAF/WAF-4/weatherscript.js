function loadDoc() {
    var xhttp = new XMLHttpRequest();
    var location =  document.getElementById("city").value;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            $('#NextDays tr').remove();
            $('td').innerHTML = "";
            document.getElementById("errormsg").innerHTML = "";
            document.getElementsByTagName("td").innerHTML = "";
            document.getElementById("DayHourValue").innerHTML = "";
            document.getElementById("tempValue").innerHTML = "";
            document.getElementById("precipvalue").innerHTML = "";
            document.getElementById("humidtyvalue").innerHTML = "";
            document.getElementById("windvalue").innerHTML = "";
            document.getElementById("commentValue").innerHTML = "";
            document.getElementById("icon").src = "";
            const obj = JSON.parse(this.responseText);
            if(obj.status === 'fail'){
                document.getElementById("errormsg").innerHTML = "The location you have entered is not correct. Please try again with correct location name."
            }
            else if(this.status == 400){
                $('#NextDays tr').remove();
                $('td').innerHTML = "";
                document.getElementById("errormsg").innerHTML = "";
                document.getElementsByTagName("td").innerHTML = "";
                document.getElementById("DayHourValue").innerHTML = "";
                document.getElementById("tempValue").innerHTML = "";
                document.getElementById("precipvalue").innerHTML = "";
                document.getElementById("humidtyvalue").innerHTML = "";
                document.getElementById("windvalue").innerHTML = "";
                document.getElementById("commentValue").innerHTML = "";
                document.getElementById("icon").src = "";
                const obj = JSON.parse(this.responseText);
                document.getElementById("errormsg").innerHTML = obj.message;
            }
            else{
            document.getElementById("DayHourValue").innerHTML = obj.currentConditions.dayhour;
            document.getElementById("tempValue").innerHTML = obj.currentConditions.temp.c + " degree Celcius";
            document.getElementById("precipvalue").innerHTML = obj.currentConditions.precip;
            document.getElementById("humidtyvalue").innerHTML = obj.currentConditions.humidity;
            document.getElementById("windvalue").innerHTML = obj.currentConditions.wind.mile + " miles";
            document.getElementById("commentValue").innerHTML = obj.currentConditions.comment;
            document.getElementById("icon").src = obj.currentConditions.iconURL;
            $("#NextDays").innerHTML = "";
            for(i=1;i<8;i++){
                $("#NextDays").append("<tr><td>Day:"+obj.next_days[i].day+"</td><td>Comment:"+obj.next_days[i].comment+"</td><td>Max Temp:"+obj.next_days[i].max_temp.c+" degree celcius</td><td>Min Temp:"+obj.next_days[i].min_temp.c+' degree celcius</td><td><img src="'+obj.next_days[i].iconURL+'"></img></td></tr>')
            }
        }
            
       }
        
   }
    try{
    xhttp.open("GET", "https://weatherdbi.herokuapp.com/data/weather/"+location, true);
    xhttp.send();
    }catch(Exception){
        document.getElementById("errormsg").innerHTML = this.message;
    }

};


function loadgeo() {
    if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(getpos);

    } else {
        document.getElementById("errormsg").innerHTML = "Geolocation is not supported by this browser.";
    }
}
function getpos(position){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            $('#NextDays tr').remove();
            $('td').innerHTML = "";
            document.getElementById("errormsg").innerHTML = "";
            document.getElementsByTagName("td").innerHTML = "";
            document.getElementById("DayHourValue").innerHTML = "";
            document.getElementById("tempValue").innerHTML = "";
            document.getElementById("precipvalue").innerHTML = "";
            document.getElementById("humidtyvalue").innerHTML = "";
            document.getElementById("windvalue").innerHTML = "";
            document.getElementById("commentValue").innerHTML = "";
            document.getElementById("icon").src = "";
            const obj = JSON.parse(this.responseText);
            if(obj.status === 'fail'){
                document.getElementById("errormsg").innerHTML = "The location you have entered is not correct. Please try again with correct location name."
            }

            else{
            document.getElementById("DayHourValue").innerHTML = obj.currentConditions.dayhour;
            document.getElementById("tempValue").innerHTML = obj.currentConditions.temp.c + " degree Celcius";
            document.getElementById("precipvalue").innerHTML = obj.currentConditions.precip;
            document.getElementById("humidtyvalue").innerHTML = obj.currentConditions.humidity;
            document.getElementById("windvalue").innerHTML = obj.currentConditions.wind.mile + " miles";
            document.getElementById("commentValue").innerHTML = obj.currentConditions.comment;
            document.getElementById("icon").src = obj.currentConditions.iconURL;
            $("#NextDays").innerHTML = "";
            for(i=1;i<8;i++){
                $("#NextDays").append("<tr><td>Day:"+obj.next_days[i].day+"</td><td>Comment:"+obj.next_days[i].comment+"</td><td>Max Temp:"+obj.next_days[i].max_temp.c+" degree celcius</td><td>Min Temp:"+obj.next_days[i].min_temp.c+' degree celcius</td><td><img src="'+obj.next_days[i].iconURL+'"></img></td></tr>')
            }
        }
            
       }
        
   }
    
    xhttp.open("GET", "https://weatherdbi.herokuapp.com/data/weather/"+position.coords.latitude+","+position.coords.longitude, true);
    xhttp.send();

  }
