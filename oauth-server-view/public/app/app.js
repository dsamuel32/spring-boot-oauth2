"use strict";
angular.module('app', []).config(['$ocLazyLoadProvider', '$stateProvider', '$urlRouterProvider', '$animateProvider', 'RestangularProvider', 'CONFIG_APP',
  function($ocLazyLoadProvider, $stateProvider, $urlRouterProvider, $animateProvider, RestangularProvider, CONFIG_APP) {
    $ocLazyLoadProvider.config({
        //debug: true,
        //events: true
    });

    $urlRouterProvider.otherwise('/public/login');

    $stateProvider.state('app', {
        url: '',
        abstract: true,
        templateUrl: 'template.html',
        controller: 'AppController',
        controllerAs:'appCtrl',
        resolve: {
            root: [ '$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load({
                    name: 'app',
                    files: []// controller and directives
                });
            }],
          /*  alerts: [ '$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load({
                    name: '$alerts',
                    files: [
                        'modules/alerts/controller/alerts-controller.js',
                        'modules/alerts/directive/alerts-directive.js',
                        'modules/alerts/service/alerts-manager.js'
                    ]
                });
            }],
            dialog: [ '$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load({
                    name: 'sisouvApp.dialog',
                    files: [
                        'modules/dialog/controller/dialog-controller.js',
                        'modules/dialog/service/dialog-manager-service.js'
                    ]
                });
            }],*/
            services: [ '$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load({
                    name: 'services',
                    files: []//SERVICES
                });
            }],
          /*  tranlate:['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                $translatePartialLoader.addPart('services/mensagem/language');
                return $translate.refresh();
            }],*/
            loader: ['$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load('da-loader');
            }]
        }
      });

  }]);
//https://www.codetutorial.io/livereload-with-grunt/
