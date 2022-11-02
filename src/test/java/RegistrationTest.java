import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void openWebPage() {
        open("http://localhost:9999");
    }

    @Test
    void shouldArrangeDelivery() {
        $("[data-test-id='city'] input").setValue("Москва");
        String planningDate = generateDate(3);
        $("[data-test-id= 'date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Кузнецов Никита");
        $("[data-test-id='phone'] input").setValue("+79647989765");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldSelectACityFromTheList() {
        $("[data-test-id='city'] input").setValue("Мо");
        $(withText("Москва")).click();
        String planningDate = generateDate(3);
        $("[data-test-id= 'date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Кузнецов Никита");
        $("[data-test-id='phone'] input").setValue("+79647989765");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldSelectDate() {
        String planningDate = generateDate(3);
        $("[data-test-id='city'] input").setValue("Москва");
        $(withText("Москва")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Кузнецов Никита");
        $("[data-test-id='phone'] input").setValue("+79647989765");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}