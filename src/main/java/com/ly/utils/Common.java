package com.ly.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.ly.po.SysRole;
import com.ly.po.SysTree;
import com.ly.po.SysUser;

/**
* @ClassName: Common
* @Description: 
* @author linyan
* @date 2017年9月13日 下午7:55:45
*
*/
public class Common {


	private static Map<String, Object> mapg = null;
	
	/**
	 * @Title: isWindows
	 * @author: linyan
	 * @Description: 判断 是否是 windows
	 * @return 是 windows  返回 true    反之  返回 false
	 */
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: getCurrentUser
	 * @author: linyan
	 * @Description: 得到 当前登录 用户信息    用户对应的  角色   和  权限
	 * @return "user":"SysUser"   "roles":"List<SysRole>"   "prems":"List<SysTree>"
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCurrentUser() {
//		if (mapg!=null) {
//			return mapg;
//		}
		mapg = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
    	if(session != null){
			if(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)!=null){
				PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				Object object = principalCollection.getPrimaryPrincipal();
				Map<String, Object> map = (Map<String, Object>)object;
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					mapg.put(entry.getKey(), entry.getValue());
				}
				return mapg;
			}
		}
    	return null;
	}
	
	public static List<SysRole> getSysRoles(){
		Map<String, Object> map = getCurrentUser();
		if(map != null&&map.containsKey("roles")){
			List<SysRole> list = new ArrayList<SysRole>();
			List<?> list2 = (List<?>)map.get("roles");
			for (Object object : list2) {
				list.add((SysRole)object);
			}
			return list;
		}
		return null;
	}
	
	public static List<SysTree> getSysTrees(){
		Map<String, Object> map = getCurrentUser();
		if(map != null&&map.containsKey("prems")){
			List<SysTree> list = new ArrayList<SysTree>();
			List<?> list2 = (List<?>)map.get("prems");
			for (Object object : list2) {
				list.add((SysTree)object);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * @Title: getUserID
	 * @author: linyan
	 * @Description: 得到  当前 登录用户  的 登录ID
	 * @return
	 */
	public static Integer getUserID(){
		Map<String, Object> map = getCurrentUser();
		if(map != null&&map.containsKey("user")){
			SysUser sysUser = (SysUser)map.get("user");
			return sysUser.getId();
		}
		return null;
	}
	
	
	/**
	 * @Title: getRemoteAddr
	 * @author: linyan
	 * @Description: 获取用户的IP 地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
}
