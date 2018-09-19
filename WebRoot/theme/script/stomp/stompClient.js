var StompClientJsFun = function(){
	
	var stompClient = null;
	
	function _connect() {
		var loginName = $("#loginName").val();
	    var socket = new SockJS('/liuliu/lyadmingsocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({"accept-version":"1.0,1.1,2.0"}, function (frame) {
	    	console.info("chengonglelelelele");
	        stompClient.subscribe('/projectstomp/project/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        	projectstomp(JSON.parse(greeting.body));
	        });
	        
	        stompClient.subscribe('/notepadstomp/notepad/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        });
	        
	        stompClient.subscribe('/emailstomp/email/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        });
	        
	        stompClient.subscribe('/noticestomp/notice/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        });
	        
	        
	        stompClient.subscribe('/app/notepad/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        });
	        stompClient.subscribe('/app/project/'+loginName, function (greeting) {
	        	console.info("================================="+greeting);
	        	projectstomp(JSON.parse(greeting.body));
	        });
	    });
	}
	
	function _disconnect() {
	    if (stompClient != null) {
	        stompClient.disconnect();
	    }
	    console.log("Disconnected");
	}
	
	//代办任务
	function projectstomp(projectmap){
		var header_task_bar = $("#header_task_bar");
		var size = projectmap.size;
		var html = ' <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">'+
				        '<i class="icon-rocket"></i>'+
				        '<span class="badge badge-default"> '+size+' </span>'+
				    '</a>'+
				    '<ul class="dropdown-menu extended tasks">'+
					    '<li class="external">'+
			                    '<h3>您有'+
			                    '<span class="bold">'+size+' 个待定</span> 任务</h3>'+
			                '<a class="checkAllProjectTask" href="app_todo.html">查看所有</a>'+
			            '</li>'+
			            '<li>'+
				            '<div class="mt-element-list">'+
				            	'<div class="mt-list-container list-simple ext-1 group">';
		var project = projectmap.project;
		for(var pro in project){
			var proje = project[pro];
			var projectId = proje.id;
			var projectTitle = proje.title;
			var projectSize = proje.size;
			var projectHtml = '<a class="list-toggle-container collapsed" data-toggle="collapse" data-project-id="'+projectId+'" href="#project_'+projectId+'" aria-expanded="false">'+
								           '<div class="list-toggle" style="padding: 10px;">'+projectTitle+
							           '<span class="badge badge-default pull-right bg-white font-green bold">'+projectSize+'</span>'+
							       '</div>'+
							   '</a>'+
							   '<div class="panel-collapse collapse" id="project_'+projectId+'" aria-expanded="false" style="height: 0px;">'+
							   		'<ul>';
			var projectItems = proje.items;
			for(var taskIndex in projectItems){
				var task = projectItems[taskIndex];
				var taskId = task.id;
				var taskTitle = task.titile;
				var taskHtml =  '<li class="mt-list-item" style="border: 3px solid;padding:3px;">'+
									'<div class="list-item-content">'+
										'<h4 class="">'+
											'<a href="javascript:;" data-project-id="'+projectId+'" data-task-id="'+taskId+'">'+taskTitle+'</a>'+
										'</h4>'+
									'</div>'+
								'</li>';
				projectHtml+=taskHtml;
			}
			projectHtml += '</ul></div>';
			html+=projectHtml;
		}
		html+='</div></div></li></ul>';
		header_task_bar.html(html);
	}
	
	//当日任务
	function notepadstomp(notepadmap){
		
	}
	
	//邮件
	function emailstomp(emailmap){
		
	}
	
	//通知
	function noticestomp(noticemap){
		
	}
	
	
	return {
		connect : function(){
			_connect();
		}
	}
}();
jQuery(document).ready(function() {
	StompClientJsFun.connect();
})
