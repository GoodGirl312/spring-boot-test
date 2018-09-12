package cn.ys.shop.order;


import cn.ys.shop.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "orderItem")
public class OrderItem {
	@Id
	@GeneratedValue
	private Integer itemid;
	@Column
	private Integer count;
	@Column
	private Double subtotal;

	/**
	 * 订单项包含的商品信息
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	private Product product;
	/**
	 * 订单项所属的订单
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "oid")
	private Order order;

	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
