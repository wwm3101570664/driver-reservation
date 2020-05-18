package com.briup.apps.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.briup.apps.bean.Privilege;
import com.briup.apps.service.IPrivilegeService;
import com.briup.apps.utils.JwtTokenUtil;
import com.briup.apps.utils.PermissionException;
import com.briup.apps.utils.UnAuthorizedException;

/**
 * @program: cms_jd1911
 * @description:
 * @author: charles
 * @create: 2019-11-18 21:02
 **/
//权限控制类
public class JwtInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IPrivilegeService privilegeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是options请求，直接返回true，不进行拦截
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 获取请求头信息authorization信息
        //final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        //此处请求头中没有设置成authorization字段，暂时通过X-Token来获取token
        final String token = request.getHeader("X-Token");
        System.out.println("token:"+token);
        if(StringUtils.isEmpty(token)){
            throw new UnAuthorizedException("用户还未登录");
        }
        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, JwtTokenUtil.base64Secret);

        // 验证权限，通过token获取用户角色id，通过用户角色id获取权限，这里可以使用redis将用户信息维护在缓存中，减少与数据库交互次数
        //long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
        int roleId = Integer.parseInt(JwtTokenUtil.getRoleId(token,JwtTokenUtil.base64Secret));
//        System.out.println("in----------"+roleId);
        this.auth(roleId,request.getServletPath());

        return true;
    }

    // 判断权限
    private boolean auth(int roleId,String path){
        // article/findAll
        // 查询出该用户的所有权限
    	List<Privilege> privileges = privilegeService.findByRoleId(roleId);
    	for (Privilege p : privileges) {
    		// 匹配
//    			System.out.println(p.getPath()+"="+path);
    			if(p.getPath().matches(path)){
    				return true;
              }
		}
    	
        throw new PermissionException("权限不足");
    }
}
