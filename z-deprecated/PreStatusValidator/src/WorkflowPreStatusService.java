import org.junit.Assert;
import org.junit.Test;

public class WorkflowPreStatusService {
	
	/**
	 * 判断前置状态是否正确
	 * @param updStatus 设置状态
	 * @param preStatus 前置状态
	 * @return 是否正确
	 */
	public boolean validate(Status updStatus, Status preStatus) {
		if (WorkflowPrestatusValidator.statusPreStatusMapping.containsKey(updStatus)) {
			return WorkflowPrestatusValidator.validatePreStatus(preStatus, updStatus);
		}
		
		return false;
	}
	
	@Test
	public void tc1() {
		Assert.assertTrue(WorkflowPrestatusValidator.validatePreStatus(Status.DELIVERY, Status.STOCK));
	}
	
	@Test
	public void tc2() {
		Assert.assertTrue(WorkflowPrestatusValidator.validatePreStatus(Status.CANCEL, Status.ORDER));
	}

}
