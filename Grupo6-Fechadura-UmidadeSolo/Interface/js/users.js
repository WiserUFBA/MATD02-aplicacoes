$(document).ready(function(){
  	$(".card").hover(
  		function(){
			$(this).find("img.fade").css("opacity", "0");
			$(this).find("h3").css("color", "#E27689");
		},
		function(){
			$(this).find("h3").css("color", "inherit");
			$(this).find("img.fade").css("opacity", "1");
		}
	); 
});