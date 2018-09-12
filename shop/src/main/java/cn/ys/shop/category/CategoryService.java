package cn.ys.shop.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryService {
	// 注入DAO
	@Autowired
	private CategoryDao categoryDao;
	/**
	 * 业务层查询所有的一级分类的方法
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}


	/**
	 * 业务层保存一级分类
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}


	/**
	 * 业务层删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 业务层查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 *业务层修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.saveAndFlush(category);
	}



}
