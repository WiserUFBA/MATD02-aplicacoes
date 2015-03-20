/**
 * Created by Erbetta on 23/09/2014.
 */

var flag_buzzer = 0;

function updateBuzzer(){
    var buzzer = document.getElementById("img_buzzer");

    var val = $('input[name="group1"]:checked').val();
    flag_buzzer = val;
    
    if(flag_buzzer!=0){   
        $.ajax({
        type: "POST",
        url: "http://localhost:8080/REST-Buzzer/devices/buzzer",
        data: {status: flag_buzzer},
        success: function (data) {
            console.log(data);
            buzzer.src = "images/buzzer_on.png";
            }
        });   
    }
    else{
        
        $.ajax({
        type: "POST",
        url: "http://localhost:8080/REST-Buzzer/devices/buzzer",
        data: {status: flag_buzzer},
        success: function (data) {
            console.log(data);
            buzzer.src = "images/buzzer_off.png";
            }
        });
        
    }
}

