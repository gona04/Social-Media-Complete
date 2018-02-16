/**
 * 
 */
console.log('outside ChatController')
app.controller("ChatController", function($scope, ChatService) {
	  $scope.messages = [];
	  $scope.message = "";
	  $scope.max = 140;

	  console.log('in chatController module');
	  
	  $scope.addMessage = function() {
		  console.log('in chat Controller addMessage() method');
		console.log($scope.message);
	    ChatService.send($scope.message);
	    $scope.message = "";
	  };

	  ChatService.receive().then(null, null, function(message) {
		  console.log('in chat controller receieve() method');
	    $scope.messages.push(message);
	  });
	});