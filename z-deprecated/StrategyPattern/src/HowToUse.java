import org.junit.Test;

public class HowToUse {

	@Test
	public void tc1() {
		OrderHandlerFacade.handle(OrderHandlerType.GOODS_ORDER.getOrderType(), 1);
	}
	
	@Test
	public void tc2() {
		OrderHandlerFacade.handle(OrderHandlerType.TELEPHONE_CHARGE_ORDER.getOrderType(), 1);
	}

}
