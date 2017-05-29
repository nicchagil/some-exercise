import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.MyLog;

public class HowToTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(HowToTest.class);

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				LOGGER.info("It is HowToTest.");
				LOGGER.debug("It is HowToTest.");
				MyLog.printMsg();
			}
		}, 3000, 3000);
	}

}
