package com.ly.scheduled;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.service.crawler.ICMBService;
import com.ly.utils.SendSMS;

/**
* @ClassName: CMB
* @Description: 
* @author linyan
* @date 2018年1月2日 上午11:18:03
*
*/
@Component
public class CMB {

	@Autowired 
	private ICMBService cmbService;
	
	public static Integer num = 2 ;
	
	public static Integer falge = 0;
	
	public static Double mn = 653.5 ; 
	
	public static String moble = "13269995062";
	
	private Integer tongNum = 0 ;
	
//	@Scheduled(cron = "0/10 * * * * ? ")
//	@Scheduled(cron = "0 1,31 8-22 * * ? ")
	public void cmb(){
//		System.out.println("============");
		if (falge==1) {
			Double resl = cmbService.getDollarRate(null);
			System.out.println(resl);
			System.out.println(num);
			System.out.println(tongNum);
			System.out.println(mn);
			System.out.println(moble);
			try {
				if (resl>mn&&tongNum<=num) {
					Map<String, String> map = SendSMS.smsMt(moble,"【Mr.Lin】您的现汇买入价为"+resl );
					if (map!=null&&map.get("code")!=null&&!map.get("code").equals("")&&map.get("code").equals("0")) {
						tongNum++ ;
					}
					System.out.println(map.get("code"));
					System.out.println(map.get("msg"));
				}else if (tongNum>num) {
					falge = 2 ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
