package cn.ys.shop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:ys
 */
@Repository
public interface UserDao extends JpaRepository<User,String> {

	/**
	 * 根据激活码查询
	 * @param code
	 * @return
	 */
	public User findByCode(String code);


	/**
	 * 根据用户名和密码查询
	 * @param username
	 * @param password
	 * @return
	 */
	public User findByUsernameAndPassword(String username,String password);

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);



    public User findUserByUid(Integer uid);
}
