package cn.ys.shop.adminuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yuanshuo
 */
@Controller
@RequestMapping("/adminUser")
public class AdminUserController{

	// 注入Service
	@Autowired
	private AdminUserService adminUserService ;


	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String toLogin(){

		return "admin/index";
	}

	/**
	 * 后台登陆的方法:
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(Model model, AdminUser adminUser, HttpServletRequest request){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			// 登陆失败
			model.addAttribute("ActionError","用户名或密码错误!");
			return "admin/index";
		}else{
			// 登陆成功
			request.getSession().setAttribute("existAdminUser", existAdminUser);
			return "admin/home";
		}
	}

   @RequestMapping(value = "/top",method = RequestMethod.GET)
	public String top(){

		return "admin/top";
   }
	@RequestMapping(value = "/left",method = RequestMethod.GET)
	public String left(){

		return "admin/left";
	}
	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public String welcome(){

		return "admin/welcome";
	}
	@RequestMapping(value = "/bottom",method = RequestMethod.GET)
	public String bottom(){

		return "admin/bottom";
	}
 

}
