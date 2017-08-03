

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MySimpleJob implements SimpleJob {
	
	private final Logger logger = LoggerFactory.getLogger(MySimpleJob.class);

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.info("ABC - " + shardingContext);
	}
	
}