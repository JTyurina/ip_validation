import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class IpValidationTest extends Assert {

    private IpValidation ipv;

    @Before
    public void init() {
        ipv = new IpValidation();
    }

    @After
    public void tearDown() {
        ipv = null;
    }

    @Test
    public void calls() {

        assertTrue(ipv.validate("192.168.0.1"));
        assertFalse(ipv.validate("192.0.1"));
        assertFalse(ipv.validate("999.168.0.1"));
        String[] s = {"192", "168", "0", "1"};
        assertEquals(3232235521L,ipv.convertToDecimal(s));
        assertEquals("192.168.0.1",ipv.convertDecimalToIp(3232235521L));
    }

}
