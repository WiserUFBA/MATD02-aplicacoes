/**
 * Created by Erbetta on 23/09/2014.
 */

var flag_servo = 0;

function updateServo(){

    var val = $('input[name="group1"]:checked').val();
    flag_servo = val;
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/REST-Servo/devices/servo",
        data: {status: flag_servo},
        success: function (data) {
            console.log(data);
            }
        });   
    }

