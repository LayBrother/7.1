import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;

class MessageSendlerImplTest {
    private static MessageSenderImpl sut;
    private static Map<String, String> headers;

    @BeforeAll
    static void init() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        sut = new MessageSenderImpl(geoService, localizationService);
        headers = new HashMap<>();
    }

    @Test
    void test_msg_RUS() {
        String ip = "172.1.2.3";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = "Добро пожаловать";
        String result = sut.send(headers);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_msg_US() {
        String ip = "96.1.2.3";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = "Welcome";
        String result = sut.send(headers);
        Assertions.assertEquals(expected, result);
    }
}