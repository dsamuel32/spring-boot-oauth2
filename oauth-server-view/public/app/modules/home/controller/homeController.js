"use strict";
angular.module('app.home').controller('homeController',
['$scope','$state','$filter',
function($scope, $state, $filter){
    var self = this;
    self.numero = 10;
    console.log($filter('translate')('abc'));




}]);
