package handler;

public class TelephoneChargeOrderHandler implements OrderHandler {

	@Override
	public void handle(Integer id) {
		System.out.println("话费订单");
	}

}
