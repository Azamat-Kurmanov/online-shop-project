angular.module('app', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function() {
        const url = 'http://localhost:8189/winter/api/v1/products';
        $http.get(url).then(function (response) {
            $scope.productList = response.data;
        });
    }

    $scope.showProductInfo = function (productId){
        const url = 'http://localhost:8189/winter/api/v1/products/'+ productId;
        $http.get(url).then(function (response) {
            alert(JSON.stringify(response.data));
        })
    }

    $scope.deleteProductById = function (productId){
        const url = 'http://localhost:8189/winter/api/v1/products/'+ productId;
        $http.delete(url).then(function (response) {
            if (response.status==200){
                $scope.loadProducts();
            }
        });
    }

    $scope.addToCart = function (productId) {
        const url = 'http://localhost:8189/winter/api/v1/cart/add/'+ productId;
        $http.get(url).then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.loadCart = function () {
        const url = 'http://localhost:8189/winter/api/v1/cart';
        $http.get(url).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.loadProducts();
    $scope.loadCart();

});