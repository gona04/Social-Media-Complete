/**
 * 
 */
app.factory('BlogPostService',function($http){
	var blogPostService = {};
	var BASE_URL = "http://localhost:3030/Diaries1";
	
	//TO GET ONLY NOT SEEN BLOGS OR REJECTED LOGS
	blogPostService.waitingForApprovalBlogs = function()
	{
		return $http.get(BASE_URL + "/getBlogPosts/0");
	}
	
	//TO GET ONLY APPROVED BLOGS
	blogPostService.approvedBlogs = function()
	{
		return $http.get(BASE_URL + "/getBlogPosts/1");
	}
	
	//TO WRITE A BLOG POST AND SAVE IT IN THE DATABASE
	blogPostService.saveBlogPost = function(blogPost)
	{
		return $http.post(BASE_URL + "/saveBlogPost",blogPost);
	}
	
	//TO GET A SPECIFIC BLOG POST
	blogPostService.getBlogPostById = function(id)
	{
		return $http.get(BASE_URL + "/getBlogPostById/" + id);
	}
	
	//TO LET THE ADMIN DECIDE WHICH BLOG TO ACCEPT
	blogPostService.updateBlogPost = function(blogPost)
	{
		return $http.put(BASE_URL + "/updateBlogPost",blogPost);
	}
	//TO GIVE USER THE APPROVAL STATUS
	blogPostService.getApprovalStatus = function()
	{
		return $http.get(BASE_URL + "/getApprovalStatus");
	}
	
	/* BLOG COMMENT */
	
	 blogPostService.addComment=function(blogComment)
	 {
	    	console.log(blogComment)
	    	return $http.post(BASE_URL+ "/addcomment",blogComment)
	    }	
	    blogPostService.getBlogComments=function(blogPostId){
	    	return $http.get(BASE_URL + "/getblogcomments/"+blogPostId)
	    }
	    blogPostService.updateViewedStatus=function(approvalStatus){
			return $http.put(BASE_URL + "/updateviewedstatus",approvalStatus)
		}
	return blogPostService;
})