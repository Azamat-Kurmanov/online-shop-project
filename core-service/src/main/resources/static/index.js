angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.tryToAuth = function(){
        const url = 'http://localhost:8189/winter/auth'
        $http.post(url, $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    }

    $scope.tryToLogout = function(){
        $scope.clearUser();
        $scope.user = null;
    }

    $scope.clearUser = function(){
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    }

    $scope.isUserLoggedIn = function(){
        if ($localStorage.winterMarketUser){
            return true;
        } else {
            return false;
        }
    }

    $scope.checkAuth = function(){
        const url = 'http://localhost:8189/winter/auth_check';
        $http.get(url).then(function (response) {
            console.log('AuthCheck: ' + response.data.value);
        });
    }

    $scope.initLoader = function(){
        if ($localStorage.winterMarketUser){
            try {
                let jwt = $localStorage.winterMarketUser.token;
                let payload = JSON.parse(atab(jwt.split('.')[1]));
                console.log('payload: ' + JSON.stringify(payload));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log('Token is expired!!!');
                    delete $localStorage.winterMarketUser
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {

            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
        }
    }

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

    $scope.removeFromCart = function (productId){
        const url = 'http://localhost:8190/winter-carts/api/v1/cart/remove/'+ productId;
        $http.get(url).then(function (response) {
            if (response.status==200){
                $scope.loadCart();
            }
        });
    }

    $scope.clearCart = function (){
        const url = 'http://localhost:8190/winter-carts/api/v1/cart/clear';
        $http.get(url).then(function (response) {
            if (response.status==200){
                $scope.loadCart();
            }
        });
    }

    $scope.addToCart = function (productId) {
        const url = 'http://localhost:8190/winter-carts/api/v1/cart/add/'+ productId;
        $http.get(url).then(function (response) {
            $scope.loadCart();
        })
    }

    $scope.loadCart = function () {
        const url = 'http://localhost:8190/winter-carts/api/v1/cart';
        $http.get(url).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.initLoader();
    $scope.loadProducts();
    $scope.loadCart();

});