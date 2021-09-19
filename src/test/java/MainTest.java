import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    public void throws_exception_if_wrong_url() {
        String wrongURL = "This is the wrong URL";
        assertThrows(MalformedURLException.class, () ->
                Main.start(wrongURL, wrongURL));
    }

    @Test
    public void throws_exception_if_can_not_connect_to_API() {
        String wrongURL = "https://api.spacexdata.com/v4/payload";
        assertThrows(IOException.class, () ->
                Main.start(wrongURL, wrongURL));
    }
}
