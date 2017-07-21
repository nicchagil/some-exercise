import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OutOfMemoryErrorExercise {
	
	private static Logger logger = Logger.getLogger(OutOfMemoryErrorExercise.class.getName());

	public static void main(String[] args) {
		logger.info("start.");
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < 1000000000; i++) {
			list.add("This is a string for " + i);
		}
		
		logger.info("end.");
	}

}
