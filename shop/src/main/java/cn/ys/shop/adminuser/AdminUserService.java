package cn.ys.shop.adminuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	public AdminUser login(AdminUser u) {
		return adminUserDao.findByUsernameAndPassword(u.getUsername(),u.getPassword());
	}

}
