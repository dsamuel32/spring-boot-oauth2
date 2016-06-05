(function(){
    'use strict';

    function homeController ($scope, $state, $filter) {
      var self = this;
      self.numero = 10;
      console.log($filter('translate')('home'));
    };

    angular.module('app.home').controller('homeController', ['$scope','$state','$filter', homeController]);

})();
