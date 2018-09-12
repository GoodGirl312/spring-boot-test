package cn.ys.shop.order;


import cn.ys.shop.user.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oid", length = 50)
	private Integer oid;
	@Column(name = "total")
	private Double total;
	@Column(name = "ordertime")
	private Date ordertime;
	@Column(name = "state",length = 2)
	private Integer state;
	@Column(name = "addr",length = 50)
	private String addr;
	@Column(name = "phone",length = 11)
	private String phone;
	@Column(name = "name",length = 50)
	private String name;
	/**
	 * 订单的所属的用户
	 */
    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;
	/**
	 * 放的是订单项的集合
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="oid" )
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return state;
	}



	public Order() {}
}
