/**
 * Created by alex on 30.12.14.
 */
var angApp = angular.module('angApp', ['ui.bootstrap']);

angApp.controller('angCtrl', function($scope){
    $scope.mockData =
        [
            {name:'Брынза из коровьего молока', belki:17.9,ziry:20.1, uglevody:0,kalorii:260,group:'Молоко и молочное'},
            {name:'Йогурт натуральный 1.5% жирности', belki:5,ziry:1.5, uglevody:3.5,kalorii:51,group:'Молоко и молочное'},
            {name:'Кефир нежирный', belki:3,ziry:0.1, uglevody:3.8,kalorii:30,group:'Молоко и молочное'},
            {name:'Кефир жирный', belki:2.8,ziry:3.2, uglevody:4.1,kalorii:59,group:'Молоко и молочное'},
            {name:'Молоко', belki:2.8,ziry:3.2, uglevody:4.7,kalorii:58,group:'Молоко и молочное'},
            {name:'Молоко ацидофильное', belki:2.8,ziry:3.2, uglevody:10.8,kalorii:83,group:'Молоко и молочное'},
            {name:'Молоко сухое цельное', belki:25.6,ziry:25, uglevody:39.4,kalorii:475,group:'Молоко и молочное'},
            {name:'Молоко сгущеное', belki:7,ziry:7.9, uglevody:9.5,kalorii:135,group:'Молоко и молочное'},
            {name:'Молоко сгущеное с сахаром', belki:7.2,ziry:8.5, uglevody:56,kalorii:315,group:'Молоко и молочное'},
            {name:'Простокваша', belki:2.8,ziry:3.2, uglevody:4.1,kalorii:58,group:'Молоко и молочное'},
            {name:'Ряженка', belki:3,ziry:6, uglevody:4.1,kalorii:85,group:'Молоко и молочное'},
            {name:'Сливки 10%', belki:3,ziry:10, uglevody:4,kalorii:118,group:'Молоко и молочное'},
            {name:'Сливки 20%', belki:2.8,ziry:20, uglevody:3.6,kalorii:205,group:'Молоко и молочное'},
            {name:'Сметана 10%', belki:3,ziry:10, uglevody:2.9,kalorii:116,group:'Молоко и молочное'},
            {name:'Сметана 20%', belki:2.8,ziry:20, uglevody:3.2,kalorii:206,group:'Молоко и молочное'},
            {name:'Сырки и масса творожные особые', belki:7.1,ziry:23, uglevody:27.5,kalorii:340,group:'Молоко и молочное'},
            {name:'Сыр российский', belki:23.4,ziry:30, uglevody:0,kalorii:371,group:'Молоко и молочное'},
            {name:'Сыр голландский', belki:26.8,ziry:27.3, uglevody:0,kalorii:361,group:'Молоко и молочное'},
            {name:'Сыр швейцарский', belki:24.9,ziry:31.8, uglevody:0,kalorii:396,group:'Молоко и молочное'},
            {name:'Сыр пошехонский', belki:26,ziry:26.5, uglevody:0,kalorii:334,group:'Молоко и молочное'},
            {name:'Сыр плавленный', belki:24,ziry:13.5, uglevody:0,kalorii:226,group:'Молоко и молочное'},
            {name:'Творог жирный', belki:14,ziry:18, uglevody:1.3,kalorii:226,group:'Молоко и молочное'},
            {name:'Творог полужирный', belki:16.7,ziry:9, uglevody:1.3,kalorii:156,group:'Молоко и молочное'},
            {name:'Творог нежирный', belki:18,ziry:0.6, uglevody:1.5,kalorii:86,group:'Молоко и молочное'}

];

    function sortBy(key, reverse) {
        return function(a, b) {
            if (a[key] < b[key]) {
                return reverse ? 1 : -1;
            }
            if (a[key] > b[key]) {
                return reverse ? -1 : 1;;
            }
            return 0;
        };
    }

    $scope.sort = function(key, reverse){
        $scope.mockData = $scope.mockData.sort(sortBy(key, reverse));
        $scope.pageChanged();
    }

    $scope.currentPage = 1;
    $scope.step = 10;
    $scope.totalItems = $scope.mockData.length;

    $scope.pagedData = $scope.mockData.slice(0, 10);

    $scope.pageChanged = function() {
        $scope.start = ($scope.currentPage * $scope.step) - $scope.step;
        $scope.end = $scope.start + $scope.step;
        console.log('Page changed to: ' + $scope.currentPage);
        console.log('Start from: ' + $scope.start);
        console.log('Go to: ' + $scope.end);
        $scope.pagedData = $scope.mockData.slice($scope.start, $scope.end);
    };

});
