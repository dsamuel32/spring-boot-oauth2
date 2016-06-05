'use strict';

angular.module('$config', [
	'oc.lazyLoad',
	'ui.router',
	'pascalprecht.translate'
])

.config(['$translateProvider', '$translatePartialLoaderProvider','$httpProvider', '$rootScopeProvider',
				function($translateProvider, $translatePartialLoaderProvider, $httpProvider, $rootScopeProvider) {

					$translatePartialLoaderProvider.addPart('language');

					$translateProvider.useLoader('$translatePartialLoader', {
						urlTemplate: '{part}/{lang}.json'
					});

					if (!$httpProvider.defaults.headers.get) {
						$httpProvider.defaults.headers.get = {};
					}
					$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
					$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
					$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

				//	$httpProvider.interceptors.push('JwtHttpInterceptor');

					$rootScopeProvider.digestTtl(60);

					$translateProvider.preferredLanguage('pt-BR');
					$translateProvider.useSanitizeValueStrategy('escapeParameters');

}])
.constant('CONFIG_APP', {
	contexto:'/oauth-server',
	apiEndpoint:'/oauth-server/api/'
});

//http://plnkr.co/edit/MankhnyAG31U665481YX?p=preview
