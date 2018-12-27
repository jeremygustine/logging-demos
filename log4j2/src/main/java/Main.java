import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //Log4j2 lazy logging: https://www.baeldung.com/log4j-2-lazy-logging

        logger.info("Logging from {}!", () -> getLoggerString());
    }

    private static String getLoggerString() {
        return "Log4j2";
    }
}

//TODO what if only the api library is included?