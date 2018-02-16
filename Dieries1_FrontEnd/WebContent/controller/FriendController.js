/**
 * 
 */
app.controller('FriendController',function(FriendService,$location,$scope,$rootScope)
		{
			//GET ALL USERS
			//$scope.getAllUsers = function()
			function getAllUsers()
			{
				console.log("inside getAllUsers");
				FriendService.getAllUsers().then(function(response){
					$scope.users = response.data;
					console.log(response.data)
					//$location.path('/getAllUsers');
				},
				function(response)
				{
					console.log(response.status);
					if(response.status == 401)
						{
							$location.path('/login');
						}
				})
			}
				
				//GET ALL FRIENDS
				function listOfFriends()
				{
					console.log('In get Friends')
					FriendService.listOfFriends().then(function(response)
					{
						$scope.friends = response.data //List<Friend> select * from friend where status='A' and (fromId=? or toId=?)
						$rootScope.noOfFriends=$scope.friends.length
					},
					function(response){
						if(response.status==401)
							$location.path('/login')
					})
				}
				
				//LIST OF PENDING REQUESTS
				function pendingRequests()
				{
					console.log('in pending Requests');
					FriendService.pendingRequests().then(function(response){
						$rootScope.pendingRequests = response.data;
						console.log(response.data.length)
						//$location.path('/pendingRequests');
					},
					function(response){
						if (response.status == 401)
							{
							$location.path('/login')
							}
					})
				}
				
				//GET ALL USERS
				$scope.getUserByUsername = function(fromId)
				{
					console.log('in getUserBy userName');
					FriendService.getUserByUsername(fromId).then(function(response)
							{
						console.log('in getUserBy userName 2');
								console.log(fromId);
								$scope.showUserDetails = true;
								console.log($scope.showUserDetails);
								$scope.user = response.data;
								console.log(response.data);
							},
							function(response)
							{
								console.log(response.data);
							})
				}
				
				
				//UPDATING PENDING FRIEND REQUEST
				$scope.updatePendingRequest = function(request,value){
					console.log('pending request ' + request)
					request.status=value //value is 'A' for accept and 'D' for delete
					console.log('after assigning value to status  ' + request)
					FriendService.updatePendingRequest(request).then(function(response){
						pendingRequests();
						$location.path('/pendingrequests')
					},function(response){
						if(response.status==401)
							$location.path('/login')
					})
				}
			
			
			//TO SEND A FRIEND REQUEST
			$scope.sendFriendRequest = function(toId)
			{
				FriendService.sendFriendRequest(toId).then(function(response)
						{
							console.log('in sendFriendRequest');
							console.log(toId);
							$scope.getUserByUsername(toId);
							getAllUsers();
							
							$location.path('/getAllUsers');
						},
						function(response)
						{
							console.log(response.data);
						})
			}
			
			
			pendingRequests();
			listOfFriends();
			getAllUsers();
		})
