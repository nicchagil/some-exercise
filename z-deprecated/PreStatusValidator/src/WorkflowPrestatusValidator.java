import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorkflowPrestatusValidator {
	
	public enum StatusPreStatusMapping {
		
		/* 设置状态和可能的前置状态的关系 */
		PAY(Status.PAY, 			new Status[] {Status.ORDER}),
		STOCK(Status.STOCK, 		new Status[] {Status.PAY}),
		DELIVERY(Status.DELIVERY, 	new Status[] {Status.STOCK}),
		RETURN(Status.RETURN, 		new Status[] {Status.DELIVERY}),
		CANCEL(Status.CANCEL, 		new Status[] {Status.ORDER, Status.PAY, Status.STOCK});
		
		private Status status;
		private Status[] prestatusArray;
		
		private StatusPreStatusMapping(Status status, Status[] prestatusArray) {
			this.status = status;
			this.prestatusArray = prestatusArray;
		}
		
		public Status getStatus() {
			return status;
		}

		public Status[] getPrestatusArray() {
			return prestatusArray;
		}
		
	}
	
	// 设置状态和可能的前置状态的关系
	public static Map<Status, Set<Status>> statusPreStatusMapping = new HashMap<Status, Set<Status>>();
	
	static {
		for (StatusPreStatusMapping temp : StatusPreStatusMapping.values()) {
			statusPreStatusMapping.put(temp.getStatus(), new HashSet<Status>(Arrays.asList(temp.getPrestatusArray())));
		}
	}
	
	/**
	 * 判断前置状态是否正确
	 * @param updStatus 设置状态
	 * @param preStatus 前置状态
	 * @return 是否正确
	 */
	public static boolean validatePreStatus(Status updStatus, Status preStatus) {
		return statusPreStatusMapping.get(updStatus).contains(preStatus);
	}

}
