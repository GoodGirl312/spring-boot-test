package cn.ys.shop.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ys.shop.utils.MailUtils;
import cn.ys.shop.utils.UUidUtils;

import java.util.List;

/**
 * 业务层
 * @author Administrator
 *
 */
@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 注册用户并发送激活邮件
	 * @param user
	 */
	public void regist(User user) {
		//0未激活，1激活
		user.setState(0);
		String code=UUidUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(),code);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}


	public void update(User exitUser) {
		userDao.save(exitUser);
	}

	public User login(User user) {
		return userDao.findByUsernameAndPassword(
				user.getUsername(),user.getPassword());
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

    public List<User> findAll() {
	   return 	userDao.findAll();
    }

	public User findByUid(Integer uid) {
		return userDao.findUserByUid(uid);
	}

	public void detele(User u) {
		userDao.delete(u);
	}

	public Page<User> findAll(Integer page) {
		return 	userDao.findAll(new PageRequest(page,2));
	}
}
