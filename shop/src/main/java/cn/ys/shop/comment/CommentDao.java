package cn.ys.shop.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author :yuanshuo
 * @date :2018/3/5
 */
@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {

     Comment findById(Integer id);

}
