'use strict';

angular.module('$config', [
	'oc.lazyLoad',
	'ui.router',
	'pascalprecht.translate',
	'ngStorage',
	'da-jwtauth',
	'da-loader'
])

.config(['$translateProvider', '$translatePartialLoaderProvider', '$httpProvider', '$rootScopeProvider', function($translateProvider, $translatePartialLoaderProvider, $httpProvider, $rootScopeProvider){
	$translateProvider.useLoader('$translatePartialLoader', {
		urlTemplate: '{part}/{lang}.json'
	});
	//inicio disable IE ajax request caching
	if (!$httpProvider.defaults.headers.get) {
		$httpProvider.defaults.headers.get = {};
	}
	$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
	//fim disable IE ajax request caching

	$httpProvider.interceptors.push('daLoaderHttpInterceptor');
	$httpProvider.interceptors.push('JwtHttpInterceptor');

	$rootScopeProvider.digestTtl(60);

	$translateProvider.preferredLanguage('pt-BR');
	$translateProvider.useSanitizeValueStrategy('escapeParameters');
}])

.run(['JwtService','$cookies', function(JwtService, $cookies){
	JwtService.encodePassword = function(user,password){
		return {"Authorization": "Basic " + btoa(user + ":" + password)};
	};
	var $storage = {};
	Object.defineProperty($storage, 'token', {
		get: function() {
			var cookieOptions = getCookieOptions();
			if (!_.isEmpty(cookieOptions)) {
				$cookies.put('token', $cookies.get('token'), cookieOptions);
				$cookies.put('usuarioLogado',$cookies.get('usuarioLogado'),cookieOptions);
			}
			return $cookies.get('token');
		},
		set: function(token){
			$cookies.put('token', token);
		}
	});

	function getCookieOptions(){
		var usuarioLogado = $cookies.getObject('usuarioLogado');
		if( usuarioLogado && usuarioLogado.tipo && usuarioLogado.tipo === 'USUARIO_EXTERNO'){
			return {'expires':new Date(new Date().getTime() + 10 * (1 * 1 * 1 * 60 * 1000))};
		}
		return {};
	}

	JwtService.setStorage($storage);
}])


.constant('CONFIG_APP', {
	contexto:'/sisouv',
	apiEndpoint:'/sisouv-service/rest/'
});
