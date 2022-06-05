package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
    @Test
    public void testMyIp(){
        String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12()
                .getIpLocation("95.29.58.213");
        assertEquals(ipLocation20, "<GeoIP><Country>RU</Country><State>25</State></GeoIP>");
    }
}
