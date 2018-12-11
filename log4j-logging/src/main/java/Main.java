import org.apache.log4j.Logger;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();
        logger.info("Logging from log4j!");
    }
}
