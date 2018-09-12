package cn.ys.shop.product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.ys.shop.categorysecond.CategorySecond;
import cn.ys.shop.comment.Comment;

import javax.persistence.*;


/**
 * @author:ys
 */
@Entity
@Table(name ="product")
public class Product {
	@Id
	@GeneratedValue
	@Column
	private Integer pid;
	@Column
	private String pname;
	@Column
	private Double market_price;
	@Column
	private Double shop_price;
	@Column
	private String image;
	@Column
	private Integer num;

	@Column
	private String pdesc;
	@Column
	private Integer isHot;
	@Column
	private Date pdate;
	/***
	 * 所属的二级分类的对象:
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="csid")
	private CategorySecond categorySecond;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="pid")
	private Set<Comment> comments=new HashSet<Comment>();

	public Product() {

	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public CategorySecond getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}


}

