/**
 * Created by Erbetta on 23/09/2014.
 */
$(document).ready(function(){

function updatePresenca(){

    var presenca = document.getElementById("img_presenca");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/REST-Lamps/devices/sensor/1",
        success: function (data) {
            console.log(data);
            if(data === 0) { // não tem presença
                presenca.src = "images/user_offline.png";
            } else{ // tem presença
                presenca.src = "images/user_online.png";
            }
        }
    });
}

function updateChuva(){

    var chuva = document.getElementById("img_chuva");
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/REST-Lamps/devices/sensor/2",
        success: function (data) {
            console.log(data);
            if(data === 1) { // não tem chuva
                chuva.src = "images/sol.png";
            } else{ // tem chuva
                chuva.src = "images/chuva.png";
            }
        }
    });
  
}

});
