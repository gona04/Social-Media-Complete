/**
 * 
 */
app.controller('UserController', function(UserService, $scope, $rootScope,
		$location, $cookieStore) {
	$scope.user = {
		"userName" : "",
		"password" : "",
		"email" : "",
		"role" : "",
		"firstName" : "",
		"lastName" : "",
		"online" : true
	}
	
	//TO SET THE USERNAME DETAILS IF IT'S LOST FROMT THE ROOTSCOPE
	if ($rootScope.currentUser != undefined) {
		console.log('in $rootScope.currentUser not undefined');
		console.log($rootScope.currentUser);
		UserService.getUser().then(function(response) {
			$scope.user = response.data;
			console.log(response.data);
		}, function(response) {
			console.log('getusererror');
			console.log(response.data);
			$location.path('/home');
		})
	}

	//TO REGISTER A USER 
	$scope.registerUser = function() {
		if ($scope.p1 == $scope.p2) {
			$scope.user.password = $scope.p1;
		} else {
			$scope.pe = "Both passwords do not match!";
			$location.path('/register')
		}

		if ($scope.user.role == null)
			$scope.roleError = "Role cannot be empty";

		console.log($scope.user);
		UserService.registerUser($scope.user).then(function(response) {
			$scope.success = "Registered Successfully Login agian";
			$location.path('/home');
		},

		function(response) {
			$scope.error = response.data;
			if ($scope.error.code == 2) {
				$scope.email = response.data;
			}
			if ($scope.error.code == 3) {
				$scope.userName1 = response.data;
			}
			if ($scope.error.code == 1) {
				$scope.exception = response.data;
			}
			$location.path("/register")
		})
	}

	//LOGIN METHOD
	$scope.login = function() {
		UserService.validUser($scope.user).then(function(response) {
			console.log(response.data);
			$rootScope.currentUser = response.data;
			$cookieStore.put("currentUser", response.data);
			$location.path('/home')
			console.log("in controller");
			console.log($scope.user.userName);
			console.log($scope.user.password);
		}, function(response) {
			console.log("in controller");
			console.log($scope.user.userName);
			console.log($scope.user.password);
			$scope.error = response.data;
			$location.path('/login');
		})
	}

	//LOGOUT FUNCTION
	$rootScope.logout = function() {
		console.log("in logout controller");
		UserService.logout().then(function(response) {
			$rootScope.logoutSuccess = "logged out successfully";
			delete $rootScope.currentUser;
			$cookieStore.remove("currentUser");
			$location.path('/home');
		}, function(response) {
			$scope.error = response.data;
			$location.path('/login');
		})
	}

	//UPDATE USER METHOD
	$scope.userUpdate = function() {
		UserService.userUpdate($scope.user).then(function(response) {
			// aleart("Successfully updated");
			$location.path('/home');
			console.log("in success");
			console.log(response.data);
		}, function(response) {
			$scope.editError = response.data;
			console.log("in error function");
			console.log(response.data);
			if ($scope.editError.code == 401) {
				$location.path('/login');
			}
			$location.path('editProfile');
		})
	}
});