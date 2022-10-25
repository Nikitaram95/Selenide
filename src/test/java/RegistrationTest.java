import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    @BeforeEach
    void openWebPage() {
        open("http://localhost:9999");
    }

    @Test
    void shouldArrangeDelivery() {
        $("[data-test-id='city'] input").setValue("Москва");
        String dateTest = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id= 'date'] input").setValue(dateTest);
        $("[data-test-id='name'] input").setValue("Кузнецов Никита");
        $("[data-test-id='phone'] input").setValue("+79647989765");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        //$$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована на")).shouldBe(visible);
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(dateTest)).shouldBe(visible);
    }
}





