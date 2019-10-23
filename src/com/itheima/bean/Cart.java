package com.itheima.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车实体
 */
public class Cart {

	//购物项的集合  (方便第二个案例移除)
	private Map<String, CartItem> cartItemMap = new HashMap<String, CartItem>();
	//总金额
	private double total;

	public Collection<CartItem> getValues(){//--去掉get-->Values--首字母小写-->values  ${cart.values}
		//获得cartItemMap里面所有的值
		Collection<CartItem> values = cartItemMap.values();
		return values;
	}

	/**
	 * 添加到购物车
	 * @param cartItem
	 */
	public void addToCart(CartItem cartItem){
		//a.获得当前cartItem对应的pid
		String pid = cartItem.getProduct().getPid();
		//b判断购物车里面之前是否存在了
		if(cartItemMap.containsKey(pid)){
			//c.如果存在
			//获得购物车里面之前的老的购物项
			CartItem oldItem = cartItemMap.get(pid);
			//数量增加
			oldItem.setCount(oldItem.getCount()+cartItem.getCount());
			//总金额增加
			total+= cartItem.getSubtotal();
		}else{
			//d.如果不存在,
			//直接向cartItemMap里面添加购物项
			cartItemMap.put(pid, cartItem);
			//总金额增加
			total+= cartItem.getSubtotal();
		}

	}

	/**
	 * 移除购物项
	 * @param pid
	 */
	public void removeFromCart(String pid){
		//map移除pid对应的购物项
		CartItem removeCartItem = cartItemMap.remove(pid);
		//总金额-当前移除的购物项的小计
		total -= removeCartItem.getSubtotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart(){
		//清空map
		cartItemMap.clear();
		//总金额=0.0
		total = 0.0;
	}


	public Map<String, CartItem> getCartItemMap() {
		return cartItemMap;
	}
	public void setCartItemMap(Map<String, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}



}
