import io.pivotal.logging.CoolApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CoolApi api = new CoolApi();
        api.doApiStuff();
        LOGGER.info("Logging from slf4j logger!");
    }
}
