import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.pivotal.logging.CoolApi;


public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Logging from slf4j logger!");
        CoolApi api = new CoolApi();
        api.doApiStuff();
    }
}
