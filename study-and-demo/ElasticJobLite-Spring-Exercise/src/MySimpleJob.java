import org.apache.log4j.Logger;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MySimpleJob implements SimpleJob {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.info(shardingContext.getShardingItem());
	}
	
}
