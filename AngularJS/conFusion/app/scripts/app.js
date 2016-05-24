'use strict';      
angular.module('confusionApp', ['ngRoute'])
	.config(function($routeProvider) {
        $routeProvider
            // route for the contactus page
            .when('/contactus', {
                templateUrl : 'template-contactus.html',
                controller  : 'ContactController'
            })
            // route for the menu page
            .when('/menu', {
                templateUrl : 'template-menu.html',
                controller  : 'MenuController'
            })
            // route for the dish details page
            .when('/menu/:id', {
                templateUrl : 'tabbed-menu-dishDetail.html',
                controller  : 'DishDetailController'
            })
            .otherwise('/contactus');
    })

;
