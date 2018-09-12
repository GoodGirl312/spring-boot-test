package cn.ys.shop.product;

import java.util.List;

import cn.ys.shop.category.CategoryDao;
import cn.ys.shop.categorysecond.CategorySecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author
 */
@Transactional
@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	public List<Product> findHot(Integer m) {
		return productDao.findFirst10ByIsHotEquals(m);
	}
	public List<Product> findNew() {
		Sort sort=new Sort(Sort.Direction.DESC,"pdate");


		return productDao.findAll(sort).subList(0,10);
	}


	/**
	 * 通过一级分类id查询所有商品并封装到page
	 * @param cid
	 * @param page
	 * @return
	 */
	public Page<Product> findByPage(final Integer cid, Integer page) {

			Page<Product> pageBean=productDao.findAll(new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
										 CriteriaBuilder cb) {
				return cb.equal(root.get("categorySecond").get("category").get("cid"),cid);
			}
		}, new PageRequest(page,12));

		return pageBean;
	}

	/**
	 * 查询所有商品
	 * @param page
	 * @return
	 */
	public Page<Product> findByPage(Integer page){

		Page<Product> pageBean=productDao.findAll(new PageRequest(page,10));
		return pageBean;
	}


	public Product findByPid(Integer pid) {

		return productDao.findByPid(pid);
	}

	public Page<Product> findByCsid(Integer csid, Integer page) {

		Page<Product> pageBean=productDao.findAll(new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
										 CriteriaBuilder cb) {
				return cb.equal(root.get("categorySecond").get("csid"),csid);
			}
		}, new PageRequest(page,12));

		return pageBean;

	}


	public void delete(Product product){
		productDao.delete(product);
	}

	public void update(Product product){
		productDao.save(product);
	}
	public void save(Product product){
		productDao.save(product);
	}
}
