import com.spaceXinsights.restservice.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    public void throws_exception_if_wrong_url() {
        String wrongURL = "This is the wrong URL";
        Main main = new Main();
        assertThrows(MalformedURLException.class, () ->
                main.start(wrongURL, wrongURL));
    }

    @Test
    public void throws_exception_if_can_not_connect_to_API() {
        String wrongURL = "https://api.spacexdata.com/v4/payload";
        Main main = new Main();

        assertThrows(IOException.class, () ->
                main.start(wrongURL, wrongURL));
    }
}
