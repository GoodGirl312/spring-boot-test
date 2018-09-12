package cn.ys.shop.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao extends JpaRepository<Product,Long>,
		JpaSpecificationExecutor<Product>{
	/**
	 *  DAO层查询热门商品只显示10个
	 * @param m
	 * @return
	 */
    public List<Product> findFirst10ByIsHotEquals(Integer m);

	@Override
	 List<Product> findAll(Sort sort);


	 Product findByPid(Integer pid);


}
