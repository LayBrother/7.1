import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

class GeoServiceImplTest {
    private static GeoServiceImpl sut;

    @BeforeAll
    static void init() {
        sut = new GeoServiceImpl();
    }

    @Test
    void test_byIp_US() {
        String ip = "96.1.2.3";
        Country expected = Country.USA;
        Country result = sut.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_byIp_RU() {
        String ip = "172.1.2.3";
        Country expected = Country.RUSSIA;
        Country result = sut.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }
}