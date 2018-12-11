import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Logging from log4j2!"); //TODO: use the lambda syntax!
    }
}

//TODO what if only the api library is included?

//TODO no Log4j 2 configuration file found