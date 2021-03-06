iseeApp.factory('AlertsService', [ '$rootScope', '$modal', function($rootScope, $modal) {
    'use strict';

    return {
        confirm : function(message, callback) {
            $rootScope.title = "ISEE";
            $rootScope.confirmMessage = message;
            $rootScope.confirmCallback = callback;
            $rootScope.confirmDialog = $modal.open({
                templateUrl : 'alerts/confirm.html',
                scope : $rootScope
            });
        }
    };

} ]);

iseeApp.controller('AlertsController', [ '$scope', '$modal', function($scope, $modal) {
    'use strict';
    
    $scope.submitOption = function(option, confirmCallback) {
        $scope.confirmDialog.dismiss('cancel')
        if(option == 'yes') {
            confirmCallback();
        }
    };
    
} ]);