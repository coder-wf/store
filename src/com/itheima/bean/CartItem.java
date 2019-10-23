package com.itheima.bean;


/**
 * 购物项实体
 */
public class CartItem {
	
	//商品对象 
	private Product product;
	//购买数量 
	private int count;
	//小计(计算的,不需要set)
	private double subtotal;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//计算
	public double getSubtotal() {
		return product.getShop_price()*count;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	

}
