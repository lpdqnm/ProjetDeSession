package ProjetSession;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class SoinTest {

    @Test
    public void testClassSoin() {
        Soin soin = new Soin();
        assertEquals(Soin.class, soin.getClass());
    }
}
