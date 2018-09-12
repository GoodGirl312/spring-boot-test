package cn.ys.shop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public Integer save(Order order) {
		Order o = orderDao.save(order);
			return o.getOid();

	}
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	public void update(Order curorder) {
		orderDao.save(curorder);
	}
	public List<Order> findByUid(Integer uid) {
		return orderDao.findByUid(uid);
	}


    public Page<Order> findByPage(Integer page) {

		return orderDao.findAll(new PageRequest(page,10));
    }

	public Page<Order> findPageByState(Integer page, Integer state) {

		return  orderDao.findAll(new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root,
										 CriteriaQuery<?> Query, CriteriaBuilder cb) {

				return cb.equal(root.get("state"),state);
			}
		},new PageRequest(page,10));
	}
}
