package com.ly.service.crawler.impl;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.stereotype.Service;

import com.ly.service.crawler.IClockService;

import Com.FirstSolver.Splash.FaceIdProtocolCodecFactory;
import net.sf.json.JSONObject;

/**
* @ClassName: ClockServiceImpl
* @Description: 
* @author linyan
* @date 2018年2月2日 下午2:14:29
*
*/
@Service
public class ClockServiceImpl  implements IClockService {
	
	class MyIoHandler extends IoHandlerAdapter{
		private String mesg = "";
		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			session.closeOnFlush();
			mesg = message+"";
		}
		public String getMes() {
			return mesg;
		}

		public void setMes(String mes) {
			this.mesg = mes;
		}
	}
	
	/* （非 Javadoc）
	 * @see com.ly.service.crawler.IClockService#getClock(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getClock(String statr, String end) {
		List<Map<String, Object>> reList  = new ArrayList<Map<String,Object>>();
		ClockServiceImpl clockServiceImpl = new ClockServiceImpl();
		MyIoHandler ioHandler = clockServiceImpl.new MyIoHandler();
		IoConnector connector = new NioSocketConnector();
		connector.getSessionConfig().setReaderIdleTime(60);

	    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new FaceIdProtocolCodecFactory("GBK", false, false)));
	    connector.setHandler(ioHandler);
		
	    InetSocketAddress inetSocketAddress = new InetSocketAddress("172.23.255.4", 9922);
	    ConnectFuture connFuture = connector.connect(inetSocketAddress);
	    try
	    {
	    	Calendar calendar = Calendar.getInstance();
	    	int syear = calendar.get(Calendar.YEAR);
	    	int smonth = calendar.get(Calendar.MONTH)+1;
	    	int sday = calendar.get(Calendar.DAY_OF_MONTH);
	    	String startTime = "";
	    	if (statr!=null&&!statr.equals("")) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		try {
					sdf.parse(statr);
					startTime = statr;
				} catch (Exception e) {
					startTime = ""+syear+"-"+smonth+"-"+sday;
				}
			}else {
				startTime = ""+syear+"-"+smonth+"-"+sday;
			}
	    	String endTime = "";
	    	calendar.add(Calendar.DAY_OF_MONTH, 1);
	    	int eyear = calendar.get(Calendar.YEAR);
	    	int emonth = calendar.get(Calendar.MONTH)+1;
	    	int eday = calendar.get(Calendar.DAY_OF_MONTH);
	    	if (end!=null&&!end.equals("")) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		try {
					sdf.parse(end);
					endTime = end;
				} catch (Exception e) {
					endTime = ""+eyear+"-"+emonth+"-"+eday+" 00:00:00";
				}
			}else {
		    	endTime = ""+eyear+"-"+emonth+"-"+eday+" 00:00:00";
			}
//	    	String mes = "GetPictureName(time=\"2017-12-06 13:00:00\" type=\"face\") ";
	    	String mes = "GetRecord(start_time=\""+startTime+"\" end_time=\""+endTime+"\")";
//	    	String mes = "GetRecord(start_time=\"2018-01-30\" end_time=\"2018-01-31 00:00:00\")";
//	    	String mes = "GetEmployeeID()";
//	    	String mes = "GetDeviceInfo()";
//	    	String mes = "GetNetInfo()";
//	    	String mes = "GetManagerID()"; //Return(result="success" total="4" id="3554" id="3679" id="3737" id="3690")
//	    	String mes = "GetManager(id=\"3690\")";
//	    	String mes = "GetManager(id=\"3690\")";
	      connFuture.awaitUninterruptibly();
	      connFuture.getSession().setAttribute("COMMAND_GETRECORD", mes);
	      connFuture.getSession().write(mes);

	      connFuture.getSession().getCloseFuture().awaitUninterruptibly().isClosed();
	      connFuture.getSession().getCloseFuture().isClosed();
	      connFuture.getSession().isClosing();
	      connFuture.isDone();
	      reList = formateL(ioHandler.getMes());
	    }
	    catch (Exception e) {
	      if ((e.getCause() instanceof ConnectException)) {
	        try {
	          if (connFuture.isConnected()) {
	            connFuture.getSession().closeNow();
	          }
	        }
	        catch (RuntimeIoException localRuntimeIoException)
	        {
	        	e.printStackTrace();
	        }
	      }
	      throw e;
	    }
		
		return reList;
	}
	
	public List<Map<String, Object>> formateL(String str){
//		System.out.println(str);
		
		String[] strs =  str.split("\n");
		if (strs.length<=2) {
			return null;
		}
		List<String> listStrs = Arrays.asList(strs);
		List<String> listStr = new ArrayList<String>(listStrs);
		int size = listStr.size();
		listStr.remove(size-1);
		listStr.remove(0);
		List<Map<String, Object>> reList = new ArrayList<Map<String,Object>>();
//		System.out.println(str);
		for (String string : listStr) {
			string = "{"+string+"}";
			string = string.replace(" ",",");
			JSONObject jsonObject = JSONObject.fromObject(string);
			String time = jsonObject.getString("time").replace(",", " ");
			String id = jsonObject.getString("id");
			String name = jsonObject.getString("name");
			String workcode = jsonObject.getString("workcode");
			String status = jsonObject.getString("status");
			String cardSrc = jsonObject.getString("card_src");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", time);
			map.put("id", id);
			map.put("name", name);
			map.put("workcode", workcode);
			map.put("status", status);
			map.put("cardSrc", cardSrc);
			reList.add(map);
		}
		return reList;
	}
	
	
	public static void main(String[] args) {
		ClockServiceImpl clockServiceImpl = new ClockServiceImpl();
		List<Map<String, Object>> list = clockServiceImpl.getClock("", "");
		if (list!=null&&list.size()>0) {
			for (Map<String, Object> map : list) {
				for (Map.Entry<String, Object> en : map.entrySet()) {
					System.out.print(en.getKey()+":"+en.getValue()+"<>");
				}
				System.out.println();
			}
		}
		
	}
	
	
}
