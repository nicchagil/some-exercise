import handler.OrderHandler;

public class OrderHandlerFacade {

	public static void handle(Integer orderType, Integer orderId) {
		if (orderType == null || orderId == null) {
			throw new RuntimeException();
		}

		/* 遍历枚举匹配对应的策略 */
		OrderHandlerType[] orderHandlerTypes = OrderHandlerType.values();
		for (OrderHandlerType orderHandlerType : orderHandlerTypes) {
			if (orderHandlerType.getOrderType().equals(orderType)) {
				try {
					((OrderHandler)orderHandlerType.getHandlerClass().newInstance()).handle(orderId); // 调用对应的策略
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
