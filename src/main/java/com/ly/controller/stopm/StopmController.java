package com.ly.controller.stopm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.service.doproject.IDoProjectService;
import com.ly.service.email.IEmailService;
import com.ly.service.notepad.INotepadService;
import com.ly.service.notice.INoticeService;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor.Uri;

/**
* @ClassName: StopmController
* @Description: 
* @author linyan
* @date 2017年8月18日 上午9:16:32
*
*/
@Controller
public class StopmController {
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private INotepadService notepadService;
	
	@Autowired
	private INoticeService noticeService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IDoProjectService doProjectService;
	
	
	@SubscribeMapping(value = "/notepad/{user}")
    public Object subscribeNotepadTask(@DestinationVariable String user){
    	Map<String, Object> map = notepadService.selectByUser(user);
    	return map; 
    }
	
    @SubscribeMapping("/email/{user}")
    public Object subscribeEmailTask(@DestinationVariable String user) { // assuming this conversion works
    	Map<String, Object> map = emailService.selectByEmail(user);
        return map; 
    }
    
    @SubscribeMapping("/notice/{user}")
    public Object subscribeNoticeTask(@DestinationVariable String user) { // assuming this conversion works
    	Map<String, Object> map = noticeService.selectByNotice(user);
    	return map; 
    }
    
    @SubscribeMapping("/project/{user}")
    public Object subscribeProjectTask(@DestinationVariable String user) { // assuming this conversion works
    	System.out.println(user+"00000");
    	Map<String, Object> map = doProjectService.selectByProject(user);
    	return map; 
    }
	
	
	
	
//	@Autowired
//	private IStompService stompService;
//	
	@MessageMapping(value="testStomp")
//	@SendTo(value="")
	public Object x(HttpServletRequest request,HttpServletResponse response){
		System.out.println("进来了 。。。。。。。。。。。。。。。。。。。。。。。。。。");
		simpMessagingTemplate.convertAndSend("阿里看到几个拉苦尽甘来");
		
		return null;
	}
	
	
	/** 
     * 表示服务端可以接收客户端通过主题“/app/hello”发送过来的消息，客户端需要在主题"/topic/hello"上监听并接收服务端发回的消息 
     * @param topic 
     * @param headers 
     */  
    @MessageMapping("/hello") //"/hello"为WebSocketConfig类中registerStompEndpoints()方法配置的  
//    @SendTo("/topic/task")
    public Object greeting(@Header("atytopic") String topic, @Headers Map<String, Object> headers,String name) {  
        System.out.println("connected successfully....");  
        System.out.println(topic);  
        System.out.println(headers);
        simpMessagingTemplate.convertAndSend("ggggg");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name==null?"咖喱机构":name);
        
        return map;
    }  
  
    /** 
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中， 
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。 
     * @return 
     */  
    @MessageMapping("/message")  
    @SendToUser("/message")  
    public Object handleSubscribe() {  
        System.out.println("this is the @SubscribeMapping('/marco')");  
        return "I am a msg from SubscribeMapping('/macro').";  
    }  
  
    /** 
     * 测试对指定用户发送消息方法 
     * @return 
     */  
    @RequestMapping(value = "/send",produces="application/json;charset=utf-8")
    @ResponseBody
    public Object send(String type ,String p , String user , String mes , HttpServletRequest request,HttpServletResponse response) {  
    	System.out.println(type+"<<>>"+user+"<<<>>>"+mes);
    	 String alk = "{\"aaa\":\"aab\"}";
//    	 simpMessagingTemplate.send("/topic/notepad/gg",alk); 
//    	simpMessagingTemplate.convertAndSend( "/topic/notepad/gg",alk); 
//    	simpMessagingTemplate.convertAndSend( "/topic/notepad/gg","{\""+type+"\":\""+mes+"\"}"); 
    	 Map<String, Object> map = doProjectService.selectByProject(user);
    	 Integer size = (Integer)map.get("size");
    	 map.put("size", size+1);
    	simpMessagingTemplate.convertAndSend( "/"+p+"/"+type+"/"+user,map); 
        return "I am a msg from SubscribeMapping('/macro').";  
    }  
	
//    @SubscribeMapping(value = "/bs/task")
//    public Object subscribeTopicTask(){
//    	System.out.println("=================================sssss========================");
//    	return ""; 
//    }
//    @SubscribeMapping("/bs/{user}")
//    public String getUser(@DestinationVariable String user) { // assuming this conversion works
//        String alk = "{\"aaa\":\"aab\"}";
//        
//        System.out.println("======================================="+user);
//        return alk;
//    }
    
}
