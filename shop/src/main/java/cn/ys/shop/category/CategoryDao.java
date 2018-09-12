package cn.ys.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ys
 */
@Repository
public interface CategoryDao extends JpaRepository<Category,Long>{
    /**
     * 通过id查询一级分类
     * @param cid
     * @return
     */
      public Category findByCid(Integer cid);

}
