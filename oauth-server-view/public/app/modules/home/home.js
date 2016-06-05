"use strict";
angular.module('app.home.service',[]);
angular.module('app.home',[]).config(['$stateProvider', function($stateProvider) {
    $stateProvider
      .state('home', {
        //  abstract: true,
          url: '/home',
          templateUrl: 'modules/home/view/home.html',
          controller: 'homeController',
          controllerAs: 'homeCtrl',
          resolve: {
              home: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load({
                      name: 'app.home',
                      files: [
                          'modules/home/controller/homeController.js'
                      ],
                      translate:['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                          $translatePartialLoader.addPart('modules/home/language');
                          return $translate.refresh();
                      }]
                  });
              }],
          }
        });
}]);
