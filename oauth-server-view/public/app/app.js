"use strict";
angular.module('app', ['oc.lazyLoad','ui.router', 'restangular', '$config','pascalprecht.translate', 'app.home'])
.config(['$ocLazyLoadProvider', '$stateProvider', '$urlRouterProvider', 'RestangularProvider', 'CONFIG_APP', '$translateProvider',
  function($ocLazyLoadProvider, $stateProvider, $urlRouterProvider, RestangularProvider, CONFIG_APP, $translateProvider) {
    $ocLazyLoadProvider.config({
        //debug: true,
        //events: true
    });


    /*$translateProvider.translations('pt-BR', {
    "home":"HOME"
  });*/

    $translateProvider.preferredLanguage('pt-BR');
    $translateProvider.useSanitizeValueStrategy('escapeParameters');


    $urlRouterProvider.otherwise('/home');

    $stateProvider.state('app', {
        url: '',
        abstract: true,
        templateUrl: 'template.html',
        controller: 'appController',
        controllerAs:'appCtrl',
        resolve: {
            root: ['$ocLazyLoad', function($ocLazyLoad){
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
          translate:['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                $translatePartialLoader.addPart('language');
                return $translate.refresh();
            }],
            loader: ['$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load('modules');
            }]
        }
      });

  }]);
//https://www.codetutorial.io/livereload-with-grunt/
