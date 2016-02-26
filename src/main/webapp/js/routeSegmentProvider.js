iseeApp.config(function($routeSegmentProvider, $routeProvider) {

    $routeSegmentProvider.options.autoLoadTemplates = true;

    // Setting routes. This consists of two parts:
    // 1. `when` is similar to vanilla $route `when` but takes segment name instead of params hash
    // 2. traversing through segment tree to set it up

    $routeSegmentProvider.
        when('/login', 'login').
        when('/home', 'home').
        when('/home/settings', 'home.settings').
        when('/home/users', 'home.users').
        when('/home/changeProfile', 'home.changeProfile').
        when('/home/groups', 'home.groups').
        segment('login', {
            templateUrl : 'templates/login.html',
        }).
        segment('home', {
            templateUrl : 'templates/home.html',
        }).
        within().
            segment('settings', {
                templateUrl : 'settings/list.html'
            }).
            segment('users', {
                templateUrl : 'users/list.html'
            }).
            segment('changeProfile', {
                templateUrl : 'users/changeProfile.html'
            }).
            segment('groups', {
                templateUrl : 'users/groupsList.html'
            });

    $routeProvider.otherwise({redirectTo: '/login'}); 
});