/**
 * 
 */
app.controller('HomeController',function(BlogPostService,FriendService,UserService,$scope,$rootScope,$location){
	function getApprovalStatus()
	{
		console.log('in home controller outside method get approval status')
		BlogPostService.getApprovalStatus()
		.then(function(response)
				{
					console.log('in home controller');
					console.log(response.data);
					$rootScope.approvalStatus = response.data;
					console.log($rootScope.approvalStatus.length);
				},
				function(reponse)
				{
					console.log(response.status);
				})
	}
	getApprovalStatus();
})