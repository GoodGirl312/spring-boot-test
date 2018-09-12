package cn.ys.shop.adminuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface AdminUserDao extends JpaRepository<AdminUser,Long> {

		public AdminUser findByUsernameAndPassword(String username,String password);

}
