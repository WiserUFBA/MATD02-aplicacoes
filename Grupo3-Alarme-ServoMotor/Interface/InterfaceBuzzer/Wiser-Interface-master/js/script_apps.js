/**
 * Created by Erbetta on 23/09/2014.
 */

var flag_porta = 0;
var flag_lampada = 0;
var flag_presenca = 0;
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
    var p_presenca = document.getElementById("p_presenca");

    if (flag_presenca === 0) {
        flag_presenca = 1;
        presenca.src = "images/user_online.png";
        p_presenca.innerHTML = "Existe(m) pessoa(s) na sala.";
    }
    else{
        flag_presenca = 0;
        presenca.src = "images/user_offline.png";
        p_presenca.innerHTML = "A sala está vazia.";
    }
}


function updateArCondicionado(){
    $("#temperatura").toggle("fast");
}

function updateTemp(temp){
    g.refresh(temp);
}

