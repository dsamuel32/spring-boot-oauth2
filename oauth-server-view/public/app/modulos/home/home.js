"use strict";
angular.module('app.home.service',[]);
angular.module('app.home',[]).config(['$stateProvider', function($stateProvider) {
    $stateProvider
      .state('home', {
        //  abstract: true,
          url: '/home',
          templateUrl: 'modulos/home/view/home.html',
          controller: 'homeController',
          controllerAs: 'homeCtrl',
          resolve: {
              home: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load({
                      name: 'app.home',
                      files: [
                          'modulos/home/controller/homeController.js'
                      ]
                  });
              }],
          }
        });
}]);
