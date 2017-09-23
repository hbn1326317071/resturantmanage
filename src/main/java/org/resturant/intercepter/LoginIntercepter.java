package org.resturant.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginIntercepter implements HandlerInterceptor {
		
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入拦截器");
		//在handler执行之前处理
		//判断用户是否登陆
		//1.从cookie中取token
//		String  token=CookieUtils.getCookieValue(request,"TT_TOKEN");
		//2.根据token换取用户信息sso系统的接口
		
		
		//取到用户信息，放行
//		TbUser user=userService.getUserByToken(token);
//		if(null==user){
//			//取不到用户信息，就要跳转到登陆页面，把用户请求的url作为参数传递登陆页面
//			response.sendRedirect(userService.SSO_BASE_URL+userService.SSO_PAGE_LOGIN+"?redirect="+request.getRequestURL());;
//			return false;
//		}
		//把用户信息放入request
//		request.setAttribute("user",user);
		return true;
		//返回值决定handler是否执行，如果为true执行，false
		//不执行
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//在handler执行之后处理，返回modelview之前
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//返回modelview之后
		//响应用户之后
		
	}

}
