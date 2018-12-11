import io.pivotal.logging.jcl.ApiUsingJCL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApiUsingJCL api = new ApiUsingJCL();
        api.doStuff();
        LOGGER.info("Logging from slf4j logger!");
    }
}
