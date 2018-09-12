package cn.ys.shop.categorysecond;

import cn.ys.shop.category.Category;
import cn.ys.shop.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ys
 */
@Controller
@RequestMapping("/categorySecond")
public class CategorySecondController{

	// 注入Service
	@Autowired
	private CategorySecondService categorySecondService;
	// 注入一级分类的Service
	@Autowired
	private CategoryService categoryService;

	/**
	 * 二级分类管理:查询所有二级分类(带有分页.)
	 */
	@RequestMapping(value = "/adminFindAll",method = RequestMethod.GET)
	public String adminFindAll(Integer page,Model model){
		// 调用Service完成查询:
		Page<CategorySecond> pageBean=categorySecondService.findByPage(page);

		model.addAttribute("pageBean", pageBean);
		return "admin/categorysecond/list";
	}

	/**
	 * 二级分类管理:跳转到添加页面的方法.
	 */
	@RequestMapping(value = "/addPage",method = RequestMethod.GET)
	public String addPage(Model model){
		// 查询一级分类的列表:
		List<Category> cList = categoryService.findAll();
		model.addAttribute("cList", cList);
		return "admin/categorysecond/add";
	}
	/**
	 * 二级分类的保存:
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(@RequestBody CategorySecond categorySecond){
    	// 调用Service保存
		categorySecondService.save(categorySecond);
		System.out.println("保存成功！");
		//保存后重新到列表页面
		return "redirect:/categorySecond/adminFindAll?page=1";

	}


	/**
	 * 二级分类删除
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String delete(Integer csid){
		CategorySecond categorySecond=categorySecondService.findByCsid(csid);
		categorySecondService.delete(categorySecond);
		//删除完成后重新查询列表
		return "forward:/categorysecond/adminFindAll";
	}

	/**
	 * 二级分类的修改页面
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(CategorySecond categorySecond,Model model){

		CategorySecond cs=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();

		model.addAttribute("categorysecond",cs);
		model.addAttribute("cList",cList);
		return "admin/categorysecond/edit";
	}

	/**
	 * 保存修改
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(CategorySecond categorySecond){
		categorySecondService.update(categorySecond);
		return "redirect:/categorySecond/adminFindAll?page=1";
	}

}
