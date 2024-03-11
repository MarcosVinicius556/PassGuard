const app = angular.module('app', ['ngRoute'])
            .config(($routeProvider) => {
                $routeProvider
                .when('/', {
                    templateUrl: 'views/user-list.html',
                    controller: 'UserController'
                }).when('/users/list', {
                    templateUrl: 'views/user-list.html',
                    controller: 'UserController'
                }).when('/users/create', {
                    templateUrl: 'views/user-create.html',
                    controller: 'UserController'
                }).when('/users/details', {
                    templateUrl: 'views/user-details.html',
                    controller: 'UserController'
                }).when('/users/update', {
                    templateUrl: 'views/user-update.html',
                    controller: 'UserController'
                }).otherwise({
                    redirectTo: '/'
                });
            });