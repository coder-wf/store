package com.itheima.constant;

public class Constants {
	
	
	/**
	 * 用户激活状态: 1激活 0未激活
	 */
	public static final Integer USER_ACTIVED = 1;
	public static final Integer USER_NOT_ACTIVE = 0;

	/**
	 * 类别的存到redis里面的key
	 */
	public static final String STORE_CATEGORY_KEY = "store_category_key";


	/**
	 * 商品是否热门  1 热门  ;0 不是热门
	 */
	public static final Integer PRODUCT_IS_HOT = 1;
	public static final Integer PRODUCT_IS_NOT_HOT = 0;

	/**
	 * 当前类别下分页查询商品的一页显示数量
	 */
	public static final int PRODUCT_CUR_SIZE = 12;

	/**
	 * 订单状态  0:未付款 UN_PAY	1:已付款 PAYED	2:已发货 DELIVERED	3.已完成FINISHED
	 */
	public static final Integer WEI_FU_KUAN = 0;
	public static final Integer YI_FU_KUAN = 1;
	public static final Integer YI_FA_HUO = 2;
	public static final Integer YI_WAN_CHENG = 3;


	/**
	 * 当前用户订单一页显示的数量
	 */
	public static final int ORDER_CUR_SIZE = 2;

}
