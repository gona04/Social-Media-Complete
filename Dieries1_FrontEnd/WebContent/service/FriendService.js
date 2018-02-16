/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService = {};
	var BASE_URL = "http://localhost:3030/Diaries1/";
	
	friendService.getAllUsers = function()
	{
		return $http.get(BASE_URL + "/getAllUsers");
	}
	
	friendService.sendFriendRequest = function(toId)
	{
		return $http.post(BASE_URL + "/sendFriendRequest/" + toId)
	}
	friendService.pendingRequests = function()
	{
		return $http.get(BASE_URL + "/pendingRequests")
	}
	friendService.getUserByUsername = function(fromId)
	{
		return $http.get(BASE_URL + "/getUserByUsername/"+fromId);
	}
	friendService.updatePendingRequest = function(request)
	{
		return $http.put(BASE_URL+"/updateFriendRequest", request);
	}
	friendService.listOfFriends = function()
	{
		return $http.get(BASE_URL + "/listOfFriends");
	}
	return friendService;
})
