/**
 * 
 */
app.controller('BlogPostController',function(BlogPostService,$scope,$location)
{
	$scope.addBlogPost = function()
	{
		BlogPostService.saveBlogPost($scope.blogPost).then(function(response){
			console.log(response.status);
			alert('Blog post added successfully.... it is waiting for approval');
			$location.path('/getAllblogs')
		},
		function(response)
		{
			if(response.status == 401)
				$location.path('/login');
			$location.path('/saveBlogPost');
		})
	}
	
	function watingForApprovalBlogs()
	{
		BlogPostService.waitingForApprovalBlogs()
		.then(function(response)
		{
			$scope.waitingApproval = response.data;
			console.log(response.data);
		},
		function(response){
			if(response.status == 401)
				{
				$location.path('/login')
				}
		})
	}
	
	function approvedBlogs(){
		BlogPostService.approvedBlogs().then(function(response){
			$scope.approvedBlogs = response.data;
			console.log(response.data);
		},
		function(response){
			if(response.status == 401)
				$location.path('/login');
				$locaion.path('/saveBlogPost');
		})
	}
	
	watingForApprovalBlogs()
	approvedBlogs()
	
})