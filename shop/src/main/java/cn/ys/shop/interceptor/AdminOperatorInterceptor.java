package cn.ys.shop.interceptor;

import cn.ys.shop.adminuser.AdminUser;
import cn.ys.shop.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :ys
 */
public class AdminOperatorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

      AdminUser user= (AdminUser) request.getSession().getAttribute("existAdminUser");
       if(user==null){

          request.getSession().setAttribute("error",
                  "还没登陆就想操作？请先登录！");
          response.sendRedirect("/adminUser/admin");
          return false;
      }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
