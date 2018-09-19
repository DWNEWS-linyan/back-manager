package com.ly.utils;

import java.net.ConnectException;
import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import Com.FirstSolver.Splash.FaceIdProtocolCodecFactory;

/**
* @ClassName: Agga
* @Description: 
* @author linyan
* @date 2017年11月23日 下午2:40:35
*
*/
public class  ConnectorIoHanVang{

	
	public static void maissn(String[] args) {
		System.out.println(444);
	}
	
	public static void sendMes(String sdate,String edate) {
		IoConnector connector;
		 IoHandler ioHandler = new MyIoHandlerAdapter();
		connector = new NioSocketConnector();
	    connector.getSessionConfig().setReaderIdleTime(60);

	    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new FaceIdProtocolCodecFactory("GBK", false, false)));
	    connector.setHandler(ioHandler);
		
	    InetSocketAddress inetSocketAddress = new InetSocketAddress("172.23.255.4", 9922);
	    ConnectFuture connFuture = connector.connect(inetSocketAddress);
	    try
	    {
//	    	String mes = "GetPictureName(time=\"2017-12-06 13:00:00\" type=\"face\") ";
	    	String mes = "GetRecord(start_time=\""+sdate+"\" end_time=\""+edate+"\")";
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
		System.out.println("wancheng");
		
	}
	
}
