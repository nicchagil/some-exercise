import java.util.logging.Logger;

public class HowToUse {

	public static void main(String[] args) {
		 Logger logger = Logger.getLogger("HowToUse"); // 默认级别info
		 
		 /* 各打印级别 */
		 logger.severe("severe");
		 logger.warning("warning");
		 logger.info("info");
		 logger.config("config");
		 logger.fine("fine");
		 logger.finer("finer");
		 logger.finest("finest");
	}

}
