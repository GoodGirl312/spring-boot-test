package cn.ys.shop.user;


import cn.ys.shop.order.Order;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author
 */
@Entity
@Table(name="user")
public class User implements Serializable{

	@Id
	@GeneratedValue
	private Integer uid;
	@NotNull(message = "用户名不能为空！")
	private String username;
	@NotNull
	@Size(min = 6,max = 12,message = "密码长度要在6-12位之间!")
	private String password;

	private String name;
	@Email(message = "邮箱格式不正确！")
	private String email;
	@Size(min=11,max=11,message = "联系号码有误！")
	private String phone;
	private String addr;
	@NotNull(message = "请选择性别！")
	private String sex;
	private Integer state;
	private String code;


	@OneToMany(targetEntity=Order.class,cascade={CascadeType.REMOVE},orphanRemoval=true)
	@JoinColumn(name = "uid")
	private Set<Order> order;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
