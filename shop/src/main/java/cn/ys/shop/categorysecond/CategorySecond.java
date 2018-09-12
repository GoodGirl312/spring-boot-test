package cn.ys.shop.categorysecond;

import java.util.HashSet;
import java.util.Set;

import cn.ys.shop.category.Category;
import cn.ys.shop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author ys
 */
@Entity
@Table(name= "categorySecond")
public class CategorySecond {
	@Id
	@GeneratedValue
	private Integer csid;

	private String csname;
	/**
	 * 二级分类所属的一级分类:
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private Category category;
	/**
	 * 关联的商品的集合
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "csid")
	private Set<Product> products = new HashSet<Product>();

	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}


}

