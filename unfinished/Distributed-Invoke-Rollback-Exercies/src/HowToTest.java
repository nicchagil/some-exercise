import java.util.logging.Logger;

import com.nicchgail.rollbackrecord.biz.ConcreteDistributedInvoke;
import com.nicchgail.rollbackrecord.biz.TransferRollbackVO;
import com.nicchgail.rollbackrecord.biz.TransferVO;

public class HowToTest {
	
	private static Logger LOGGER = Logger.getLogger("HowToTest");

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			try {
				simulation();
			} catch (Exception e) {
				// 测试用途，不打印任何信息
			}
			
			LOGGER.info("");
		}
	}
	
	public static void simulation() {
		ConcreteDistributedInvoke cdi = new ConcreteDistributedInvoke();
		
		/* 假设账号1向账号2转账200元，现调用接口给账号2加200元，如果失败，则需要给账号1已扣的200元恢复回来 */
		TransferVO transferVO = new TransferVO();
		transferVO.setId(2);
		transferVO.setAmount(200);
		
		TransferRollbackVO transferRollbackVO = new TransferRollbackVO();
		transferRollbackVO.setId(1);
		transferRollbackVO.setAmount(200);
		
		cdi.invoke(transferVO, transferRollbackVO);
	}
	
	

}
