/**
 * Created by Erbetta on 23/09/2014.
 */

var flag_lampada = 0;
var flag_lampada_2 = 0;

function onSensorLuz(){
    var lampada = document.getElementById("img_lampada");
    var lampadas = [
                    {img: "images/lampada_apagada.png", op: 100},
                    {img: "images/lampada_acesa.png", op: 100}
                    ];
    setInterval(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/org.gujavasc_REST-GAS-LUZ_war_1.0/devices/sensor/5",
            success: function (data) {
                console.log(data.state);        
                var x = 0;
                if(data.state < 50){
                    x = 0;
                }else{
                    x = 1;
                }
                lampada.src = lampadas[x].img;
            }
        });
    }, 700);
}
