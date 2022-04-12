import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

class LocalizationServiceImplTest {
    private static LocalizationServiceImpl sut;

    @BeforeAll
    static void init() {
        sut = new LocalizationServiceImpl();
    }

    @Test
    void test_locale_RUS() {
        String expected = "Добро пожаловать";
        String result = sut.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void test_locale_All() {
        String expected = "Welcome";
        String result = sut.locale(Country.RUSSIA);
        Assertions.assertNotEquals(expected, result);
    }
}