//桌面提醒
function DesktopNotify(title,options){
	var option = {};
	if(title!=null && title != "" && title != undefined){
		//显示信息
		if(options.body){
			option.body = options.body;
		}
		//图标 
		if(options.icon){
			option.icon = options.icon;
		}
		//方向  右下  左下   自动    auto  ltr   rtl
		if(options.dir){
			option.dir = options.dir;
		}
		//标签   如果设置了renotify 为 true  覆盖 相同的标签 通知
		if(options.tag){
			option.tag = options.tag;
		}
		//图片
		if(options.image){
			option.image = options.image;
		}
		//数据   任何 数据类型
		if(options.data){
			option.data = options.data;
		}
		//语言
		if(options.lang){
			option.lang = options.lang;
		}
		//是否  覆盖 相同标签的 通知
		if(options.renotify){
			option.renotify = options.renotify;
		}
		//是否 需要用户 点击通知 后 才消失 默认自动消失
		if(options.requireInteraction){
			option.requireInteraction = options.requireInteraction;
		}
		// 先检查浏览器是否支持   // 检查用户是否同意接受通知
		if ("Notification" in window && Notification.permission === "granted") {
			var notification = new Notification(title,option);
			if(options.onclick&&typeof options.onclick=="function"){
				notification.onclick=options.onclick;
			}
			if(options.onclose&&typeof options.onclose=="function"){
				notification.onclose =options.onclose;
			}
			return notification;
		}
		// 否则我们需要向用户获取权限
		else if (Notification.permission !== 'denied') {
			Notification.requestPermission(function (permission) {
				// 如果用户同意，就可以向他们发送通知
				if (permission === "granted") {
					var notification = new Notification(title,option);
					if(options.onclick&&typeof options.onclick=="function"){
						notification.onclick=options.onclick;
					}
					if(options.onclose&&typeof options.onclose=="function"){
						notification.onclose =options.onclose;
					}
					return notification;
				}
			});
		}
	}
}

function TitleTwinkleInterval(){
	var ti = document.title;
	var interlv = window.setInterval(function(){
		document.title = "【您有新消息】";
	},300)
	var op = {};
	op.title=ti;
	op.interval = interlv;
	return op;
}

function ClearTitleInterval(option){
	document.title = op.title;
	window.clearTimeout(option.interval);
}
