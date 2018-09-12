package cn.ys.shop.cart;

import javax.servlet.http.HttpServletRequest;
import cn.ys.shop.product.Product;
import cn.ys.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :ys
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	/**
	 * 注入productService
	 */
	@Autowired
	private ProductService productService;

	/**
	 * 获取car的方法
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCart",method = RequestMethod.GET)
	public Cart getCart(HttpServletRequest request){

		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		return cart;
	}


	/**
	 * 添加到购物车
	 * @return
	 */
	@RequestMapping(value = "/addCart",method = RequestMethod.POST)
	public String addCart(HttpServletRequest request,Integer pid,Integer count){
		//根据id查询商品
		Product product = productService.findByPid(pid);
		//创建购物项
		CartItem item=new CartItem();
		item.setCount(count);
		item.setProduct(product);

		//从session中获取购物车，如果没有，创建一个并添加到session
		Cart cart= getCart(request);

		cart.addCart(item);

		return "cart";
	}


	/**
	 * 清空购物车
	 * @return
	 */
	@RequestMapping(value = "/clearCart",method = RequestMethod.GET)
	public String clearCart(HttpServletRequest request){
		//从session中获取session，如果没有，创建一个并添加到session
		Cart cart= getCart(request);
		cart.clearCart();


		return "cart";
	}

	/**
	 * 删除商品
	 * @return
	 */
	@RequestMapping(value ="/removeFromCart",method = RequestMethod.GET)
	public String delete(HttpServletRequest request,Integer pid){
		//从session中获取session，如果没有，创建一个并添加到session
		Cart cart= getCart(request);
		cart.deleteCart(pid);

		return "cart";
	}

	/**
	 * 显示购物车
	 * @return
	 */
	@RequestMapping(value = "/showCart",method = RequestMethod.GET)
	public String showCart(HttpServletRequest request,Model model){

		Cart cart=getCart(request);
		model.addAttribute("cart",cart);
		return "cart";
	}
}
