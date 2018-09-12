package cn.ys.shop.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
/**
 * @author:ys
 */
@Controller
@RequestMapping("/user")
public class UserController{
	/**
	 * 注入service
	 */
	@Autowired
	private UserService userService;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/registPage",method = RequestMethod.GET)
	public String registPage(){

		return "regist";
	}

	/**
	 * 查看用户名是否已被注册
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
	public void checkUsername(String username,HttpServletResponse response)
			throws IOException{

		User exitUser=userService.findByUsername(username);
		response.setContentType("text/html;charset=utf-8");
		if(exitUser==null){
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else{
			response.getWriter().print("<font color='red'>用户名已存在！</font>");
		}
	}

	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String regist(@Valid @RequestBody User user, Errors errors, String checkCode,
						 HttpSession session, Model model){
		//校验属性
		if (errors.hasErrors()) {
			Map<String, String> err = new HashMap<String, String>();
			List<FieldError> list = errors.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				err.put(error.getField(), error.getDefaultMessage());
				System.out.println(error.getDefaultMessage());
			}

			model.addAttribute("errfields", err);
			return "regist";
		}
		//从session中获取验证码，和当前从页面穿过来的作比较
		String checkcode=(String) session.getAttribute("checkcode");
		if(checkCode!=null&&checkCode.equalsIgnoreCase(checkcode)){
			userService.regist(user);
			model.addAttribute("msg","注册成功，请去邮箱激活...");
			return "msg";
		}else{
			model.addAttribute("msg","验证码不正确！");
			model.addAttribute("user",user);
			return "regist";
		}

	}

	/**
	 * 激活用户
	 * @return
	 */
	@RequestMapping(value = "/active",method = RequestMethod.GET)
	public String active(@RequestBody User user,Model model){
		//根据激活码查询用户
		User exitUser=userService.findByCode(user.getCode());

		if(exitUser!=null){
			//激活状态
			exitUser.setState(1);
			userService.update(exitUser);
			model.addAttribute("msg","激活成功，请登录！！");
			return "login";
		}else{
			model.addAttribute("msg","激活失败，请重新注册！");
			return "regist";
		}


	}

	/**
	 * 到登录页面
	 * @return
	 */
	@RequestMapping(value = "/loginPage",method = RequestMethod.GET)
	public String loginPage(){

		return "login";
	}

	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(String checkCode, User user,Model model){
		//验证码校验
		String checkcode=(String)session.getAttribute("checkcode");


		if(checkCode==null){
			model.addAttribute("login","请输入验证码！");
			return "login";
		}else
		if(!(checkCode.equalsIgnoreCase(checkcode))){
			model.addAttribute("login","验证码错误！");
			return "login";
		}

		User user1=userService.login(user);

		if(user1!=null){
			//登录成功
			session.setAttribute("exitUser", user1);

			return "redirect:/index";

		}else{
			//登录失败
			session.setAttribute("login","用户名或密码错误或没有激活！");
			return "login";
		}

	}



	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/quit",method = RequestMethod.GET)
	public String quit(HttpSession session){
		//销毁session
		session.invalidate();
		return "redirect:/index";
	}

	/**
	 * 查询所有
	 */
	@RequestMapping(value = "/adminFindAll",method = RequestMethod.GET)
	public String findAll(Model model){

		List<User> userList=userService.findAll();
		model.addAttribute("uList",userList);
		return "admin/user/list";
	}
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String delete(Integer uid){
        User u=userService.findByUid(uid);
		userService.detele(u);
		return "forward:/user/adminFindAll";
	}


}
