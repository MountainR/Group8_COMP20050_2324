
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.Ignore;

public class IgnoreTest {
   @Ignore("Test is ignored")
   @Test
   public void testIgnore() {
     Integer aNumber = Integer.valueOf(768);
     assertSame("should be same", aNumber, aNumber);
   }
}
