// Ionic Starter App
// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic'])

.controller('MainCtrl', function($scope,$http) {

    var get_temp = function() {
                $http({
                    url: 'http://localhost:8080/REST-Lamps/devices/laboratorio-wiser/gettemperatura/arcondicionado/1', // A URL que a requisição será enviada
                    method: 'GET', // Método
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
    }

    $scope.arcondicionado = {
        "state": false,
        "temp": 16
    }
    
    var flag_ar = 0;


    // Função toggle para ligar a luz
    $scope.turn = function(){
        
        if (flag_ar === 0) {
            flag_ar = 1;
            $http({
                url: 'http://localhost:8080/REST-Lamps/devices/laboratorio-wiser/on/arcondicionado/1', // A URL que a requisição será enviada
                method: 'GET', // Método
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function(data){
                get_temp()
                    .then(
                        function(payload){
                            
                        }
                    );
            }); 
            $scope.arcondicionado.state = true;
        } else {
            $http({
                url: 'http://localhost:8080/REST-Lamps/devices/laboratorio-wiser/off/arcondicionado/1', // A URL que a requisição será enviada
                method: 'GET', // Método
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function(data){
                // RETORNO DA REQUISIÇÃO
            }); 
            $scope.arcondicionado.state = false;
            flag_ar = 0;
        }
    }
    
    // Função para alterar a temperatura.     
    $scope.changeTemp = function(temp){
    	$http({
            url: 'http://localhost:8080/REST-Lamps/devices/laboratorio-wiser/mudartemperatura/arcondicionado/1/'+temp,
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function(data){
            // RETORNO DA REQUISIÇÃO
        });
    }
})