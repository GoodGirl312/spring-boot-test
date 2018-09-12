package cn.ys.shop.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDao extends JpaRepository<Order,Long>,JpaSpecificationExecutor<Order>{
	/**
	 * 通过订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid);

    @Query("select o from Order o where o.user.uid=?1 order by ordertime desc")
	 List<Order> findByUid(Integer uid);



}
