import org.apache.logging.log4j.LogManager;

public class Main {
    private static org.apache.logging.log4j.Logger LOG4J2_LOGGER = LogManager.getLogger(Main.class);
    private static org.apache.log4j.Logger LOG4J_LOGGER = org.apache.log4j.Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG4J_LOGGER.info("Logging from Log4j!");
        LOG4J2_LOGGER.info("Logging from {}!", () -> getLoggerString());
    }

    private static String getLoggerString() {
        return "Log4j2";
    }
}