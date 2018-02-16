/**
 * 
 */
app.factory('UserService',function($http){
	var userService = {};
	var BASE_URL = "http://localhost:3030/Diaries1/";
	
	//FOR SIGNUP AND REGISTRATION
	userService.registerUser = function(user)
	{
		return $http.post(BASE_URL + "/register",user);
	}
	
	//TO GET VALID USERS
	userService.validUser = function(user)
	{
		console.log("in service");
		console.log(user.userName);
		console.log(user.password);
		return $http.post(BASE_URL + "/login",user);
	}
	
	//TO LOGOUT
	userService.logout = function()
	{
		return $http.get(BASE_URL + "/logout");
	}
	
	//TO GET ALL USERS
	userService.getUser = function()
	{
		return $http.get(BASE_URL + "/getUser")
	}
	
	//TO UPDATE USERS
	userService.userUpdate = function(user)
	{
		console.log(user);
		return $http.put(BASE_URL + "/userUpdate",user);
	}
	return userService;
});