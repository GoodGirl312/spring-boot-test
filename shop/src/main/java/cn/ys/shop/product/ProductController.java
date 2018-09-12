package cn.ys.shop.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import cn.ys.shop.category.Category;
import cn.ys.shop.category.CategoryService;
import cn.ys.shop.categorysecond.CategorySecond;
import cn.ys.shop.categorysecond.CategorySecondService;
import cn.ys.shop.utils.UUidUtils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ys
 */

@RequestMapping("/product")
@Controller
public class ProductController{

	@Autowired
	private ProductService productService;
	//注入一级分类的service
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategorySecondService categorySecondService;
	// 查询商品的方法:
	@RequestMapping(value = "/findByCid",method = RequestMethod.GET)
	public String findByCid(Model model,Integer cid,Integer page){
		// 查询分类:
		// 查询所有一级分类:
		List<Category> categorylist=categoryService.findAll();
		model.addAttribute("categorylist", categorylist);

		//查询商品
		Page<Product> pageBean=productService.findByPage(cid,page);
		model.addAttribute("pageBean",pageBean);
		model.addAttribute("cid",cid);
		return "list";
	}

	//到详情页面
	@RequestMapping(value = "/findByPid",method = RequestMethod.GET)
	public String findByPid(Model model,Integer pid){

		//查询一级分类中的信息
		List<Category> categorylist=categoryService.findAll();
		model.addAttribute("categorylist", categorylist);
		//根据pid查询
		Product product=productService.findByPid(pid);
		model.addAttribute("product",product);
		return "desc";
	}

	@RequestMapping(value = "/findByCsid",method = RequestMethod.GET)
	public String findByCsid(Model model,Integer csid,Integer page){
		//查询一级分类中的信息
		List<Category> categorylist=categoryService.findAll();
		model.addAttribute("categorylist", categorylist);
		//查询商品
		Page<Product> pageBean=productService.findByCsid(csid,page);

		model.addAttribute("pageBean",pageBean);
		model.addAttribute("csid",csid);
		return "cslist";
	}


	/**
	 * 后台:查询所有商品的方法
	 */
	@RequestMapping(value = "/adminFindAll",method = RequestMethod.GET)
	public String adminFindAll(Integer page,Model model){
		 Page<Product> pageBean = productService.findByPage(page);

		 model.addAttribute("pageBean",pageBean);
		return "admin/product/list";
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping(value = "/addPage",method = RequestMethod.GET)
	public String addPage(Model model){
		// 查询所有的二级分类 :
		List<CategorySecond> csList = categorySecondService.findAll();
		model.addAttribute("csList",csList);
		return "admin/product/add";
	}

	/**
	 * 保存商品:同时上传图片
	 * @throws IOException
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(MultipartFile upload,HttpServletRequest request,
					   @RequestBody Product product) throws IOException {
		Integer csid=product.getCategorySecond().getCsid();
		// 文件上传的操作:
			// 获得上传的路径:
		String path = request.getServletContext().getRealPath("/products");
		String uploadFileName = upload.getOriginalFilename();
		if(uploadFileName.hashCode()!=0){
			//说明文件不为空
			String newName=UUidUtils.getUUID()+
					uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String realPath = path+"/"+csid+"/"+newName;
			File diskFile = new File(realPath);
			if(!diskFile.exists()){
				//先得到文件的上级目录，并创建上级目录，在创建文件
				diskFile.getParentFile().mkdirs();
				try {
					//创建文件
					diskFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 文件上传:
			upload.transferTo(diskFile);
			// 设置图片上传路径:
			product.setImage("products/"+csid+"/"+newName);

		}

		// 保存到数据库:
		// 设置时间:
		product.setPdate(new Date());

		// 调用Service保存商品:
		productService.save(product);

		return "redirect:/product/adminFindAll?page=0";
	}
	/**
	 * 商品删除
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String  delete(Integer pid){
		Product p=productService.findByPid(pid);
		productService.delete(p);

		return "redirect:/product/adminFindAll?page=1";
	}
	/**
	 * 商品修改之到修改界面
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Product product,Model model){

		//修改前查出商品
		 Product product1=productService.findByPid(product.getPid());
		//查出商品所属的二级分类
		List<CategorySecond> csList=categorySecondService.findAll();

        model.addAttribute("product",product1);
        model.addAttribute("csList",csList);

		return "admin/product/edit";
	}
	/**
	 * 商品修改之更新
	 * @throws IOException
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(Product product, MultipartFile upload,
						 HttpServletRequest request) throws IOException{

		Integer csid=product.getCategorySecond().getCsid();
		//判断图片是否更换（！）
		String uploadFileName = upload.getOriginalFilename();
		if(uploadFileName.hashCode()!=0){
			//说明图片已经更改
			String path = request.getServletContext().getRealPath("/products");
			String newName=UUidUtils.getUUID()
					+uploadFileName.substring(uploadFileName.lastIndexOf("."));
			String realPath = path+"/"+csid+"/"+newName;
			File diskFile = new File(realPath);
			if(!diskFile.exists()){
				diskFile.getParentFile().mkdirs();

				diskFile.createNewFile();
			}
			// 文件上传:
			upload.transferTo(diskFile);
			// 保存到数据库:
			// 设置图片上传路径:
			product.setImage("products/"+csid+"/"+newName);

		}
		// 调用Service保存商品:
		productService.update(product);

		 return "redirect:/product/adminFindAll?page=0";
	}


}
