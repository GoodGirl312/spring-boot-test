package cn.ys.shop.category;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author :ys
 */
@Controller
@RequestMapping("/category")
public class CategoryController {


	// 注入Service
	@Autowired
	private CategoryService categoryService;
	/**
	 * 后台:查询所有的一级分类的方法:
	 */
	@RequestMapping(value = "/adminFindAll",method = RequestMethod.GET)
	public String adminFindAll(Model model){
		List<Category> cList = categoryService.findAll();

		model.addAttribute("cList",cList);
		return "admin/category/list";
	}

	/**
	 * 后台：添加一级分类
	 * 到添加页面
	 */
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add(){

		return "admin/category/add";
	}
	/**
	 * 后台:保存一级分类:
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(@RequestBody Category category){
		categoryService.save(category);
		//保存完成后重新查询列表
		return "redirect:/category/adminFindAll";
	}


	/**
	 * 后台:删除一级分类：
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String delete(Category category){
		categoryService.delete(category);
		//删除完成后重新查询列表
		return "forward:/category/adminFindAll";
	}

	/**
	 * 后台:编辑一级分类:(查询一级分类)
	 * 到修改界面
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Category category,Model model){
		category = categoryService.findByCid(category.getCid());
		model.addAttribute("category",category);
		return "admin/category/edit";
	}

	/**
	 * 后台:修改一级分类:
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(Category category){
		categoryService.update(category);
		//修改完成后重新查询列表
		return "redirect:/category/adminFindAll";
	}

}
