/**
 * 
 */
app.factory('JobService',function($http){
	
	var jobService = {};
	var BASE_URL = "http://localhost:3030/Diaries1/";
	
	jobService.getAllJobs = function()
	{
		return $http.get(BASE_URL+"/getAllJobs");
	}
	jobService.saveJob = function(job)
	{
		return $http.post(BASE_URL + "/saveJob",job);
	}
	jobService.getJobById = function(id)
	{
		return $http.get(BASE_URL+"/getjobbyid/" + id);
	}
	return jobService;
}
	
);