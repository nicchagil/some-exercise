package handler;

public class GoodsOrderHandler implements OrderHandler {

	@Override
	public void handle(Integer id) {
		System.out.println("商品订单");
	}

}
