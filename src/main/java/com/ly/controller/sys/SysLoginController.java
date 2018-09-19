package com.ly.controller.sys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.po.SysTree;
import com.ly.po.SysUser;
import com.ly.service.sys.ISysUserService;
import com.ly.service.sys.IUserInfoService;
import com.ly.utils.Common;
import com.ly.utils.MyProperties;
import com.ly.utils.TreesConstants;
import com.ly.vo.AddOrEditUserInfoVo;

/**
* @ClassName: SysLoginController
* @Description: 
* @author linyan
* @date 2017年7月12日 下午5:39:56
*
*/
@Controller
@RequestMapping(value="sys")
public class SysLoginController {

	@Autowired
	private RedisManager redisManager;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	private final String CALLBACK_REDIS_END_KEY = "-callback";
	
	
	@RequestMapping(value="login")
	public String login(String userName , String userPass , String remenber , HttpServletRequest request,HttpServletResponse response,Model model , RedirectAttributes redirectAttributes ){
		System.out.println("remenber:" + userName);
		System.out.println("userPass:" + userPass);
		System.out.println("remenber:" + remenber);
//		SecurityUtils.getSubject().login(new UsernamePasswordToken(userName, userPass));
		String url = "redirect:/sys/index";
		try {
			String verification = request.getParameter("verification"); //验证码
			if (verification==null||verification.trim().equals("")) {
				redirectAttributes.addAttribute("mes", "请输入验证码");
				return url;
			}
			String verLower = verification.toLowerCase();
			
			String sessionId = request.getSession().getId();
			
			byte[] redisVeri = redisManager.get(sessionId.getBytes());
			
			if (redisVeri==null||redisVeri.length<1) {
				redirectAttributes.addAttribute("mes", "验证码已过期");
				return url;
			}
			String redisVerifi = new String(redisVeri).toLowerCase();
			if (!verLower.equals(redisVerifi)) {
				redirectAttributes.addAttribute("mes", "您输入的验证码不正确");
				return url;
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("mes", "您输入的用户名或密码不正确.");
			return url;
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPass);
		if (remenber != null && !remenber.trim().equals("")) {
			token.setRememberMe(true);
		}
		
		System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject currentUser=SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			System.out.println("对用户[" + userName + "]进行登录验证..验证开始");
			currentUser.login(token);
			url = "redirect:/sys/main";
			if (remenber!=null&&"1".equals(remenber)) {
				Cookie cookie = new Cookie("userName", userName);
				cookie.setMaxAge(7*24*60*60);
				response.addCookie(cookie);
				
				Cookie passCookie = new Cookie("userPass", userPass);
				passCookie.setMaxAge(7*24*60*60);
				response.addCookie(passCookie);
			}
			HttpSession session = request.getSession();
			SysUser sysUser = (SysUser)session.getAttribute(MyProperties.get("SESSION_USER_KEY"));
			List<SysTree> listTrees = Common.getSysTrees();
			Boolean isNotepad = false;
			Boolean isEmail = false;
			Boolean isNotice = false;
			Boolean isProject = false;
			for (SysTree sysTree : listTrees) {
				switch (sysTree.getEnName()) {
					case TreesConstants.TREE_EMAIL:
						isEmail = true;
						break;
					case TreesConstants.TREE_NOTEPAD:
						isNotepad = true;
						break;
					case TreesConstants.TREE_NOTICE:
						isNotice = true;
						break;
					case TreesConstants.TREE_PROJECT:
						isProject = true;
						break;
					default:
						break;
				}
			}
			Integer userInfoId = Integer.valueOf(sysUser.getUserId());
			AddOrEditUserInfoVo userInfo = userInfoService.editFind(userInfoId);
			session.setAttribute("loginName", sysUser.getUserName());
			String picIcon = userInfo.getPicIcon();
			session.setAttribute("isProject", isProject);
			session.setAttribute("isNotice", isNotice);
			session.setAttribute("isEmail", isEmail);
			session.setAttribute("isNotepad", isNotepad);
			session.setAttribute("picIcon", picIcon);
			session.setAttribute("infoName", userInfo.getName());
			System.out.println(session.getId());
			System.out.println("对用户[" + userName + "]进行登录验证..验证通过");
		} catch (UnknownAccountException a) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addAttribute("mes", "未知账户");
		} catch (IncorrectCredentialsException a) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addAttribute("mes", "密码不正确");
		} catch (LockedAccountException a) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addAttribute("mes", "账户已锁定");
		} catch (ExcessiveAttemptsException a) {
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addAttribute("mes", "用户名或密码错误次数过多");
		} catch (AuthenticationException a) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
			a.printStackTrace();
			redirectAttributes.addAttribute("mes", "用户名或密码不正确");
		} catch (IndexOutOfBoundsException e) {
			redirectAttributes.addAttribute("mes", "密码不正确");
			e.printStackTrace();
		} catch (Exception e) {
			redirectAttributes.addAttribute("mes", "用户名或密码不正确");
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("login/yanZhengMa")
	public void yanZhengMa(HttpServletRequest request,HttpServletResponse response){
		//设置不缓存图片  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "No-cache");  
        response.setDateHeader("Expires", 0);  
        //指定生成的响应图片,一定不能缺少这句话,否则错误.  
        response.setContentType("image/jpeg");  
        int width=86,height=40;     //指定生成验证码的宽度和高度  
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //创建BufferedImage对象,其作用相当于一图片  
        Graphics g=image.getGraphics();     //创建Graphics对象,其作用相当于画笔  
        Graphics2D g2d=(Graphics2D)g;       //创建Grapchics2D对象  
        Random random=new Random();  
        Font mfont=new Font("楷体",Font.BOLD,22); //定义字体样式  
        g.setColor(getRandColor(200,250));  
        g.fillRect(0, 0, width, height);    //绘制背景  
        g.setFont(mfont);                   //设置字体  
        g.setColor(getRandColor(180,200));  
          
        //绘制100条颜色和位置全部为随机产生的线条,该线条为2f  
        for(int i=0;i<100;i++){  
            int x=random.nextInt(width-1);  
            int y=random.nextInt(height-1);  
            int x1=random.nextInt(6)+1;  
            int y1=random.nextInt(12)+1;  
            BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); //定制线条样式  
            Line2D line=new Line2D.Double(x,y,x+x1,y+y1);  
            g2d.setStroke(bs);  
            g2d.draw(line);     //绘制直线  
        }  
        //输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。  
        String sRand="";  
        String ctmp="";  
        int itmp=0;  
        //制定输出的验证码为四位  
        for(int i=0;i<4;i++){  
//            switch(random.nextInt(3)){  
            switch(4){  //测试时  不生成 字母 和 汉字
                case 1:     //生成A-Z的字母  
                     itmp=random.nextInt(26)+65;  
                     ctmp=String.valueOf((char)itmp);  
                     break;  
                case 2:     //生成汉字  
                     String[] rBase={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};   
                     //生成第一位区码  
                     int r1=random.nextInt(3)+11;  
                     String str_r1=rBase[r1];  
                     //生成第二位区码  
                     int r2;  
                     if(r1==13){  
                         r2=random.nextInt(7);     
                     }else{  
                         r2=random.nextInt(16);  
                     }  
                     String str_r2=rBase[r2];  
                     //生成第一位位码  
                     int r3=random.nextInt(6)+10;  
                     String str_r3=rBase[r3];  
                     //生成第二位位码  
                     int r4;  
                     if(r3==10){  
                         r4=random.nextInt(15)+1;  
                     }else if(r3==15){  
                         r4=random.nextInt(15);  
                     }else{  
                         r4=random.nextInt(16);  
                     }  
                     String str_r4=rBase[r4];  
                     //将生成的机内码转换为汉字  
                     byte[] bytes=new byte[2];  
                     //将生成的区码保存到字节数组的第一个元素中  
                     String str_12=str_r1+str_r2;  
                     int tempLow=Integer.parseInt(str_12, 16);  
                     bytes[0]=(byte) tempLow;  
                     //将生成的位码保存到字节数组的第二个元素中  
                     String str_34=str_r3+str_r4;  
                     int tempHigh=Integer.parseInt(str_34, 16);  
                     bytes[1]=(byte)tempHigh;  
						try {
							ctmp=new String(bytes,"GBK");
						} catch (UnsupportedEncodingException e) {
							ctmp = "了";
							e.printStackTrace();
						}  
                     break;  
                default:  
                     itmp=random.nextInt(10)+48;  
                     ctmp=String.valueOf((char)itmp);  
                     break;  
            }  
            sRand+=ctmp;  
            Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));  
            g.setColor(color);  
            //将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示  
            /*将文字旋转制定角度*/  
            Graphics2D g2d_word=(Graphics2D)g;  
            AffineTransform trans=new AffineTransform();
            int du = random.nextInt(10);
            trans.rotate((du+30)*3.14/180,15*i+8,7);  
            /*缩放文字*/  
            float scaleSize=random.nextFloat()+0.8f;  
            if(scaleSize>1f) scaleSize=1f;  
            trans.scale(scaleSize, scaleSize);  
            g2d_word.setTransform(trans);
            if (i==0) {
            	g.drawString(ctmp, 15*i+10, 20); 
			}else {
				g.drawString(ctmp, 15*i+20, 20); 
			}
             
        }
        String sessionId = request.getSession().getId();
        System.out.println(sessionId);
        redisManager.set(sessionId.getBytes(), sRand.getBytes(), 3000);
        g.dispose();    //释放g所占用的系统资源  
        try {
        	 ImageIO.write(image,"JPEG",response.getOutputStream()); //输出图片
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*该方法主要作用是获得随机生成的颜色*/   
    public Color getRandColor(int s,int e){  
        Random random=new Random ();  
        if(s>255) s=255;  
        if(e>255) e=255;  
        int r,g,b;  
        r=s+random.nextInt(e-s);    //随机生成RGB颜色中的r值  
        g=s+random.nextInt(e-s);    //随机生成RGB颜色中的g值  
        b=s+random.nextInt(e-s);    //随机生成RGB颜色中的b值  
        return new Color(r,g,b);  
    }
	
	@RequestMapping(value = "keyDown")
	@ResponseBody
	public Object keyDown(String verification,HttpServletRequest request ,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		if (verification==null||verification.trim().equals("")) {
			map.put("code", 1);
			map.put("mes", "您输入的验证码为空");
		}else {
			String sesseionId = null ;
			try {
				sesseionId = request.getSession().getId();
				System.out.println(sesseionId);
				byte[] redisVeri = redisManager.get(sesseionId.getBytes());
				if (redisVeri == null || redisVeri.length<1) {
					map.put("mes", "验证码已过期，请刷新");
					map.put("code", 3);
				}else {
					String redisVerification = new String(redisVeri).toLowerCase();

					if (verification.trim().length()>redisVerification.length()) {
						map.put("code", 6);
						map.put("mes", "位数过多");
					}else {
						if (verification.trim().length()==redisVerification.length()) {
							String verifiLower = verification.trim().toLowerCase();
							if (verifiLower.equals(redisVerification)) {
								map.put("code", 0);
								map.put("mes", "");
							}else {
								map.put("code", 7);
								map.put("mes", "验证码不正确");
							}
						}else {
							String verifiLower = verification.trim().toLowerCase();
							
							String verifiLength = redisVerification.substring(0,verification.trim().length());
							
							if (verifiLower.trim().equals(verifiLength)) {
								map.put("code", 5);
								map.put("mes", "部分相同");
							}else {
								map.put("code", 4);
								map.put("mes", "验证码不正确.");
							}
						}
					}
				}
			} catch (Exception e) {
				map.put("code", 2);
				map.put("mes", "出错了 ");
			}
		}
		return map;
	}
    
    
    
	@RequestMapping(value = "index")
	public String index(String mes,Model model,HttpServletRequest request){
//		SecurityUtils.getSubject().logout();
		model.addAttribute("mes", mes);
		Cookie[] cookies = request.getCookies();
		String userName = "";
		String userPass = "";
		String ramenber = "0";
		if (cookies!=null&&cookies.length>0) {
			for (Cookie cookie : cookies) {
				if ("userName".equals(cookie.getName())) {
					ramenber = "1";
					userName = cookie.getValue();
				}else if ("userPass".equals(cookie.getName())) {
					userPass = cookie.getValue();
				}
			}
		}
		model.addAttribute("userNmae", userName);
		model.addAttribute("userPass", userPass);
		model.addAttribute("ramenber", ramenber);
		return "index";
	}
	/**
	 * @Title: retrievePass
	 * @author: linyan
	 * @Description: 找回密码
	 * @return
	 */
	@RequestMapping(value = "retrievePass")
	@ResponseBody
	public Object retrievePass(String telPhoneCallBack ,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map = sysUserService.retrievePass(telPhoneCallBack);
		if(map.get("code").toString().equals("0")){
			String callbackcode = "";
			for (int i = 0; i < 4; i++) {
				Random random = new Random();
				int a1 = random.nextInt(10);
				callbackcode+=a1;
			}
			redisManager.set((telPhoneCallBack+CALLBACK_REDIS_END_KEY).getBytes(),callbackcode.getBytes(), 60*2);
			map.put("callbackdoe", callbackcode);
			System.out.println("找回码："+telPhoneCallBack+"----"+callbackcode);
			//发送手机 找回码
		}
		return map;
	}
	
	@RequestMapping(value = "yanzhengzhaohuima")
	@ResponseBody
	public Object yanzhengzhaohuima(String telPhoneCallBack ,String telPhone ,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(telPhone+"----"+telPhoneCallBack);
		byte[] v = redisManager.get((telPhoneCallBack+CALLBACK_REDIS_END_KEY).getBytes());
		map.put("code", 0);
		if (v!=null&&v.length>0) {
			String vm = new String(v);
			if (!telPhone.equals(vm)) {
				map.put("code", 1);
				map.put("mes", "您输入的找回码不正确，请重新输入。");
			}
		}else {
			map.put("code", 2);
			map.put("mes", "您的找回码已经过期。请重新获取");
		}
		return map;
	}
	
	@RequestMapping(value = "saveNewPass")
	@ResponseBody
	public Object saveNewPass(String newPass ,String tel, HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(newPass+"<<<<0000000000000000>>>"+tel);
		map = sysUserService.saveNewPass(newPass,tel);
		return map;
	}
	
	@RequestMapping(value = "logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:index";
	}
	
	@RequestMapping(value="main")
	public String goMain(HttpServletRequest request,HttpServletResponse response){
		return "main";
	}
	
	@RequestMapping(value="goMainBody")
	public String goMainBody(){
		return "mainBody";
	}
	
	@RequestMapping(value ="unauthenticated")
	public String unauthenticated(){
		return "unauthenticated";
	}
	
	@RequestMapping(value = "unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
	
	@RequestMapping(value = "forezerofore")
	public String forezerofore(){
		return "forezerofore";
	}
	
	@RequestMapping(value ="fivezerozero")
	public String fivezerozero(){
		return "fivezerozero";
	}
}
