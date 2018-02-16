/**
 * 
 */
console.log('outside chat Service')
app.factory('ChatService',function($q,$timeout)
{
	console.log('in chatservice');
	 var service = {},
	    listener = $q.defer(), 
	    socket = {
	      client: null,
	      stomp: null
	    }, messageIds = [];
	    
	    service.RECONNECT_TIMEOUT = 30000;
	    service.SOCKET_URL = "/RajChat/chat";
	    service.CHAT_TOPIC = "/topic/message";
	    service.CHAT_BROKER = "/app/chat";
	    
	    service.receive = function() {
	    	console.log('in chat service in receieve() method')
	      return listener.promise;
	    };
	    
	    service.send = function(message) {
	    	console.log('in chat service in send() message')
	      var id = Math.floor(Math.random() * 1000000);
	      socket.stomp.send(service.CHAT_BROKER, {
	        priority: 9
	      }, JSON.stringify({
	        message: message,
	        id: id
	      }));
	      messageIds.push(id);
	    };
	    
	    var reconnect = function() {
	    	console.log('in chat service in reconnect() method')
	      $timeout(function() {
	        initialize();
	      }, this.RECONNECT_TIMEOUT);
	    };
	    
	    var getMessage = function(data) {
	    	console.log('in chat service in getMessage() method')
	      var message = JSON.parse(data), out = {};
	      out.message = message.message;
	      out.time = new Date(message.time);
	      /*if (_.contains(messageIds, message.id)) {
	        out.self = true;
	        messageIds = _.remove(messageIds, message.id);
	      }*/
	      return out;
	    };
	    
	    var startListener = function() {
	    	console.log('in chat service start listner')
	      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
	        listener.notify(getMessage(data.body));
	      });
	    };
	    
	    var initialize = function()
	    {
	    console.log('in chat service initialize')
	      socket.client = new SockJS(service.SOCKET_URL);
	      socket.stomp = Stomp.over(socket.client);
	      socket.stomp.connect({}, startListener);
	      socket.stomp.onclose = reconnect;
	    };
	    
	    initialize();
	    return service;
});
