'use strict';

angular.module('$config', [
	'oc.lazyLoad',
	'ui.router'
])

.config(['$httpProvider', '$rootScopeProvider', function($httpProvider, $rootScopeProvider){

	if (!$httpProvider.defaults.headers.get) {
		$httpProvider.defaults.headers.get = {};
	}
	$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

//	$httpProvider.interceptors.push('JwtHttpInterceptor');

	$rootScopeProvider.digestTtl(60);

}])
.constant('CONFIG_APP', {
	contexto:'/oauth-server',
	apiEndpoint:'/oauth-server/api/'
});
