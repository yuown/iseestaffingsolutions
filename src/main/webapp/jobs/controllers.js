iseeApp.controller('JobsController', [ '$scope', 'AjaxService', '$modal', 'AlertsService',
        function($scope, AjaxService, $modal, AlertsService) {
            'use strict';

            $scope.load = function() {
                AjaxService.call('jobs', 'GET').success(function(data, status, headers, config) {
                    $scope.items = data;
                });
            };

            $scope.add = function(item) {
                $scope.item = angular.copy(item) || {
                    title : "",
                    description : "",
                    id : null,
                };
                $scope.title = $scope.item.id == null ? "Add Job" : "Edit Job";
                $scope.addDialog = $modal.open({
                    templateUrl : 'jobs/add.html',
                    scope : $scope
                });
            };

            $scope.deleteRecord = function(item) {
                AlertsService.confirm('Are you sure to delete this?', function() {
                    AjaxService.call('jobs/' + item.id, 'DELETE').success(function(data, status, headers, config) {
                        $scope.load();
                    });
                });
            };

            $scope.apply = function(item) {
                AlertsService.confirm('Are you sure to apply for this Job ?', function() {
                    AjaxService.call('jobs/apply/' + item.id, 'POST').success(function(data, status, headers, config) {
                        $scope.load();
                    });
                });
            };
        } ]);

iseeApp.controller('AddJobController', [ '$scope', 'AjaxService', function($scope, AjaxService) {
    'use strict';

    $scope.save = function(item) {
        AjaxService.call('jobs', 'POST', item).success(function(data, status, headers, config) {
            $scope.addDialog.dismiss('cancel');
            $scope.load();
        });
    };
} ]);