import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Logging with java.util.logging!");
    }
}
