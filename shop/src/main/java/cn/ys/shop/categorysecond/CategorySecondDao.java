package cn.ys.shop.categorysecond;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySecondDao extends JpaRepository<CategorySecond,Long>{

    public CategorySecond findByCsid(Integer csid);

}
