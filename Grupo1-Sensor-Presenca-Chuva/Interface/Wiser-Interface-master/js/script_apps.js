/**
 * Created by Erbetta on 23/09/2014.
 */
$(document).ready(function(){
    

var flag_porta = 0;
var flag_lampada = 0;
var flag_presenca = 0;
var flag_chuva = 0;
var intervalo = setInterval(updatePresenca, 5000);
var g;

var x = -1;
var y = -1;

window.addEventListener("load", startApps, true);

function startApps () {
    updatePresenca();
        g = new JustGage({
        id: "gauge",
        value: 16,
        min: 16,
        max: 27,
        title: "Temperatura"
    });
    updateArCondicionado();

}

function updatePorta(){
    var porta = document.getElementById("img_porta");

    if (flag_porta === 0) {
        flag_porta = 1;
        porta.src = "images/porta_aberta.png";
    }
    else{
        flag_porta = 0;
        porta.src = "images/porta_fechada.png";
    }
}


function updateLampada(){
    var lampada = document.getElementById("img_lampada");

    if (flag_lampada === 0) {
        flag_lampada = 1;
        lampada.src = "images/lampada_acesa.png";
    }
    else{
        flag_lampada = 0;
        lampada.src = "images/lampada_apagada.png";
    }
}


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
//    var chuva = document.getElementById("img_chuva");
//    var p_chuva = document.getElementById("p_chuva");
//
//    if (flag_chuva === 0) {
//        flag_chuva = 1;
//        chuva.src = "images/chuva.png";
//        p_chuva.innerHTML = "Chuvoso.";
//    }
//    else{
//        flag_chuva = 0;
//        chuva.src = "images/sol.png";
//        p_chuva.innerHTML = "Ensolarado.";
//    }
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/REST-Lamps/devices/sensor/2",
        success: function (data) {
            console.log(data);
            if(data === 1) { // não tem chuva
                presenca.src = "images/sol.png";
            } else{ // tem chuva
                presenca.src = "images/chuva.png";
            }
        }
    });
    
}


function updateArCondicionado(){
    $("#temperatura").toggle("fast");
}

function updateTemp(temp){
    g.refresh(temp);
}

});
