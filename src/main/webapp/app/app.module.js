const app = angular.module('app', ['ui.router']);
            
/**
 * Configuração de rotas
 */
app.config(($stateProvider, $urlRouterProvider) => {
    $urlRouterProvider.otherwise('/'); //Rota default

    $stateProvider
        .state('home', 
            { 
                url: '/', 
                template: '<h1>Pagina de Home</h1>' }
        ).state('users', 
            { 
                url: '/users', 
                templateUrl: 'views/users/user-list.html',
                controller: 'UserController'
            }
        ).state('users.create',
            { 
                url: '/create', 
                templateUrl: 'views/users/user-create.html',
                controller: 'UserController'
            }
        ).state('users.edit',
            { 
                url: '/edit/:userId', 
                templateUrl: 'views/users/user-update.html',
                controller: 'UserController'
            }
        ).state('users.details',
            { 
                url: '/details/:userId', 
                templateUrl: 'views/users/user-details.html',
                controller: 'UserController'
            }
        );

});