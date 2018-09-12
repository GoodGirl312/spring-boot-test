package cn.ys.shop.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :ys
 */
public class Cart {

	/**
	 * 购物车存放多个购物项
	 * key用商品id,购物项为value
	 */
	private Map<Integer,CartItem> map=new HashMap<Integer, CartItem>();

	/**
	 * 提供一个获取map中value的方法
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}

	/**
	 * 总计
	 */
	private Double total=0d;
	public Double getTotal() {
		return total;
	}

	//
	//

	/**
	 * 提供三个方法
	 * 1.将购物项添加到购物车
	 * @param item
	 */
	public  void addCart(CartItem item){

		//判断购物车有没有该购物项，如果有，直接数量加1，否则直接加入到购物车
		Integer pid=item.getProduct().getPid();
		if(map.containsKey(pid)){
			//如果map中有购物项，数量加1 ，小计变化，总计变化
			CartItem _cartItem=map.get(pid);
			//取出原来购物项中的数量+要添加的购物项中的数量
			Integer count=_cartItem.getCount()+item.getCount();
			//小计变化
			_cartItem.setCount(count);


		}else{
			map.put(pid, item);
		}

		total+=item.getSubtotal();
	}


	/**
	 * 2.将购物项从购物车移除
	 * @param pid
	 */
	public  void deleteCart(Integer pid){
		//移除商品
		CartItem item=map.remove(pid);
			//总计减少
		total=total-item.getSubtotal();

	}

	/**
	 * 3.清空购物车
	 */
	public void clearCart(){
		//清空map
		map.clear();
		//总计设为0
		total=0d;
	}

}
