(function(){
    'use strict';
    function config ($stateProvider) {
      $stateProvider
        .state('home', {
          //  abstract: true,
            url: '/home',
            templateUrl: 'modules/home/view/home.html',
            controller: 'homeController',
            controllerAs: 'homeCtrl',
            resolve: {
                        home: ['$ocLazyLoad',
                            function($ocLazyLoad){
                                return $ocLazyLoad.load({name: 'app.home', files:['modules/home/controller/homeController.js']});
                            }],
                         trans:['home','$translate', '$translatePartialLoader',
                            function (home,$translate, $translatePartialLoader) {
                                $translatePartialLoader.addPart('modules/home/language');
                                return $translate.refresh();
                        }]
                    }
          });
    };

    angular.module('app.home.service',[]);

    angular.module('app.home',[]).config(['$stateProvider', config]);

})();
