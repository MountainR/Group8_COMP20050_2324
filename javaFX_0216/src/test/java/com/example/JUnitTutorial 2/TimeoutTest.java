
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimeoutTest {
    @Test(timeout=100)
    public void testTimeout() throws Exception {
          TimeUnit.SECONDS.sleep(1);
    }
}

