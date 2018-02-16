/**
 * 
 */
app.controller('JobController',function(JobService,$scope,$location)
{
/*	if ($rootScope.currentUser == undefined)
		{
		console.log("in app.js");
	console.log($rootScope.currentUser);
	$rootScope.currentUser = $cookieStore.get("currentUser");
	console.log($rootScope.currentUser);
		}*/
	$scope.showJobDetails = false;
	function getAllJobs()
	{
		JobService.getAllJobs().then(function(response)
		{
			console.log('in job Controller success');
			$scope.jobs=response.data;
			console.log(response.data);
		},
		function(response){
			console.log('in job controller error')
			$location.path('/login');
			console.log(response.data);
		})
	}
	
	$scope.saveAJob = function(){
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getAllJobs')
		},
		function(response){
			console.log(response.status);
			if(response.status == 401)
				{
					$scope.error=response.data
					$location.path('/login')
				}
			else if(response.status == 500)
				{
					$scope.error = response.data;
					$location.path('/saveJob');
				}
			$location.path('/home');
		})
	}
	
	$scope.getJobDetails = function(id){
		$scope.showJobDetails = true;
		console.log('in GetJobDetails');
		console.log(id);
		console.log($scope.showJobDetails);
		JobService.getJobById(id).then(function(response){
			console.log(response.data);
			$scope.jo = response.data;
			//$location.path('/getAllJobs');
			console.log($scope.jo);
		},
		function(response)
		{
			$scope.error = response.data;
			$location.path('/login');
		})
	}
	
getAllJobs()
})