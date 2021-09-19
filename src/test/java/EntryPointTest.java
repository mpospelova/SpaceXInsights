import com.spaceXinsights.restservice.EntryPoint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryPointTest {

    @Test
    public void throws_exception_if_wrong_url() {
        String wrongURL = "This is the wrong URL";
        EntryPoint entryPoint = new EntryPoint();
        assertThrows(MalformedURLException.class, () ->
                entryPoint.start(wrongURL, wrongURL));
    }

    @Test
    public void throws_exception_if_can_not_connect_to_API() {
        String wrongURL = "https://api.spacexdata.com/v4/payload";
        EntryPoint entryPoint = new EntryPoint();

        assertThrows(IOException.class, () ->
                entryPoint.start(wrongURL, wrongURL));
    }

    @Test
    public void string_returned() throws IOException {
        String correctURLLaunch = "https://api.spacexdata.com/v5/launches";
        String correctURLPayload = "https://api.spacexdata.com/v4/payloads";
        EntryPoint entryPoint = new EntryPoint();

        assertNotNull(entryPoint.start(correctURLLaunch, correctURLPayload));
    }
}
