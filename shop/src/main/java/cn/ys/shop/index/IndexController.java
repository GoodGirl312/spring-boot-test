package cn.ys.shop.index;

import java.util.List;
import cn.ys.shop.category.Category;
import cn.ys.shop.category.CategoryService;
import cn.ys.shop.product.Product;
import cn.ys.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author
 */
@Controller
public class IndexController {

	//注入一级分类的service
	@Autowired
	private CategoryService categoryService;
	//注入商品service
	@Autowired
	private ProductService productService;
	/**
	 * 执行首页访问方法
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(Model model)  {
		//查询所有一级分类
		List<Category> categoryList=categoryService.findAll();

		model.addAttribute("categorylist", categoryList);

		//查询热门商品
		List<Product> hotList=productService.findHot(1 );

		//查询最新商品
		List<Product> newList=productService.findNew();

		model.addAttribute("hotList",hotList);
		model.addAttribute("newList",newList);

		return "index";
	}
}
