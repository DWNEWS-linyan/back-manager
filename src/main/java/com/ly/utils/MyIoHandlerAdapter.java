package com.ly.utils;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
* @ClassName: AGGasdf
* @Description: 
* @author linyan
* @date 2017年11月23日 下午2:42:04
*
*/
public class MyIoHandlerAdapter extends IoHandlerAdapter{

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		Meg.mes = String.valueOf(message);
		session.closeOnFlush();
	}

}
