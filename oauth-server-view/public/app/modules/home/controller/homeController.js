"use strict";
angular.module('app.home').controller('homeController',
['$scope','$state','$filter',
function($scope, $state, $filter){
    var self = this;

    console.log($filter('translate')('abc'));




}]);
