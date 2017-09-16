package com.ly.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

public class SendMail {
	
	/**
	 * @Title: sendMessage
	 * @Description: 发送邮件
	 * @param isAuth   是否验证  true  是  
	 * @param smtpHost  发送 服务器地址
	 * @param smtpPort 发送邮件需要的端口
	 * @param isSSL    是否 使用 SSL 协议证书   
	 * @param fromUser 发件人地址
	 * @param fromUserPassword  发件人邮箱密码 
	 * @param toUsers  发件人地址
	 * @param ccUsers  抄送人 地址
	 * @param bccUsers  密送人 地址
	 * @param title    邮件标题
	 * @param messageText   邮件  内容
	 * @param messageType   邮件 内容的 类型
	 * @param files			附件
	 * @throws Exception    
	 */
	public static void sendMessage(boolean isAuth,String smtpHost,String smtpPort,boolean isSSL, String fromUser,
			String fromUserPassword, List<String> toUsers,List<String> ccUsers,List<String> bccUsers, String title,
			String messageText, String messageType,List<File> files) throws Exception {
		/** 参数验证   开始 */
		if(StringUtils.isEmpty(smtpHost)){
			throw new NullPointerException("smtpHost is Required");
		}
		if(StringUtils.isEmpty(smtpPort)){
			throw new NullPointerException("smtpPort is Required");
		}
		if (isAuth) {
			if(StringUtils.isEmpty(fromUser)){
				throw new NullPointerException("fromUser is Required");
			}
			if(StringUtils.isEmpty(fromUserPassword)){
				throw new NullPointerException("fromUserPassword is Required");
			}
		}
		if(StringUtils.isEmpty(title)){
			throw new NullPointerException("title is Required");
		}
		if(StringUtils.isEmpty(messageText)){
			throw new NullPointerException("messageText is Required");
		}
		if (StringUtils.isEmpty(messageType)) {
			messageType = "text/html;charset=gb2312";
		}
		if(toUsers==null||toUsers.size()<1){
			throw new NullPointerException("toUsers is Required");
		}
		if (ccUsers==null||ccUsers.size()<1) {
			ccUsers = new ArrayList<String>();
		}
		if (bccUsers==null||bccUsers.size()<1) {
			bccUsers = new ArrayList<String>();
		}
		if (files==null||files.size()<1) {
			files = new ArrayList<File>();
		}
		/** 参数验证   结束  */
		
		//Session对象 所需要的参数
		Properties props = System.getProperties();
		//发送 服务器地址 
		props.setProperty("mail.smtp.host", smtpHost);
		//发送邮件需要的端口  这个端口 是和 发送服务器 地址 相关联的    不用的服务器地址 端口号是不同的
		props.setProperty("mail.smtp.port", smtpPort);
		//验证
		props.put("mail.smtp.auth", isAuth ? "true":"false");
		//是否 使用  STARTTLS 加密传输
		props.setProperty("mail.smtp.starttls.enable","true");
		//是否 使用 SSL 协议证书   
		props.setProperty("mail.smtp.ssl.enable", isSSL ? "true":"false");
		//这是看 Properties 日志的  真正上线后 可以 不用设置   默认 为 false
		props.put("mail.debug", "true");
		//Session对象
		Session mailSession = null;
		if (isAuth) {
			mailSession = Session.getInstance(props,new Authenticator() {
				//验证    也可以写一个类  继承 Authenticator 重写  getPasswordAuthentication 
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromUser,fromUserPassword);
				}
			});
		}else {
			mailSession = Session.getInstance(props);
		}
		//这是看 Session 日志的  真正上线后 可以 不用设置   默认 为 false
		mailSession.setDebug(true);
		//得到 message 对象
		MimeMessage message = new MimeMessage(mailSession);

		//发件人地址
		InternetAddress fromAddress = new InternetAddress(fromUser);
		// 设置发件人
		message.setFrom(fromAddress);
		// 这里有几种方法
		//直接循环 一个一个 添加 到message中  
		{
			for (String toUser : toUsers) {
				InternetAddress toAddress = new InternetAddress(toUser);
				//用 addRecipient 方法  设置 收件人
				message.addRecipient(RecipientType.TO, toAddress);
			}
		}
		/**
		//组装成 Address[] 数组    然后 在添加到 mesage中
		{
			Address[] toUsersAddresses = new Address[toUsers.size()];
			for (int i = 0; i < toUsers.size(); i++) {
				String toUser = toUsers.get(i);
				InternetAddress toAddress = new InternetAddress(toUser);
				toUsersAddresses[i] = toAddress;
			}
			//用 addRecipients 方法  
			message.addRecipients(RecipientType.TO,toUsersAddresses);
		}

		//还可以直接用string 添加 到 mesage中
		{
			//这里 主要是  拼成  xx@123.com,xx@123.com 这种形式    用英文逗号（,）分隔的形式  
			//也可以用其他方法
			StringBuffer toUsersAddresses  = new StringBuffer();
			for (String toUser : toUsers) {
				toUsersAddresses.append(toUser+",");
			}
			String toUserAddresses = toUsersAddresses.substring(0, toUsersAddresses.length()-1);
			//用 addRecipients 方法 .  
			//这个方法 在源码中 同样 也是转换成了  InternetAddress[] 数组  .  InternetAddress 类 是Address 的子类
			//InternetAddress.parse(addresses)   参数是string 返回 InternetAddress[] 数组  .
			message.addRecipients(RecipientType.TO,toUserAddresses);
		}
		*/
		for (String ccUser : ccUsers) {
			InternetAddress ccAddress = new InternetAddress(ccUser);
			//设置 抄送人 地址
			message.addRecipient(RecipientType.CC, ccAddress);
		}
		for (String bccUser : bccUsers) {
			InternetAddress bccAddress = new InternetAddress(bccUser);
			//设置 密送人 地址
			message.addRecipient(RecipientType.BCC, bccAddress);
		}
		//设置 发送 时间
		message.setSentDate(Calendar.getInstance().getTime());
		//设置标题
		message.setSubject(title);
		//设置 邮件内容
		//用  Multipart 设置参数
		Multipart multipart = new MimeMultipart();
		//循环 文件  分别 添加 到 Multipart 中
		for (File file : files) {
			//设置  内容
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			//设置 文件 
			DataSource dataSource = new FileDataSource(file);
			// 设置 内容  头
			DataHandler dataHandler = new DataHandler(dataSource);
			mimeBodyPart.setDataHandler(dataHandler);
			//  MimeUtility.encodeText()   这个发放 是为了方式乱码的
			mimeBodyPart.setFileName(MimeUtility.encodeText(dataHandler.getName()));
			multipart.addBodyPart(mimeBodyPart);
		}
		//设置  消息体
		BodyPart part = new MimeBodyPart();
		part.setContent(messageText, messageType);
		multipart.addBodyPart(part);
		// 将  消息体 和 附件 放到  message 消息 中
		message.setContent(multipart);
		//发送消息
		Transport.send(message);
	}

	public static void main(String[] args) {
		try {
			List<String> toUsers = new ArrayList<String>();
			toUsers.add("linyan@caixun.com");
//			toUsers.add("linyan@caixun.com");
			List<String> ccUsers = new ArrayList<String>();
			ccUsers.add("linyan9248@gmail.com");
			List<File> files = new ArrayList<File>();
			files.add(new File("C:\\Users\\linyan\\Desktop\\APP管理后台PHP端接口.doc"));
//			FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\linyan\\Desktop\\APP管理后台PHP端接口.doc"));
//			SendMail.sendMessage("smtp.gmail.com", "465", true, "linyan@dwnews.com", "linyan9248..", toUsers, ccUsers, "agg", "ggggggggggggg", "text/html;charset=gb2312",inputStream);
			SendMail.sendMessage(true,"smtp.global-mail.cn", "25", false, "linyan@caixun.com", "ly123456..", toUsers,null, null, "agg", "gggggg是打发ggggggg",null,null);
//			SendMail.sendMessage("smtp.gmail.com", "linyan@dwnews.com",
////					SendMail.sendMessage("smtp.gmail.com", "linyan9248@gmail.com",
//					"linyan9248..", "linyan@caixun.com", "nihao",
//					"---------------wrwe-----------",
//					"text/html;charset=gb2312");
//			SendMail.sendMessage("smtp.global-mail.cn", "linyan@caixun.com",
//					"ly123456..", "linyan@dwnews.com", "nihao",
//					"---------------wrwe-----------",
//					"text/html;charset=gb2312");
			String lajlkString = "linyan@caixun.com";
			
			
			InternetAddress[] gg = InternetAddress.parse(lajlkString);
			
			for (InternetAddress address : gg) {
				String a = address.getAddress();
				System.out.println(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
