package cn.ys.shop.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ys.shop.cart.Cart;
import cn.ys.shop.cart.CartItem;
import cn.ys.shop.user.User;
import cn.ys.shop.utils.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author:ys
 */
@Controller
@RequestMapping("/order")
public class OrderController{

	// 注入OrderService
	@Autowired
	private OrderService orderService;

		/**
	 * 保存订单执行的方法;
	 * @return
	 */
	@RequestMapping(value = "/saveOrder",method = RequestMethod.GET)
	public String saveOrder(HttpServletRequest request,Model model){
		Order order = new Order();
		/****************封装订单的数据*********/
		order.setOrdertime(new Date());
		// 1 未付款   2 已经付款.  3.已经发货   4 已经收货.
		order.setState(1);
		// 有些数据需要从购物车中获取:
		// 获得购物车:
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			model.addAttribute("msg","您还没有购物!请先去购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 订单所属的用户:

		User exitUser = (User) request.getSession().getAttribute("exitUser");
		if(exitUser == null){
			model.addAttribute("msg","您还没有登录!请先去登录!");
			return "msg";
		}
		order.setUser(exitUser);
		/********************封装订单项数据*************/
		// 订单项数据从 购物项的数据获得.
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 清空购物车.
		cart.clearCart();

		// 保存订单:
		Integer oid = orderService.save(order);
		order.setOid(oid);
		model.addAttribute("order",order);

		return "order";
	}

	/**
	 * 在想支付
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/payOrder",method = RequestMethod.POST)
	public String  payOrder(Order order,String pd_FrpId) throws IOException{

		//修改订单
		Order curorder=orderService.findByOid(order.getOid());
		curorder.setAddr(order.getAddr());
		curorder.setName(order.getName());
		curorder.setPhone(order.getPhone());

		orderService.update(curorder);
		//付款
		//定义付款的参数
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://169.254.160.178:8080/shop/order_callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order,
				p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF,
				pa_MP,pd_FrpId , pr_NeedResponse, keyValue);


		StringBuffer sb =
				new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

        return "redirect:"+sb.toString();

	}

	/**
	 * 付款成功后的回调方法
	 * @return
	 */
	@RequestMapping(value = "/callBack",method = RequestMethod.GET)
	public String callBack(Model model,String r6_Order,String r3_Amt){
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态.
		currOrder.setState(2);
		orderService.update(currOrder);

		model.addAttribute("msg","订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
		return "msg";
	}

	/**
	 * 根据用户id查询订单
	 * @return
	 */
	@RequestMapping(value = "/orderList",method = RequestMethod.GET)
	public String orderList(Integer uid,Model model){
		List<Order> oList=	orderService.findByUid(uid);
		//存入值栈
		model.addAttribute("oList",oList);

		return "orderlist";
	}

	/**
	 * 根据订单号查询订单
	 * @return
	 */
	@RequestMapping(value = "/findByOid",method = RequestMethod.GET)
	public String findByOid(Integer oid,Model model){

		Order order=orderService.findByOid(oid);
		model.addAttribute("order",order);
		return "order";
	}
	/**
	 * 后台查询所有订单
	 */
	@RequestMapping(value = "/adminFindAll",method = RequestMethod.GET)
	public String adminFindAll(Integer page,Model model) {
		Page<Order> pageBean = orderService.findByPage(page);
		// 将PageBean的数据保存到页面:
		model.addAttribute("pageBean", pageBean);
		return "admin/order/list";
	}

	/**
	 * 根据订单状态查询订单
	 */
	@RequestMapping(value = "/adminFindByState",method = RequestMethod.GET)
	public String adminFindByState(Integer page,Integer state,Model model){

		Page<Order> pageBean=orderService.findPageByState(page,state);
		model.addAttribute("pageBean", pageBean);
		return "admin/order/list";
	}



}
