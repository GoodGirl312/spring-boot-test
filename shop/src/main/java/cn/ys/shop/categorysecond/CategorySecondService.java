package cn.ys.shop.categorysecond;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ys
 */
@Transactional
@Service
public class CategorySecondService {

	/**注入DAO*/
	@Autowired
	private CategorySecondDao categorySecondDao;


	/**
	 * 业务层带有分页查询二级分类
	 * @param
	 */
	public Page<CategorySecond> findByPage(Integer page) {

		Page<CategorySecond> pageBean=categorySecondDao.findAll(new PageRequest(page,10));

		return pageBean;
	}


	/**
	 * 业务层保存二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}


	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}


	public CategorySecond findById(Integer csid) {

		return categorySecondDao.findByCsid(csid);
	}


	public void update(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
}
