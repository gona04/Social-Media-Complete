/**
 * 
 */

var app = angular.module('mainApp', [ 'ngRoute', 'ngCookies' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/home', {
		templateUrl : 'views/home.html',
		controller:'HomeController'
	})
	.when('/register', {
		templateUrl : 'views/registrationForm.html',
		controller : 'UserController'
	})
	.when('/login', {
		templateUrl : 'views/login.html',
		controller : 'UserController'
	})
	.when('/editProfile', {
		templateUrl : 'views/editProfile.html',
		controller : 'UserController'
	})
	.when('/getAllJobs',{
		templateUrl:'views/jobTitle.html',
		controller:'JobController'
	})
	.when('/saveJob',{
		templateUrl:'views/jobForm.html',
		controller:'JobController'
	})
	.when('/saveBlogPost',{
		templateUrl:'views/addBlogPost.html',
		controller:'BlogPostController'
	})
	.when('/getAllBlogs',{
		templateUrl:'views/listOfBlogs1.html',
		controller:'BlogPostController'
	})
	.when('/getBlogPostById/:id',{
		templateUrl:'views/blogPostDetails.html',
		controller:'BlogPostDeatilsController'
	})
	.when('/BlogPostForApproval/:id',{
		templateUrl:'views/approvalForm.html',
		controller:'BlogPostDeatilsController'
	})
	.when('/approvalStatus/:id',{
		templateUrl:'views/blogPostStatus.html',
		controller:'BlogPostDetailsController'
	})
	.when('/getAllUsers',{
		templateUrl:'views/listOfUsers.html',
		controller:'FriendController'
	})
	.when('/pendingRequests',{
		templateUrl:'views/pendingRequests.html',
		controller:'FriendController'
	})
	.when('/listOfFriends',{
		templateUrl:'views/listOfFriends.html',
		controller:'FriendController'
	})
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatController'
	})
	.when('/profilepic',{
		templateUrl:'views/profilepic.html'
	})
	.otherwise({
		templateUrl : 'views/home.html'
	})
});

app.run(function($rootScope, $cookieStore) 
		{
				console.log("in app.js outside if");
				console.log($rootScope.currentUser);
			if ($rootScope.currentUser == undefined)
				{
					console.log("in app.js");
					console.log($rootScope.currentUser);
					$rootScope.currentUser = $cookieStore.get("currentUser");
					console.log($rootScope.currentUser);
				}
})