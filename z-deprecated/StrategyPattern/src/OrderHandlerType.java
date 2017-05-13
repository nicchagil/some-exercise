import handler.GoodsOrderHandler;
import handler.TelephoneChargeOrderHandler;

public enum OrderHandlerType {
	
	GOODS_ORDER(1, GoodsOrderHandler.class),
	TELEPHONE_CHARGE_ORDER(2, TelephoneChargeOrderHandler.class);
	
	private Integer orderType = null; // 数据库中订单类型的值
	private Class handlerClass = null; // 处理器的类

	private OrderHandlerType(Integer orderType, Class handlerClass) {
		this.orderType = orderType;
		this.handlerClass = handlerClass;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public Class getHandlerClass() {
		return handlerClass;
	}

}
