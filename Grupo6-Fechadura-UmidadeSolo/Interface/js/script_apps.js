var flag_porta = 0;
var umidade = '';

var settings = {
    X: 0,
    Y: 0,
    SegmentWidth: 0.12,
    BevelWidth: 0.49,
    SegmentInterval: 0.025,
    SideBevelEnabled: false,
    Padding: 15,
    Spacing: 10,
    FillLight: '#9eff0d',
    FillDark: '#0c1401',
    StrokeLight: '#ff0000',
    StrokeDark: '#550000',
    StrokeWidth: 0
};
            
$(function() {
    var charMasks = SixteenSegment.prototype.CharacterMasks;
    var a1 = 1 << 0, a2 = 1 << 1, b = 1 << 2, c = 1 << 3,
            d1 = 1 << 4, d2 = 1 << 5, e = 1 << 6, f = 1 << 7,
            g1 = 1 << 8, g2 = 1 << 9, h = 1 << 10, i = 1 << 11,
            j = 1 << 12, k = 1 << 13, l = 1 << 14, m = 1 << 15;
    charMasks['%'] = a1 | i | g1 | f | m | j | g2 | c | d2 | l;
    charMasks['º'] = a2 | b | g2 | i;

    "use strict";
    // Set the canvas size to fill its container
    var canvas = document.getElementById('canvas');
    var container = document.getElementById('canvas-container');
    canvas.width = container.clientWidth;
    canvas.height = container.clientHeight;

    // Create a new sixteen segment display
    segment = new SixteenSegment(1, canvas);

    // When the inputs change update the display
    $("#textInput").keyup(function () {
        update(segment, $("#textInput").val().toUpperCase());
    });
    $("#textInput").change(function () {
        update(segment, $("#textInput").val().toUpperCase());
    });

    // Initialize the segment settings and input controls
    settings.Width = canvas.width;
    settings.Height = canvas.height;
    for (var s in settings) {
        segment[s] = settings[s];
    }

    update(segment, umidade);
});

// Update the Segment Display settings
function update(segment, text) {
    "use strict";
    // Clear the canvas in case the display is resized
    var canvas = document.getElementById('canvas');
    canvas.width = canvas.width;

    // Set the display properties and update the display
    segment.SetCount(6);
    segment.DispayText(text);
}

function updatePorta(){
    var porta = document.getElementById("img_porta");
    var botao_porta = $(".push_button");
    var loading_icon = $(".push_button").find("i");
    var botao_text = $(".push_button").find("span");

    loading_icon.removeClass("display-none");
    loading_icon.addClass("fa-spin");
    botao_porta.attr("disabled", true);
    
    if (flag_porta === 0) {
        flag_porta = 1;
       
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/REST-Lamps/devices/fechadura/1",
            data: {status: flag_porta},
            success: function (data) {
                console.log(data);
                porta.src = "images/porta_fechada.png";
                botao_porta.removeClass("blue");
                botao_porta.addClass("red");
                botao_text.text(" ABRIR");
            },
            complete: function(){                
                loading_icon.addClass("display-none");
                loading_icon.removeClass("fa-spin");
                botao_porta.attr("disabled", false);
            }
        }); 
    }
    else{
        flag_porta = 0;
        
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/REST-Lamps/devices/fechadura/1",
            data: {status: flag_porta},
            success: function (data) {
                console.log(data);
                porta.src = "images/porta_aberta.png";
                botao_porta.removeClass("red");
                botao_porta.addClass("blue");
                botao_text.text(" FECHAR");
            },
            complete: function(){                
                loading_icon.addClass("display-none");
                loading_icon.removeClass("fa-spin");
                botao_porta.attr("disabled", false);
            }
        });
    }
}

function readHumiditySensor() {
    $('.btn-umidade').addClass('disabled in-progress');
    $('.btn-umidade span').text('Aguarde...');
    update(segment, "");
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/REST-Lamps/devices/sensor-umidade/1",
        async: false,
        success: function (data) {
            if(data.umidade === "s"){
                update(segment, "SECO");
            }else if(data.umidade === "u"){
                update(segment, "UMIDO");
            }else if(data.umidade === "a"){
                update(segment, "AGUA");
            }
        },
        complete: function () {
            $('.btn-umidade').removeClass('disabled in-progress');
            $('.btn-umidade span').text('Detectar');
        }
    });
}