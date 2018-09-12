package cn.ys.shop.category;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.ys.shop.categorysecond.CategorySecond;

import javax.persistence.*;

/**
 * @author
 */
@Entity
@Table(name = "category")
public class Category implements Serializable{

	@Id
	@GeneratedValue
	@Column
	private Integer cid;
	@Column
	private String cname;
	/**
	 * 二级分类的集合
	 */
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="cid")
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category() {
	}

}
